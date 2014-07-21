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
</head>
<body>
<div class="wrap">
	<div class="header">
		<div class="banner"></div>
	</div>
	<div class="main">
			
			<div class="pointswrap">

				<div class="leftpoint">
					<p>点名册列表</p>
				</div>
			</div>	
			<div class="goodslist">
				<table class="goodstable">
					<#if rollBooks?? && rollBooks?size &gt; 0>
						<#list rollBooks as rollBook>
							<tr>
								<td class="goodinfo">${rollBook.name}</td>
								<td class="goodinfo"><a href="/rollbook/start?wx=${wx}&rid=${rollBook.id}">开始点名</a></td>
								<td class="goodinfo"><a href="/rollbook/rolllist?wx=${wx}&rid=${rollBook.id}">点名记录</a></td>
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