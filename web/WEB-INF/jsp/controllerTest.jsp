<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
显示
<button onclick="getAll()" id="btn">aaa</button>

<button onclick="client()">bbb</button>
<table id="tab" style="width: 500px;">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>price</th>
        <th>date</th>
    </tr>
</table>
<br>添加<br>

<form action="/book/add" method="post" >
    <div>title:<input name="title"></div>
    <div>price:<input name="price"></div>
    <div>date: <input type="date" name="publishDate"></div>
    <div><input type="submit" vlaue="添加"></div>
</form>

<script src="../a_res/js/jquery-1.11.1.min.js"></script>
<script>
    // getAll();
    function getAll () {
        $.ajax({
            url: "/book/getAll",
            type: "GET",
            success: function (data) {
                var tr = ""
                for(var a = 0;a < data.length;a++){
                    var date = formatDate(new Date(data[a].publishDate));
                    tr += "<tr><td style = 'text-align: center'>"+data[a].id+"</td><td style = 'text-align: center'>"+data[a].title+"</td><td style = 'text-align: center'>"+data[a].price+"</td><td style = 'text-align: center'>"+date+"</td></tr>";
                }
                $("#tab").append(tr);
                console.log(data.data.list)
            },
            error: function () {
                alert("修改失败");
            }
        });
    };

    function client () {
        $.ajax({
            url: "/book/client",
            type: "GET",
            success: function (data) {
            },
            error: function () {
                alert("修改失败");
            }
        });
    };

    function formatDate(now) {
        var year=now.getFullYear();
        var month=now.getMonth()+1;
        var date=now.getDate();
        if(month<10){
            month = "0"+month;
        }
        if(date<10){
            date = "0"+date;
        }
        return year+"-"+month+"-"+date;
    }

    $("form").submit(function () {
        var title = $("input[name='title']").val();
        var price = $("input[name='price']").val();
        var publishDate = $("input[name='publishDate']").val();

        if (title == "" || title == null || title == undefined) {
            alert("title 不能为空");
            return false;/*阻止表单提交*/
        } else if (price == "" || price == null || price == undefined) {
            alert("price 不能为空");
            return false;/*阻止表单提交*/
        } else if (publishDate == "" || publishDate == null || publishDate == undefined) {
            alert("publishDate 不能为空");
            return false;/*阻止表单提交*/
        } else {
            return true;
        }
    })
</script>
</body>
</html>
