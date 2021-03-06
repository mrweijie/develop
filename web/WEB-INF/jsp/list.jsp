
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
    <link href="../../a_res/css/magnific-popup.css" rel="stylesheet" type="text/css">
    <script>
        $(document).ready(function() {
            $('.popup-with-zoom-anim').magnificPopup({
                type: 'inline',
                fixedContentPos: false,
                fixedBgPos: true,
                overflowY: 'auto',
                closeBtnInside: true,
                preloader: false,
                midClick: true,
                removalDelay: 300,
                mainClass: 'my-mfp-zoom-in'
            });
        });
    </script>
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
                <li><a href="#section-3">Latest Collection</a></li>
                <li><a href="#section-4">Latest Video</a></li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<div class="wmuSlider example1 section" id="section-1">
</div>
<script src="../../a_res/js/jquery.wmuSlider.js"></script>
<script>
    $('.example1').wmuSlider();
</script>
<div class="main">
    <div class="about section" id="section-2">
        <div class="container">
            <div class="col-md-8 about_left">
                <h3>About Fashion</h3>
                <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet</p>
                <p class="m_2">Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum</p>
            </div>
            <div class="col-md-4 about_right">
                <img src="../../a_res/images/WeChat.jpg" alt=""/>
            </div>
        </div><div class="tlinks">Collect from <a href=""  title=""></a></div>
    </div>
    <div class="portfolio section" id="section-3"  style="margin-top:80px;">
        <ul class="portfolio_grids">
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p1.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                            <div id="small-dialog1" class="mfp-hide">
                                <div class="pop_up1">
                                    <h3>Duis autem vel </h3>
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p2.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p3.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p4.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p5.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <div class="clearfix"></div>
        </ul>
        <ul class="portfolio_grids">
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p6.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p7.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p8.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p9.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="view view-fifth">
                    <img src="../../a_res/images/p10.jpg" class="img-responsive" alt=""/>
                    <div class="mask">
                        <div class="mask1">
                            <h3>Latest</h3>
                            <h4>Fashion</h4>
                            <a class="popup-with-zoom-anim" href="#small-dialog1"> <div class="info">Next</div></a>
                        </div>
                    </div>
                </div>
            </li>
            <div class="clearfix"></div>
        </ul>

    </div>
    <div class="video section" id="section-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6 video_frame">
                </div>
                <div class="col-md-6 about_left">
                    <h4 class="m_3">Watch Fashion Latest</h4>
                    <h5 class="m_4">Videos</h5>
                    <div class="video_btn">
                        <a href="#" class="btn1 btn-1 btn1-1b">More Videos</a>
                    </div>
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
    });
</script>

</body>
</html>
