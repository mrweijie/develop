<%@ page language="java" contentType="text/html; charset=utf-8"
				pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<body>
<%--<form style="margin-top: 20px;">--%>
	<%--上传附件更新&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/tolist">上传</a>--%>
<%--</form>--%>
<div >
	<%--<a href="a_res/index.html"><img src="img/1.png" alt="后台页面" style="height: 500px;width: 500px"></a>--%>
	<%--<a href="pro2/index.html"><img src="img/img2.png" alt="后台页面" style="height: 500px;width: 500px"></a>--%>
	<a href="home/index"><%--<img src="img/img3.png" alt="后台页面" style="height: 500px;width: 500px">--%>   页面跳转</a>

	<form method="POST"  enctype="multipart/form-data" id="form1" action="/home/upload">
	</form>
	<button onclick="abc()">111111111111111111</button>
</div>
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