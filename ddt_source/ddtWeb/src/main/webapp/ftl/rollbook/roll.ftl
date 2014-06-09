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
                    <form class="fixform" action="/rollbook/saveinfo" name="roll_book_info_form">
                    	<input type="hidden" name="id" value="<#if rollBook??>${rollBook.id}</#if>">
                    	<input type="hidden" name="groupId" id="groupId" value="<#if rollBook??>${rollBook.groupId}</#if>">
                        <fieldset>
                            <legend>编辑</legend>
                            <p><span>名称：</span><input type="text" name="name" id="name" readonly value="<#if rollBook??>${rollBook.name}</#if>"></p>
                            <p><span>开始时间：</span><input type="text" name="validStartDate" readonly id="start_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validStartTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>结束时间：</span><input type="text" name="validEndDate" readonly id="end_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validEndTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>点名开始时间：</span><input type="text" name="rollStartTime" id="start_roll_datepicker" onClick="WdatePicker()" value="<#if rollBook?? && rollBook.rollStartTime??>${rollBook.rollStartTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>点名结束时间：</span><input type="text" name="rollEndTime" id="end_roll_datepicker" onClick="WdatePicker()" value="<#if rollBook?? && rollBook.rollEndTime??>${rollBook.rollEndTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>随机码：</span><input type="text" name="rollCode" id="rollCode" value="<#if rollBook??>${rollBook.rollCode!''}</#if>"></p>
                            <p><span>总人数：</span><input type="text" name="userCount" readonly id="userCount" value="<#if rollBook??>${rollBook.userCount!'0'}</#if>"></p>
                            <div class="btns">
                                <p class="save"><a href="javascript:void(0)" class="submit" id="save_info_check"><span>提交</span></a></p>
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
