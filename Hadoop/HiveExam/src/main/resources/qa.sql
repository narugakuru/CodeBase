-- 创建文本(TextFile)文件表
drop table if exists persons;
CREATE TABLE persons (
    name STRING,
    age int,
    birthday date,
    address struct<country:string, province:string, city:string>,
    hobbies array<string>,
    skills map<string, string>
    )
    -- 指定行格式
    ROW FORMAT DELIMITED
        -- LINES TERMINATED BY '\n'        -- 数据行默认换行分割
        FIELDS TERMINATED BY ','           -- 字段使用<,>分割
        -- ESCAPED BY '\\'                 -- 默认逃逸字符\
        COLLECTION ITEMS TERMINATED BY ';' -- 复合类型(Map,Array,Struct)元素使用<;>分割
        MAP KEYS TERMINATED BY ':'         -- MAP元素使用<;>分割
        NULL DEFINED AS ''                 -- 空值默认填充空串 \n
    STORED AS TEXTFILE          -- 默认格式
    TBLPROPERTIES (
        "skip.header.line.count"="1",      -- 跳过头部第一行
        "skip.footer.line.count"="1"       -- 跳过尾部第一行
    );
-- 跳过头部第一行
alter table persons set tblproperties ("skip.header.line.count"="1");
-- 跳过尾部第一行
alter table persons set tblproperties ("skip.footer.line.count"="1");
-- 空值默认填充空串
alter table persons set serdeproperties ("serialization.null.format"="");
-- 装载数据到表
load data local inpath '/root/persons.csv' overwrite into table persons;
-- 清空表中数据
truncate table persons;
-- 查询
select * from persons;
select name, age,
       address.country,
       hobbies[0] as `1st hobby`,
       size(hobbies) as hobbies_size,
       array_contains(hobbies, 'shopping') as hobbies_contain,
       size(skills) as skills_size,
       map_keys(skills) as skills_keys,
       array_contains(map_keys(skills), 'cooking') as key_contain,
       map_values(skills) as skills_values,
       array_contains(map_values(skills), 'cooking') as value_contain,
       nvl(skills['cooking'], "-") as cooking_level,
       map_keys(skills)[0] as `1st map key`
    from persons;
-- 创建文本(TextFile)文件表
CREATE TABLE persons (
                         name STRING,
                         age int,
                         birthday date,
                         address map<string, string>,
                         hobbies array<string>
)
    -- 指定行格式（正则序列化器）
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.RegexSerDe'
        -- 设置行中各字段如何分割
        WITH SERDEPROPERTIES (
        "input.regex" = "([^]*) ([^]*) ([^]*) (-|\\[^\\]*\\]) ([^ \"]*|\"[^\"]*\") (-|[0-9]*) (-|[0-9]*)(?: ([^ \"]*|\".*\") ([^ \"]*|\".*\"))?"
        )
    STORED AS TEXTFILE;

-- 创建JSON文件数据表

-- Q1.提取酒店预订记录[reserve.csv文件]中，
-- 预订ID，酒店ID，顾客ID，预订时间，入住日期，入住时间，退房日期信息
-- （"reserve_id","hotel_id","customer_id","reserve_datetime","checkin_date","checkin_time","checkout_date"）信息。
select reserve_id as `预订ID`, hotel_id, reserve_datetime from reserve limit 5;

-- 提取酒店预订记录[reserve.csv文件]中，入住日期在2016年10月1日至2016年10月15日间的信息
select * from reserve
where date(checkin_date) >= date("2016-10-01")
  and date(checkin_date) <= date("2016-10-15")
limit 10;
--- 或者
select * from reserve
where date(checkin_date) between date("2016-10-01") and date("2016-10-15")
limit 10;

-- Q2a.分别提取在入住期间，连续入住多晚与一晚的预订信息
select * from reserve
where datediff(checkout_date, checkin_date)=1
limit 10;
--
select * from reserve
where datediff(checkout_date, checkin_date)>1 limit 10;
-- Q2b.在2016年10月1日至2016年10月15日间在住的信息。
select * from reserve
where date(checkin_date) >= date("2016-10-01")
    and date(checkin_date) <= date("2016-10-15") or
            date(checkin_date)  < date("2016-10-01")
        and date(checkout_date) > date("2016-10-01")
