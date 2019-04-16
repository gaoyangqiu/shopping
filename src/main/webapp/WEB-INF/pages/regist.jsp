<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/include/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <title>用户注册</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/animate.css">
    <link rel="stylesheet" href="/static/css/style.css">

</head>
<body class="style-3">

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-push-8">

            <!-- Start Sign In Form -->
            <form action="#" class="fh5co-form animate-box" data-animate-effect="fadeInRight" id="registrationForm">
                <h2>注册</h2>
                <div class="form-group">
                    <label for="nickname" class="sr-only">用户名</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="用户名"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="eMail" class="sr-only">邮箱</label>
                    <input type="email" class="form-control" id="eMail" name="eMail" placeholder="邮箱"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">密码</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="phone" class="sr-only">联系电话</label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="联系电话"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="address" class="sr-only">地址</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="地址"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <p>已有账号? <a href="/login">登录</a></p>
                </div>
                <div class="form-group">
                    <input  value="提交注册" onclick="regist()" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->


        </div>
    </div>
    <div class="row" style="padding-top: 60px; clear: both;">
        <div class="col-md-12 text-center">
            <p>
                <small>&copy; All Rights Reserved</small>
            </p>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<!-- Bootstrap -->
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="/static/js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="/static/js/jquery.waypoints.min.js"></script>
<script src="/static/js/bootstrap/bootstrapValidator.min.js"></script>
<!-- Main JS -->
<script src="/static/js/main.js"></script>
<script type="text/javascript">
    $('#registrationForm').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nickname: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 10,
                        message: '长度6到10之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能是字符数字'
                    }
                }
            },
            eMail: {
                validators: {
                    notEmpty: {
                        message: '邮箱地址不能为空'
                    },
                    emailAddress: {
                        message: '邮箱格式不正确'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'nickname',
                        message: '密码和用户名不能相同'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '联系电话格式不正确'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '联系地址不能为空'
                    }
                }
            }
        }
    });

    function regist() {
        var nickname = $("#nickname").val();
        var eMail = $("#eMail").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        var address = $("#address").val();
        $.ajax({
                type: "POST",
                data: JSON.stringify({
                    "phone": phone,
                    "nickname": nickname,
                    "eMail": eMail,
                    "address": address,
                    "password": password,
                    "balance": 0

                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                url: "/registUserInfo",
                success: function (result) {
                    if (result.code != "0") {
                          alert(result.errorMessage);
                    } else {
                        alert("注册成功");
                        location.href = "/index";
                    }
                }
            }
        );
    }
</script>

</body>
</html>

