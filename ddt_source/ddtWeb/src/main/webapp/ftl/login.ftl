<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
<link type="text/css" rel="stylesheet" href="/css/login.css"  />
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/comm.js"></script>
<script type="text/javascript">
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
<body>
	<div class="wrapper">
		<div class="logo"><img src="/images/logo.jpg" height="100%"></div>
        <div class="bannerwrap">
            <div class="banner"></div>
        </div>
		<div class="main">
			<div class="loginbox">
				<form name="lgnForm" class="lgnForm" id="lgnForm">
		            <fieldset>
		                <legend>登录</legend>
		                <p><input type="text" name="username" id="uname" class="inputTxt" placeholder="用户名"></p>
		                <p><input type="password" name="userpass" id="upwd" class="inputTxt" placeholder="密码"></p>
		                <p><input type="button" class="inputBtn" value="登录" id="btn-login"></p>
		            </fieldset>
        		</form>
    		</div>
		</div>
		<div id="footer">
		     <#include "/common/footer.ftl">
		</div>
	</div>
	
</body>

</html>