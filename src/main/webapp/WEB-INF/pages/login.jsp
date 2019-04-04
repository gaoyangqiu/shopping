<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/static/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>用户登录</title>

	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>


	<link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="/static/css/animate.css">
	<link rel="stylesheet" href="/static/css/style.css">

	</head>
	<body class="style-3">

		<div class="container">

			<div class="row">
				<div class="col-md-4 col-md-push-8">

					<!-- Start Sign In Form -->
					<form class="fh5co-form animate-box" data-animate-effect="fadeInRight">
						<h2>用户登录</h2>
						<div class="form-group">
							<label for="username" class="sr-only">用户名</label>
							<input type="text" class="form-control" id="username" placeholder="请输入用户名" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密码</label>
							<input type="password" class="form-control" id="password" placeholder="请输入密码" autocomplete="off">
						</div>
						<div class="form-group">
							<p>没有注册? <a href="/regist">注册</a>
						</div>
						<div class="form-group">
							<input  value="登录" class="btn btn-primary" onclick="login()">
						</div>
					</form>
					<!-- END Sign In Form -->
				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved</small></p></div>
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
	<!-- Main JS -->
	<script src="/static/js/main.js"></script>

		<script type="text/javascript">

            function login(){
                var phone=$("#username").val();
                var password=$("#password").val();
                $.ajax({
                        type: "POST",
                        data: JSON.stringify({
                            "phone": phone,
                            "password": password
                        }),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        url: "/ajaxLogin",
                        success: function (result) {
                            if (result.code != "0") {
                                alert(result.errorMessage);
                            } else {
                                location.href = "/index";
                            }
                        }
                    }
                );
            }

		</script>
	</body>
</html>

