<%@ page import="com.car.shopping.entity.TbUsers" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/static/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>租赁平台</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/layui/layui.all.js"></script>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/index.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" id="form">
    <div class="layui-header">
        <div class="layui-logo">租赁平台</div>
        <ul class="layui-nav">
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <%
                    TbUsers username = (TbUsers)session.getAttribute("user");
                    String name=username.getNickname();
                %>
                <a href="javascript:;" id="userName">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <%=name%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退出登录</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" id="memus">

            </ul>


        </div>
    </div>

    <div class="layui-body">
        <!-- 动态选项卡 -->
        <div id="tabzu" class="layui-tab layui-tab-card layui-tab-brief" lay-filter="tabDemo" lay-allowclose="true">
            <ul class="layui-tab-title"></ul>

            <div class="layui-tab-content">

            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

</body>
</html>