limit 10;


-- Q3.随机采样酒店预订记录[reserve]中约50%的记录信息
-- 随机采样
select rand(second(`current_timestamp`()));
-- 全局仅一个Reducer
select count(*) from reserve;
select * from reserve order by rand() limit 10;
-- 局部可多个Reducer
select *from reserve sort by rand() limit 10;
-- Mapper阶段distribute 随机分配多个Reducer
select * from reserve distribute by rand() sort by rand() limit 10;
-- 等效
select * from reserve cluster by rand() limit 10;
-- 块采集50%的数据
select * from reserve tablesample(50 PERCENT);
-- 块采集100行数据
select * from reserve tablesample(100 ROWS);
-- 块采集2MB大小数据
select * from reserve tablesample(2M);
-- 依据rand()采100分桶中3号（桶号从1开始）桶中数据
select * from reserve tablesample(BUCKET 1 out of 3 on rand());

-- Q3a.采样提取记录顾客不重复
-- 建立临时表
with reserver_tb as (
    select * ,
           -- 窗口函数
           -- first_value(col, skip_null_value) 取当前分区(窗口或窗口帧)第一个值
           -- last_value(col, skip_null_value)  取当前分区最后一个值
           -- lag(col, up_n, null_default)      取当前分区当前行向上第n个值
           -- lead(col, down_n, null_default)   取当前分区当前行向下第n个值

           -- rank()                            排序相同时会重复，如，1、1、 3、4
           -- dense_rank()                      排序相同时会重复，如，1、1、 2、3
           -- row_number()                      物理行号，如1、2、3、4

           -- 依据客户ID分窗口，窗口帧内随机排序并生成行号
           row_number() over (partition by customer_id order by rand()) as rdm_num,
           rand() as rdm
    from reserve
)
-- 取随机值小于0.5的数据
select * from reserver_tb
where rdm_num = 1 and rdm <= 0.5;

-- Q4.在酒店预订记录[reserve]中，计算每家酒店预订记录数及预订顾客数
select hotel_id as `酒店`, count(*) as `预订记录`, count(distinct customer_id) as `预订顾客`
from reserve group by hotel_id;

-- Q4a.每家酒店按入住人数分类计算总住宿费
select hotel_id as `酒店`, people_num as `入住人数`, sum(total_price) as `住宿费`
from reserve group by hotel_id, people_num
order by hotel_id limit 10;
-- Q4b.计算酒店按各顾客总消费额及酒店总收入
select hotel_id, customer_id, sum(total_price)
from reserve
group by hotel_id, customer_id with cube having count(*) > 1
order by hotel_id limit 20;
-- a,b,c rollup [abc,ab,a,()] cube [abc, ab, ac, bc ,a, b, c, ()]
-- 普通方式
(select hotel_id, '' as customer_id,sum(total_price)
    from reserve group by hotel_id limit 10)
union all
(select hotel_id, customer_id, sum(total_price)
    from reserve group by hotel_id, customer_id limit 10);

-- Q4c.获得每家酒店人数分类住宿总费用最高的数据
with comp as (
    select hotel_id , people_num ,  sum(total_price) as tp,
           ROW_NUMBER()  over (partition by hotel_id order by sum(total_price) desc) as rn
    from reserve group by hotel_id, people_num
)

select * from comp where rn=1 order by hotel_id limit  10;

-- Q5.在酒店预订记录[reserve.csv文件]中，计算各酒店住宿费最大值，最小值，中位数，四分位数(Q1)
select hotel_id,
       max(total_price) as `最大`,
       min(total_price) as `最小`,
       percentile(total_price, 0.5) as `中位数`,
       percentile(total_price, 0.25) as `四分位数(Q1)`,
       avg(total_price) as `均价`,
       round(avg(total_price), 2) as `保留2位`,
       ceil(avg(total_price)) as `向上取整`,
       floor(avg(total_price)) as `向上取整`
from reserve group by hotel_id
order by hotel_id limit 10;



-- from .. where .. join .. on .. select .. group by .. select .. having .. distinct .. order by .. limit .. union/union all











-- 统计酒店每年各季度住宿费用收入总额
select hotel_id,
       year(checkin_date) as `year`,
       quarter(checkin_date) as `quarter`,
       sum(total_price) as total
