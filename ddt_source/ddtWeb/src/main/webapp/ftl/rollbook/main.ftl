<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/pager.ftl">
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
                    <form class="search_form" method="post" action="/rollbook/list"> 
                        <p class="input">
                          <input type="text" name="query" class="query_value" value="<#if query??>${query!''}</#if>"/> 
                          <label for="query" id="for_query" class="keyword">查询条件</label>
                        </p>
                        <p class="query_btn">
                          <input type="submit" class="go" alt="Search" title="Search" value="查询"/>
                        </p>
                    </form>
                    <span class="new">
                    	<p class="query_btn"><input type="button" value="新增" onclick="window.location.href='/rollbook/view'" class="go"></p>
                    </span>
                </div>
                <div class="query_res">
                  <input type="hidden" id="cur_page">
                  <input type="hidden" id="show_per_page">
                  <table class="tableData">
                    <tr class="">
                        <th style="width:10%">名称</th>
                        <th style="width:16%">开始时间</th>
                        <th style="width:16%">结束时间</th>
                        <th style="width:11%">总人数</th>
                        <th style="width:20%" colspan=5>操作</th>
                    </tr>
                    <#if rollBooks?? && rollBooks?size &gt; 0>
                    	<#list rollBooks as rollBook>
                    		<tr>
	                            <td>${rollBook.name!''}</td>
	                            <td>${rollBook.validStartTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                            <td>${rollBook.validEndTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                            <td>${rollBook.userCount!'0'}</td>
	                            <td><a href="/rollbook/userlist?rid=${rollBook.id}">名单</a></td>
	                            <td><a href="/rollbook/view?rid=${rollBook.id}" style="font:">查看</a></td>
	                            <td><a href="/rollbook/roll?rid=${rollBook.id}">点名</a></td>
	                            <td><a href="/rollbook/rolllist?rid=${rollBook.id}">点名历史</a></td>
	                            <td><a href="/rollbook/del?rid=${rollBook.id}&page=${page}">删除</a></td>
                        	</tr>
                    	</#list>
                    </#if>
                  </table>
                  <@lpager total=totalPage index=page url=pageUrl />
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
