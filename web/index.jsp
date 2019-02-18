<%@ page language="java" contentType="text/html; charset=utf-8"
				pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<body>
<%--<div >--%>
	<ul>
		<li style="height: 50px"><a href="home/index">网站主页</a></li>
		<li style="height: 50px"><a href="home/houtai">登录页面</a></li>
		<li style="height: 50px"><a href="home/details">产品详细</a></li>
	</ul>
<%--</div>--%>
<script src="./a_res/js/jquery-1.11.1.min.js"></script>
<script>
    function abc () {
        $.ajax({
            url: "/home/list",
            type: "GET",
			data:{
              name:"1111",
			},
            dataType : 'json',
            success: function (data) {
                alert("修改成功");
				console.warn(data);
            },
            error: function () {
                alert("修改失败");
            }
        });
    };

</script>
</body>
</html>