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
        NULL DEFINED AS ''                 -- 空值默认填充空串
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

-- Q2.提取酒店预订记录[reserve]中，入住日期在2016年10月1日至2016年10月15日间的信息。
select * from reserve
    where year(checkin_date) = 2016 and month(checkin_date) = 10 and
          day(checkin_date) >= 1 and day(checkin_date) <= 15
-- order by checkin_date
limit 10;
-- 或者
select * from reserve
where date(checkin_date) >= date("2016-10-01")
  and date(checkin_date) <= date("2016-10-15")
limit 10;
-- 或者
select * from reserve
    where date(checkin_date) between date("2016-10-01") and date("2016-10-15")
limit 10;

-- Q2a.分别提取在入住期间，连续入住多晚与一晚的预订信息
-- 多晚
select * from reserve
    where datediff(checkout_date, checkin_date) > 1
limit 10
;
-- 一晚
select * from reserve
where datediff(checkout_date, checkin_date) = 1
limit 10
;

-- Q2b.在2016年10月1日至2016年10月15日间在住的信息
select * from reserve
    where checkin_date between '2016-10-01' and '2016-10-15' or
          (checkin_date < '2016-10-01' and checkout_date >= '2016-10-01')
;

-- Q3.随机采样酒店预订记录[reserve]中约50%的记录信息
-- 随机采样
-- 全局仅一个Reducer
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
select * from reserve tablesample(BUCKET 3 out of 100 on rand());

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
    group by hotel_id, customer_id with rollup having count(*) > 1

    order by hotel_id limit 20;

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

select hotel_id, concat_ws(',', sort_array_by( collect_set(string(people_num)), 'col1', 'DESC'))
    from reserve
    group by hotel_id
    limit 20;

select  stack(2, customer_id, sex) from customer limit 10;

show functions ;

DESCRIBE FUNCTION row_number;

DESCRIBE FUNCTION EXTENDED row_number;

select version();

select xxx("sss", "sss")

