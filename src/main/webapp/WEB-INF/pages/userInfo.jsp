<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/static/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/layui/layui.all.js"></script>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/index.js"></script>
</head>
<body>
<form class="layui-form"  lay-filter="example">
    <input type="text" name="uid"  class="layui-input" style="display:none" value="${sessionScope.user.uid}">
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input" value="${sessionScope.user.nickname}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-block">
            <input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${sessionScope.user.password}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select name="sex" >
                <option value="0"  <c:if test="${0==sessionScope.user.sex}">selected</c:if>> 女</option>
                <option value="1"  <c:if test="${1==sessionScope.user.sex}">selected</c:if>> 男</option>
                <option value="2"  <c:if test="${2==sessionScope.user.sex}">selected</c:if>> 未知</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="eMail" placeholder="请输入邮箱" autocomplete="off" class="layui-input" value="${sessionScope.user.eMail} ">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" placeholder="请输入联系电话" autocomplete="off" class="layui-input" value="${sessionScope.user.phone}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" placeholder="请输入联系地址" autocomplete="off" class="layui-input" value="${sessionScope.user.address}">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">余额</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="balance" placeholder="￥" autocomplete="off" class="layui-input" disabled>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="submit">立即提交</button>
        </div>
    </div>
</form>

<script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');
        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length ==0){
                    return '名字不能为空';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(submit)', function(data){
                $.ajax({async: false,
                        type: "POST",
                        data: JSON.stringify(data.field),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        url: "/updateUserInfo",
                        success: function (result) {
                            if (result.code != "0") {
                                alert(result.errorMessage);
                            } else {
                                layer.alert("修改成功", {
                                    title: ''
                                })
                            }
                        }
                    }
                );
        });
        form.render();


    });



</script>

</body>
</html>