from reserve
group by hotel_id, year(checkin_date), quarter(checkin_date)
order by hotel_id, year(checkin_date), quarter(checkin_date) limit 20;

select (month(2016-02-23) + 2) DIV 3, ceil(month(2016-02-23) / 3);
-- 统计各酒店每年假期住宿费用收入占比
with fintlb as (select hotel_id, year(r.checkin_date) as `year`, sum(total_price) as total
    from reserve r join holiday_mst h
                    on r.checkin_date = h.target_day
group by hotel_id, year(r.checkin_date), h.holidayday_flg)
select hotel_id, `year`, concat(round((min(total) / sum(total)) * 100 , 2), "%")
    from fintlb group by hotel_id, `year` order by hotel_id;
--
select r.hotel_id, year(r.checkin_date),
       sum(`if`(h.holidayday_flg, r.total_price, 0)) as holiay,
       sum(r.total_price) as total,
       concat(
           round(
                sum(`if`(h.holidayday_flg, r.total_price, 0)) / sum(r.total_price) * 100, 2), "%") as `percent`
from reserve r join holiday_mst h on r.checkin_date = h.target_day
group by r.hotel_id, year(r.checkin_date);

-- 各酒店以年为同比周期月份为环比周期，统计住宿费用和入住人数的同比与环比数据
select hotel_id,
    year(checkin_date) as `year`,
    month(checkin_date) as `month`
from reserve
group by hotel_id, year(checkin_date), month(checkin_date)
order by hotel_id,year(checkin_date), month(checkin_date)
;











-- UDF
select name, ucase(name) from persons;
-- UDAF
select year(checkin_date), sum(total_price) from reserve
group by year(checkin_date)
;
-- UDTF.
select * from persons;
select explode(hobbies) from persons;
select name, hs from persons lateral view explode (hobbies) a as hs;


-- add jar hdfs://192.168.133.129:9000/user/hive/jars/HiveExam-1.0-SNAPSHOT.jar;

add jar /root/HiveExam-1.0-SNAPSHOT.jar;
create temporary function gupper as 'org.example.UpperUDF';
select gupper(name, "KKK") from persons;

select *,  INPUT__FILE__NAME, BLOCK__OFFSET__INSIDE__FILE from persons;

--  用户表(users.dat)
CREATE TABLE `users`(
                        `userid` string COMMENT '用户ID',
                        `gender` string COMMENT '性别',
                        `age` int COMMENT '年龄',
                        `occupation` string COMMENT '职业',
                        `zip_code` string COMMENT '邮编')
    ROW FORMAT SERDE
        'org.apache.hadoop.hive.contrib.serde2.MultiDelimitSerDe'
        WITH SERDEPROPERTIES ('field.delim'='::');
--  电影表(movies.dat)
CREATE TABLE `movies`(
                         `movieid` string COMMENT '电影ID',
                         `movietitle` string COMMENT '片名',
                         `genres` array<string> COMMENT '类别')
    ROW FORMAT SERDE
        'org.apache.hadoop.hive.contrib.serde2.MultiDelimitSerDe'
        WITH SERDEPROPERTIES ('collection.delim'='|','field.delim'='::');
--  电影评论表(ratings.dat)
CREATE TABLE `ratings`(
                          `userid` string COMMENT '用户ID',
                          `movieid` string COMMENT '电影ID',
                          `rating` int COMMENT '评分',
                          `ratingtime` bigint COMMENT '评论时间')
    ROW FORMAT SERDE
        'org.apache.hadoop.hive.contrib.serde2.MultiDelimitSerDe'
        WITH SERDEPROPERTIES ('field.delim'='::');

load data local inpath '/root/ratings.dat' overwrite into table db_one.`ratings`;
select * from ratings limit 10;

create materialized view ratingtop10
    disable rewrite
    as
select r.MovieId,count(*) as mc from ratings as r
group by r.MovieID order by mc desc limit 10;

select m.movietitle,r10.mc from movies as m join ratingtop10 r10
                                                 on m.MovieID=r10.movieid order by r10.mc desc;

SELECT m.movietitle, COUNT(r.MovieID) AS rating_count

FROM movies m

         JOIN ratings r ON m.MovieID = r.MovieID

GROUP BY m.MovieID, m.movietitle

ORDER BY rating_count DESC

LIMIT 10;