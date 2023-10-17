<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>ECharts</title>
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="echarts.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="line-chart" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var lineChartData = JSON.parse('${lineChartData}');
    var lineChart = echarts.init(document.getElementById('line-chart'));
    // 指定图表的配置项和数据
    var option = ({
        title: {
            text: '各年龄段平均影评分走势'
        },
        xAxis: {
            type: 'category',
            data: lineChartData.age
        },
        yAxis: {
            type: 'value',
            min: 0,
            max: 10,
            interval: 1
        },
        series: [{
            data: lineChartData.avg_rating,
            type: 'line'
        }]
    });

    // 使用刚指定的配置项和数据显示图表。
    lineChart.setOption(option);
</script>
</body>
</html>