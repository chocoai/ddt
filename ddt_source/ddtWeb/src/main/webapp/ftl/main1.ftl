<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
/*<![CDATA[*/
* {
 margin: 0;
 padding: 0;
}
html, body {
 height: 100%;
 text-align: center;
 font: 12px/1.4 Verdana, sans-serif;
 background: #efefef;
}
#wrapper {
 width: 1200px;
 min-height: 100%;
 margin: auto;
 
}
* html #wrapper {
 height: 100%;
}

.header{width: 1200px;height: 130px;margin: 0 auto;}
.info_bar {
 background: #6c3;
 height: 30px;
}
.exit,.uname{float: right;color: #fff;font-weight: 700;width: 100px;height: 30px;line-height: 30px;margin:0;}
.exit a:link,a:visited{text-decoration: none;color: #fff;}
.logo,.banner{float: left;}
.logo{width: 200px;height: 100px;background: #9c0;}
.banner{width: 1000px;height: 100px;background: #90c;}
#out-content {
 padding-bottom: 50px;
 margin-top: 5px;
}
#out-content:after {
  clear: both;
  display: block;
  font: 1px/0px serif;
  content: ".";
  height: 0;
  visibility: hidden;
}
* html #out-content {
 height: 1%;
}
.leftside {
 float: left;
 width: 196px;
 border-width: 2px 2px 0 2px;border-style:  solid;border-color:  #6c3;
}
.leftul{width: 196px;margin: 0;padding: 0;position: relative;}
.leftul li{list-style:none;width:196px;height: 45px;text-align: center;border-bottom: 2px solid #6c3;}
.leftul a{display: block;text-decoration: none;height: 45px;line-height: 45px;width: 196px;color: #999;}
.leftul li:hover{background: #6c3;}
.leftul a:hover{color: #fff;}
#content-box {
 float: right;
 width: 100%; margin-left: -210px;
}
.content{margin-left: 210px;border: 1px solid #d0d0d0;}
#footer {
 height: 50px;
 background: #6c3;
 width:1200px;
 margin: -50px auto 0;
}
/*]]>*/
</style>
</head>
<body>
<div id="wrapper">
  <div class="header">
        <div class="info_bar">
          <p class="exit"><a href="">退出</a></p>
          <p class="uname">用户名</p>

        </div>
        <div class="logo"></div>
        <div class="banner"></div>
        
      </div>
  <div id="out-content">
    <div id="content-box">
      <div class="content">
      	  <h3>content</h3>
      </div>
    
  </div>
<div class="leftside">
          <ul class="leftul">
            <li>
              <a href="">我的点名册</a>
            </li>
            <li>
              <a href="">我的积分</a>
            </li>
            <li>
              <a href="">我的</a>
            </li>
          </ul>
        </div>
  </div>
</div>
<div id="footer">
  <h3>footer</h3>
</div>
</body>
</html>
