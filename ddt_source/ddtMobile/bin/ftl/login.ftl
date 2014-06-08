<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>点点通</title>
<link type="text/css" rel="stylesheet" href="style/style.css"  />
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/comm.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/suggest.js"></script>
<script type="text/javascript">
	$(function(){
		indexLoad();
	})
	
	document.onkeydown = function(e) {  
	    // 兼容FF和IE和Opera  
	    var theEvent = e || window.event;  
	    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
	    if (code == 13) {
	    	$("#btn-login").focus(); 
	        $("#btn-login").click();
	    }
	}
</script>
</head>
<body style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
<div class="header-login">
	<#include "/common/header.ftl">
</div>
<div class="banner">
	<div class="max-width">
    	<img class="banner-img" src="images/banner_01.png" />
    	<div class="login-box">
        	<div class="dt">欢迎您登录</div>
        	<form action="login" method="post" name="login_form">
        		<div class="error" id="error_log" style="display:none"></div>
        		<div class="dd" id="useremail"><label for="username">账号：</label><input type="text" name="username" id="username" /></div>
            	<div class="dd"><label for="password">密码：</label><input type="password" name="password" id="password" /></div>
            	<div class="dd"><label for="imgCode">验证码：</label><input type="text" name="imgCode" id="imgCode" /><img src="image-view" alt="VerCode" width="78px" height="34px" onClick="GetImgCode();" id="image-view" style="cursor:pointer"/><a class="fac" href="javascript:GetImgCode();">换一张</a></div>
            </form>
            <div class="dd"><a class="btn-login" id="btn-login" href="javascript:void(0)">立即登录</a></div>
            <div class="dd rr"><a href="#">忘记密码？</a> | <a href="register">注册账号</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
//账户输入建议
var email_str=["@163.com","@qq.com","@sina.com","@126.com","@vip.qq.com","@yahoo.com","@gmail.com","@sohu.com","@yeah.net"];
new suggest(email_str,"useremail","username");
</script>
<div class="read max-width">
	<div class="groups">
    	<div class="groups-title"><span class="icon groups safe"></span>可靠保障</div>
        <div class="groups-content">网络安全是指网络系统的硬件、软件及其系统中的数据受到保护，不因偶然的或者恶意的原因而遭受到破坏、更改、泄露，系统连续可靠正常地运行，网络服务不中断。 网络安全从其本质上来讲就是网络上的信息安全。</div>
    </div>
    <div class="line">&nbsp;</div>
    <div class="groups">
    	<div class="groups-title"><span class="icon groups serve"></span>可靠保障</div>
        <div class="groups-content">网络安全是指网络系统的硬件、软件及其系统中的数据受到保护，不因偶然的或者恶意的原因而遭受到破坏、更改、泄露，系统连续可靠正常地运行，网络服务不中断。 网络安全从其本质上来讲就是网络上的信息安全。</div>
    </div>
    <div class="line">&nbsp;</div>
    <div class="groups">
    	<div class="groups-title"><span class="icon groups team"></span>过硬资质</div>
        <div class="groups-content">网络安全是指网络系统的硬件、软件及其系统中的数据受到保护，不因偶然的或者恶意的原因而遭受到破坏、更改、泄露，系统连续可靠正常地运行，网络服务不中断。 网络安全从其本质上来讲就是网络上的信息安全。</div>
    </div>
    <div class="line">&nbsp;</div>
    <div class="groups">
    	<div class="groups-title"><span class="icon groups mail"></span>联系我们</div>
        <div class="groups-content">网络安全是指网络系统的硬件、软件及其系统中的数据受到保护，不因偶然的或者恶意的原因而遭受到破坏、更改、泄露，系统连续可靠正常地运行，网络服务不中断。 网络安全从其本质上来讲就是网络上的信息安全。</div>
    </div>
    <div class="clear"></div>
</div>
<div class="footer">
	版权所有 XXXXXXX 备案号：粤ICP备11057646-3
</div>
</body>
</html>
