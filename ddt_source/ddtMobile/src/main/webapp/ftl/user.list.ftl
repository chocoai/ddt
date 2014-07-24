<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览；-->
<meta content="width=320px, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<!--iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览；-->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!--iphone设备中的safari私有meta标签，它指定的iphone中safari顶端的状态条的样式；-->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!--告诉设备忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection" />
<link type="text/css" rel="stylesheet" href="/css/main.css"  />
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function showDiv(uid) {
		$("#reason_" + uid).css("display", "block");
		$("#remark_" + uid).css("display", "none");
	}
	
	function cancelDiv(uid) {
		$("#reason_" + uid).css("display", "none");
		$("#remark_" + uid).css("display", "block");
	}
	
	function remark(uid, rid) {
		var reasonVal = $("#selectValue_" + uid).val();
		$.get("remark",{uid:uid, rid:rid, reason:reasonVal}, function(data){
			alert(data.result);
		},"json");
	}
</script>
</head>
<body>
<div class="wrap">
	<div class="header">
		<div class="banner"></div>
	</div>
	<div class="main">
			
			<div class="pointswrap">

				<div class="leftpoint">
					<p>点名册用户列表,共${count}人， 其中${unrolledCount}未参与点名</p>
				</div>
			</div>	
			<div class="goodslist">
				<table class="goodstable">
					<#if users?? && users?size &gt; 0>
						<#list users as user>
							<tr>
	                            <td>${user.username!''}</td>
	                            <td>${user.mobile!''}</td>
	                            <td><#if user.rollTime??>${user.rollTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
	                            <td>${user.distance}</td>
	                            <td>${user.info!''}</td>
	                            <td>
	                            	<div style="display:none" id="reason_${user.userId}">
	                            		<select id="selectValue_${user.userId}">
	                            			<option value="事假">事假</option>
	                            			<option value="病假">病假</option>
	                            			<option value="未带手机">未带手机</option>
	                            		</select>
	                            		<a href="javascript:remark(${user.userId},${user.rollBookInfoId})">确定</a>
	                            		<a href="javascript:cancelDiv(${user.userId})">取消</a>
	                            	</div>
	                            	<div id="remark_${user.userId}">
	                            		<a href="javascript:showDiv(${user.userId})">备注</a>
	                            	</div>
	                            </td>
                        	</tr>
						</#list>
					</#if>
				</table>
			</div>
	</div>
	<div class="footer">
		<#include "/common/footer.ftl">
	</div>
</div>
</body>
</html>