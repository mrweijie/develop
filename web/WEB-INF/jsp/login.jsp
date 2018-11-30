<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/15
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" class="fullscreen-bg">

<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="../../a_res/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../a_res/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../a_res/assets/vendor/linearicons/style.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../../a_res/assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="../../a_res/assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="../../a_res/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="../../a_res/assets/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <div class="vertical-align-wrap">
        <div class="vertical-align-middle">
            <div class="auth-box ">
                <div class="left">
                    <div class="content">
                        <div class="header">
                            <div class="logo text-center"><img src="../../a_res/assets/img/logo-dark.png" alt="Klorofil Logo"></div>
                            <p class="lead">Login to your account</p>
                        </div>
                        <form class="form-auth-small">
                            <div class="form-group">
                                <label for="signin-email" class="control-label sr-only">User</label>
                                <input type="text" class="form-control" id="signin-email" value="" placeholder="username">
                            </div>
                            <div class="form-group">
                                <label for="signin-password" class="control-label sr-only">Password</label>
                                <input type="password" class="form-control" id="signin-password" value="" placeholder="pwd">
                            </div>
                            <div class="form-group clearfix">
                                <label class="fancy-checkbox element-left">
                                    <input type="checkbox">
                                    <span>Remember me</span>
                                </label>
                            </div>
                            <input type="button" class="btn btn-primary btn-lg btn-block" onclick="login()" value="LOGIN">
                            <div class="bottom">
                                <%--<span class="helper-text"><i class="fa fa-lock"></i> <a href="#">Forgot password?</a></span>--%>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="right">
                    <div class="overlay"></div>
                    <div class="content text">
                        <%--<h1 class="heading">Free Bootstrap dashboard template</h1>--%>
                        <%--<p>by The Develovers</p>--%>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<!-- END WRAPPER -->
<script src="../a_res/js/jquery-1.11.1.min.js"></script>
<script>
    function login () {
        var username = $("#signin-email").val();
        var password = $("#signin-password").val();
        $.ajax({
            url: "/home/login",
            type: "POST",
            data:{
              username : username,
              pwd : password
            },
            success: function (data) {
                alert("修改成功");
                // console.warn(data);
            },
            error: function () {
                alert("修改失败");
            }
        });
    };
</script>
</body>

</html>

