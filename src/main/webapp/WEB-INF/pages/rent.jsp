<%@ page import="com.car.shopping.entity.TbUsers" %>
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
<%
    TbUsers username = (TbUsers)session.getAttribute("user");
    Integer balance=username.getBalance();
%>
<input type="hidden" id="balance" value="<%=balance%>" hidden>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xm" lay-event="rent">租用</a>
</script>
<script type="text/javascript">
$(function () {



    layui.use(['table', 'form'], function(){

        var table = layui.table;
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,laydate = layui.laydate;

        //方法级渲染
        table.render({
            elem: '#test'
            ,url: '/rentList'
            ,cellMinWidth: 80
            ,limit:10//每页默认数
            ,limits:[10,20,30,40]
            ,cols: [
                [
                    {checkbox: true, fixed: true}
                    ,{field:'id', title: 'ID', sort: true, fixed: true}
                    ,{field:'number', title: '单车编号'}
                    ,{field:'typeName', title: '单车类型', sort: true}
                    ,{field:'price', title: '单车价格'}
                    ,{field:'placementName', title: '单车存放点'}
                    ,{field:'statusName', title: '单车状态', sort: true}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                ]
            ]
            ,page: true
            ,height:'full-60'
        });
        //监听工具条
        table.on('tool(test)', function(obj){
            var balance=$("#balance").val();
            var data = obj.data;
            $("#bicycleId").val(data.id);
            console.info(data)
            if(obj.event === 'rent'&&balance>0){
                layer.open({
                    type: 1
                    ,title: "确认租赁" //不显示标题栏
                    ,closeBtn: false
                    ,area: '600px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
/*                    ,btn: ['确认租用', '残忍拒绝']
                    ,btnAlign: 'c'*/
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content:$("#add-main")
                    ,yes: function(){

                    }
                });
            }else{
                layer.open({
                    type: 1
                    ,title: "金额不足请充值" //不显示标题栏
                    ,closeBtn: false
                    ,area: '600px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuimoney' //设定一个id，防止重复弹出
                    /*                    ,btn: ['确认租用', '残忍拒绝']
                                        ,btnAlign: 'c'*/
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content:$("#add-money")
                    ,yes: function(){

                    }
                });
            }
        });
        //监听表格复选框选择
        table.on('checkbox(test)', function(obj){
            console.log(obj)
        });

        laydate.render({
            elem: '#startTime'

            ,type: 'datetime'
        });
        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });
/*        //提交监听事件
        form.on('submit(save)', function (data) {

            //alert(JSON.stringify(params))
            submit($,JSON.stringify(params1));
            return false;
        })*/
        var obj = document.getElementById('closeBtn');
        obj.addEventListener('click', function cancel(){
            CloseWin();
        });
        var obj1 = document.getElementById('closeMoney');
        obj1.addEventListener('click', function cancel(){
            CloseWin();
        });


        $('#weixin').on('click', function(){
            var balance=$("#money").val();
            if (""==balance||null==balance){
                alert("金额为空");
            }
            $.ajax({
                cache: false,
                type: "POST",
                url:"/updateBalance",
                data:{ "balance":balance}
                ,
                async: true,
                success: function(data) {
                    if(data.code == 0){
                        CloseWin();
                        alert("充值成功")
                    }else {
                        alert(data.msg)
                    }
                },
            });
        });
        $('#zhifubao').on('click', function(){
            var balance=$("#money").val();
            if (""==balance||null==balance){
                alert("金额为空");
            }
            $.ajax({
                cache: false,
                type: "POST",
                url:"/updateBalance",
                data:{ "balance":balance}
                ,
                async: true,
                success: function(data) {
                    if(data.code == 0){
                        CloseWin();
                        alert("充值成功")
                    }else {
                        alert(data.msg)
                    }
                }
            });
        });

        $('#submit').on('click',function (){
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var bicycleId= $("#bicycleId").val();
            var params1 = {
                "endTime":endTime,
                "startTime":startTime,
                "bicycleId":bicycleId
            };
            $.ajax({
                cache: false,
                type: "POST",
                url:"/createRent",
                data:JSON.stringify(params1)
                ,
                async: true,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data) {
                    if(data.code == 0){
                        CloseWin();
                        alert("租赁成功")
                    }else {
                        alert(data.msg)
                    }
                },
            });
        }
        )
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

//关闭页面
function CloseWin(){
  /*  parent.location.reload(); // 父页面刷新
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭*/
    parent.location.reload(); // 父页面刷新
    layer.closeAll();
}


</script>
<div id="add-main" style="display: none;">
    <input type="text" class="layui-input" id="bicycleId"  style="display:none" >
        <div class="layui-form-item center" >
            <label class="layui-form-label" style="width: 100px" >开始租用时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="startTime" lay-verify="dateTime"  placeholder="yyyy-MM-dd" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">结束租用时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="endTime" lay-verify="dateTime"  placeholder="yyyy-MM-dd" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="submit" >立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn" >关闭</button>
            </div>
        </div>
</div>

<div id="add-money" style="display: none;">
        <div class="layui-form-item center" >
            <label class="layui-form-label" >请输入充值金额</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="money" >
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="saveMoney" id="weixin">微信充值</button>
                <button class="layui-btn" lay-submit lay-filter="saveMoney" id="zhifubao">支付宝充值</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closeMoney" >考虑一下</button>
            </div>
        </div>
</div>
</body>
</html>