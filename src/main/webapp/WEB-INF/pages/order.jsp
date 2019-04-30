<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/static/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>单车租用</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/layui/layui.all.js"></script>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/index.js"></script>
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xm" lay-event="pay">支付</a>
</script>
<script type="text/javascript">
$(function () {



    layui.use('table', function(){

        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#test'
            ,url: '/orderList'
            ,cellMinWidth: 80
            ,limit:10//每页默认数
            ,limits:[10,20,30,40]
            ,cols: [
                [
                    {checkbox: true, fixed: true}
                    ,{field:'id', title: 'ID', sort: true, fixed: true}
                    ,{field:'bicycleNumber', title: '单车编号'}
                    ,{field:'bicycleTypeName', title: '单车类型', sort: true}
                    ,{field:'startTimeShow', title: '开始时间'}
                    ,{field:'endTimeShow', title: '结束时间'}
                    ,{field:'timeShow', title: '租用时间'}
                    ,{field:'statusName', title: '订单状态'}
                    ,{field:'totalPrice', title: '总价格'}
                ]
            ]
            ,page: true
            ,height:'full-60'
        });
        //监听工具条
        table.on('tool(test)', function(obj){
            var data = obj.data;
            console.info(data)
            if(obj.event === 'pay'){
                layer.open({
                    type: 1
                    ,title: "确认支付" //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['确认支付', '残忍拒绝']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content:''
                    ,yes: function(){
                        var id=data.id;
                        $.ajax({
                            type: "post",
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            data:JSON.stringify({
                                "orderId":id
                            }),
                            url: "/orderPay",
                            success: function (result) {
                                if (result.code != "0") {
                                    alert(result.errorMessage);
                                } else {
                                    alert("支付成功");
                                    layer.closeAll();
                                    //执行重载
                                    table.reload('test', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
        //监听表格复选框选择
        table.on('checkbox(test)', function(obj){
            console.log(obj)
        });

    });


    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
})





</script>

</body>
</html>