<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/20
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="../../a_res/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../../a_res/js/jquery-1.11.1.min.js"></script>
    <!-- Custom Theme files -->
    <link href="../../a_res/css/style.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 设置编码为 utf-8  -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Add fancyBox main JS and CSS files -->
    <script src="../../a_res/js/jquery.magnific-popup.js" type="text/javascript"></script>

    <script src="../../a_res/js/jquery.imagezoom.min.js" type="text/javascript"></script>

    <link href="../../a_res/css/magnific-popup.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header_top">
    <div class="container">
        <div class="logo">
            <a href="../../a_res/index.html"><img src="../../a_res/images/logo.png" alt=""></a>
        </div>
        <div class="cssmenu">
            <ul id="nav">
                <li class="current"><a href="#section-1">Home</a></li>
                <li><a href="#section-2">About Us</a></li>
                <li><a href="#section-3">Latest Product</a></li>
                <li><a href="#section-4">Latest Video</a></li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<%--<div class="wmuSlider example1 section" id="section-1">--%>
<%--</div>--%>
<script src="../../a_res/js/jquery.wmuSlider.js"></script>
<script>
    $('.example1').wmuSlider();
</script>
<div class="main">
    <div class="video section" id="section-1">
        <div class="container">
            <div class="row" style="border: 1px solid #CDCDCD;">
                <div class="col-md-6 about_right">
                    <div class="tb-booth tb-pic tb-s310">
                        <a href="images/01.jpg"><img src="../../a_res/images/p1.jpg" alt="美女" rel="../../a_res/images/p1.jpg" class="jqzoom" /></a>
                    </div>

                    <ul class="tb-thumb" id="thumblist">
                        <li class="tb-selected"><div class="tb-pic tb-s40"><a href="javascript:void(0)"><img src="../../a_res/images/04_small.jpg" mid="../../a_res/images/p1.jpg" big="../../a_res/images/p1.jpg"></a></div></li>
                        <li><div class="tb-pic tb-s40"><a href="javascript:void(0)"><img  src="../../a_res/images/04_small.jpg" mid="../../a_res/images/p2.jpg" big="../../a_res/images/p2.jpg"></a></div></li>
                        <li><div class="tb-pic tb-s40"><a href="javascript:void(0)"><img  src="../../a_res/images/04_small.jpg" mid="../../a_res/images/p3.jpg" big="../../a_res/images/p3.jpg"></a></div></li>
                        <li><div class="tb-pic tb-s40"><a href="javascript:void(0)"><img  src="../../a_res/images/04_small.jpg" mid="../../a_res/images/p4.jpg" big="../../a_res/images/p4.jpg"></a></div></li>
                        <li><div class="tb-pic tb-s40"><a href="javascript:void(0)"><img  src="../../a_res/images/04_small.jpg" mid="../../a_res/images/p5.jpg" big="../../a_res/images/p5.jpg"></a></div></li>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="contact section" id="section-5">
</div>
<div class="footer_bottom">
    <div class="container">
        <p class="copy">Copyright &copy; 2014.developer name J.Z .Contact me <a href="" target="_blank" title="">Wechat</a> - Collect from <a href="" title="" target="_blank"></a></p>
        <ul class="social">
            <li><a href=""> <i class="fb"> </i> </a></li>
            <li><a href=""><i class="rss"> </i> </a></li>
            <li><a href=""><i class="tw"> </i> </a></li>
        </ul>
    </div>
</div>
<script src="../../a_res/js/jquery.scrollTo.js"></script>
<script src="../../a_res/js/jquery.nav.js"></script>
<script>
    $(document).ready(function() {
        $('#nav').onePageNav({
            begin: function() {
                console.log('start')
            },
            end: function() {
                console.log('stop')
            }
        });

        $(".jqzoom").imagezoom();

        $("#thumblist li a").mouseover(function(){
            $(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
            $(".jqzoom").attr('src',$(this).find("img").attr("mid"));
            $(".jqzoom").attr('rel',$(this).find("img").attr("big"));
        });
    });
</script>
</body>
</html>

