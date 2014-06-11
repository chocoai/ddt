<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<div id="wrapper">
    <#include "/common/head.ftl">
    <div id="out-content">
        <div id="content-box">
            <div class="content">
            	<div class="querydiv">
                    <span class="new">
                    	<p class="query_btn"><a href="/rollbook/useradd?rid=${rid}"><input type="button" value="新增"></a></p>
                    </span>
                </div>
                <div class="query_res">
                  <input type="hidden" id="cur_page">
                  <input type="hidden" id="show_per_page">
                  <table class="tableData">
                    <thead>
                        <tr class="">
                            <th style="width:10%">微信号</th>
                            <th style="width:16%">用户名</th>
                            <th style="width:16%">手机号</th>
                            <th style="width:11%">创建时间</th>
                            <th style="width:20%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="content_table">
                    <#if users?? && users?size &gt; 0>
                    	<#list users as user>
                    		<tr>
	                            <td>${user.wxName!''}</td>
	                            <td>${user.userName!''}</td>
	                            <td>${user.mobile!''}</td>
	                            <td>${user.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                            <td><a href="/rollbook/userdel?uid=${user.id}&rid=${rid}&page=${page}">删除</a></td>
                        	</tr>
                    	</#list>
                    </#if>
                    </tbody>
                  </table>
                  <div id="page_nav">
                    <a href="">首页</a>
                    <a href="">上一页</a>
                    <a href="" class="page currentPage">1</a>
                    <a href="" class="page">2</a>
                    <a href="">下一页</a>
                    <a href="">末页</a>
                  </div>
                </div>

            </div>
    
        </div>
        <#include "/common/sidebar.ftl">
    </div>
</div>
<div id="footer">
    <#include "/common/footer.ftl">
</div>
<script type="text/javascript">
    (function(){
        $('.query_value').focus(function(){
            $('#for_query').css('display','none');
        }).blur(function(){
            $('#for_query').css('display','block');            
        });

    })(jQuery);
</script>
</body>
</html>
