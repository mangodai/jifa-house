<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎回来</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="application/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/echarts.min.js" type="application/javascript"></script>
    <style>
        body {
            margin: 0px;
            padding: 0px;
        }

        .welcome {
            position: absolute;
            top: 0px;
            left: 0px;
            bottom: 0px;
            right: 0px;
            margin: auto;
            width: 800px;
            height: 200px;
            text-align: center;
        }

        .welcome span {
            font-size: 90px;
            color: #009688;
        }

        .wel-world {
            font-size: 34px;
            color: #009688;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="welcome">
    <p><span class="layui-icon layui-icon-tree"></span></p>
    <p class="wel-world">欢迎访问后台管理系统！</p>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="container1" style="width: 100%;height:400px;"></div>
</div>
</body>
<script type="application/javascript">
    var data = ${data};
    setTimeout(getData4, 100);

    function getData4() {

        var dom = document.getElementById("container1");
        var myChart = echarts.init(dom);
        var app = {};
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: data.legendData
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: data.seriesData
                }
            ]
        };

        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }

    }
</script>
</html>