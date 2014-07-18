<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/fix.css">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/calendar.js"></script>
<script type="text/javascript" src="/js/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/plupload/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="/js/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="/js/upload.js"></script>
<script type="text/javascript" src="/js/rb.js"></script>

</head>
<body>
<div id="wrapper">
    <#include "/common/head.ftl">
    <div id="out-content">
        <div id="content-box">
            <div class="content">
                <div class="form">
                    <form class="fixform" action="/rollbook/save" name="roll_book_form">
                    	<input type="hidden" name="id" id="id" value="<#if rollBook??>${rollBook.id}</#if>">
                    	<input type="hidden" name="groupId" id="groupId" value="<#if rollBook??>${rollBook.groupId}</#if>">
                        <fieldset>
                            <legend>编辑</legend>
                            <p><span>名称：</span><input type="text" name="name" id="name" value="<#if rollBook??>${rollBook.name}</#if>"></p>
                            <p><span>开始时间：</span><input type="text" name="validStartDate" id="start_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validStartTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>结束时间：</span><input type="text" name="validEndDate" id="end_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validEndTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>总人数：</span><input type="text" name="userCount" id="userCount" value="<#if rollBook??>${rollBook.userCount!'0'}</#if>"></p>
                            <div style="display:<#if rollBook?? && rollBook.id &gt; 0>none<#else>block</#if>"> 
	                            <p class="upfile" id="container">
	                                <input type='text' name='textfield' id='textfield' class='txt' />  
	                                <a id="pickfile" href="javascript:void(0)">选择文件</a>
	                            </p>
                            </div>
                            <div class="btns">
                                <p class="save"><a href="javascript:void(0)" class="submit" id="<#if rollBook?? && rollBook.id &gt; 0>save_check<#else>uploadfile</#if>"><span>提交</span></a></p>
                                <p class="close"><a href="/rollbook/template" class="close_btn"><span>模板下载</span></a></p>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <#include "/common/sidebar.ftl">
    </div>
</div>
<div id="footer">
    <#include "/common/footer.ftl">
</div>
</body>
</html>
