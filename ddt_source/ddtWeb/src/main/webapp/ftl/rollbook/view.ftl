<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/fix.css">
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/calendar.js"></script>
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
                    	<input type="hidden" name="" value="<#if rollBook??>${rollBook.id}</#if>">
                        <fieldset>
                            <legend>编辑</legend>
                            <p><span>名称：</span><input type="text" name="name" id="name" value="<#if rollBook??>${rollBook.name}</#if>"></p>
                            <p><span>开始时间：</span><input type="text" name="validStartDate" id="start_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validStartTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>结束时间：</span><input type="text" name="validEndDate" id="end_datepicker" onClick="WdatePicker()" value="<#if rollBook??>${rollBook.validEndTime?string('yyyy-MM-dd')}</#if>"></p>
                            <p><span>总人数：</span><input type="text" name="userCount" id="userCount" value="<#if rollBook??>${rollBook.userCount!'0'}</#if>"></p>
                            <p><span>文件</span>
                            	<input type='text' name='textfield' id='textfield' class='txt' />  
                            </p>
                            <p> <span></span>
                            	<input type='button' class='btn' value='浏览...' />
 								<input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
 							</p>
                            <div class="btns">
                                <p class="save"><a href="javascript:void(0)" class="submit" id="save_check"><span>提交</span></a></p>
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
<script type="text/javascript">
    (function(){
        $('.query_value').focus(function(){
            $('#for_query').css('display','none');
        }).blur(function(){
            $('#for_query').css('display','block');            
        });

    })(jQuery);
//     (function(){
//         function pagerBar(dataCount,perpageSize,serverUrl,contentPlace,pagerbarPlace,callBack){ 
//             this.dataCount = dataCount; 
//             this.perpageSize = perpageSize; 
//             this.serverUrl = serverUrl; 
//             this.contentPlace = $("#"+contentPlace); 
//             this.pagerbarPlace = $("#"+pagerbarPlace); 
//             this.callBack = callBack; 

//             this.pageCount = 0; 
//             this.pageIndex = 1; 
//             this.curInfo = $("<span/>"); 
//             this.prePage = $("<span/>"); 
//             this.nextPage = $("<span/>"); 
//             this.init(); 
//         } 
//         pagerBar.prototype = { 
//             init : function(){ 
//                 this.getPageCount(); 
//                 this.initLink(); 
//                 this.showBarInfo(); 
//                 if(this.pageCount>0){ 
//                 this.setLink(1); 
//                 } 
//             }, 
//             getPageCount : function(){ 
//                 this.pageCount = Math.ceil(this.dataCount / this.perpageSize); 
                
//             }, 
//             initLink : function(){ 
//                 var self = this; 

//                 this.prePage = $("<span/>").html("上一页").addClass("pageLink"); 
//                 this.prePage.click(function(){ 
//                     self.setLink(self.pageIndex-1); 
//                 }); 
//                 this.nextPage = $("<span/>").html("下一页").addClass("pageLink"); 
//                 this.nextPage.click(function(){ 
//                     self.setLink(self.pageIndex+1); 
//                 }); 
//                 this.pagerbarPlace.append(this.curInfo).append(this.prePage).append(this.nextPage);
//             }, 
//             showBarInfo : function(){ 
//                 this.prePage.hide(); 
//                 this.nextPage.hide(); 

//                 if(this.pageCount==0){ 
//                     this.curInfo.html("暂时没有信息!"); 
//                 } 
//                 else if(this.pageCount==1){ 
//                     this.curInfo.html("1/1"); 
//                 } 
//                 else{ 
//                 this.curInfo.html(this.pageIndex + "/" + this.pageCount); 
//                 } 

//             }, 
//             setLink : function(i){ 
//                 var self = this; 
//                 $.ajax({ 
//                     url:self.serverUrl, 
//                     type:"get", 
//                     data:{
//                         pageSize:self.pageSize,
//                         pageIndex:i
//                     }, 
//                     datatype:'html',
//                     cache:false, 
//                     error:function(){ 
//                         alert("数据加载失败!"); 
//                     }, 
//                     success:function(htmlData){ 
//                         self.contentPlace.html(htmlData); 
//                         if(self.pageCount==1){ 
//                             self.prePage.hide(); 
//                             self.nextPage.hide(); 
//                         }else{ 
//                             if(i==1){ 
//                                 self.prePage.hide(); 
//                                 self.nextPage.show(); 
//                             }else if(i==self.pageCount){ 
//                                 self.prePage.show(); 
//                                 self.nextPage.hide(); 
//                             }else{ 
//                                 self.prePage.show(); 
//                                 self.nextPage.show(); 
//                             } 
//                         } 
//                         self.pageIndex = i; 
//                         self.curInfo.html(self.pageInde+"/"+self.pageCount); 
//                         if(self.callBack){ 
//                             self.callBack(); 
//                         } 
//                     } 
//                 }); 
//             }, 
//             changeServerUrl : function(dataCount,serverUrl){ 
//                 this.dataCount = dataCount; 
//                 this.serverUrl = serverUrl; 
//                 this.pageIndex=1; 

//                 this.getPageCount(); 
//                 this.showBarInfo(); 
//                 this.contentPlace.html(""); 
//                 if(this.pageCount>0){ 
//                     this.setLink(1); 
//                 } 
//             }, 
//             dataCountDec : function(){ 
//                 this.dataCount--; 
//                 this.getPageCount(); 
//                 if(this.pageCount<this.pageIndex){ 
//                     this.pageIndex = this.pageCount; 
//                 } 
//                 if(this.pageIndex>0){ 
//                     this.setLink(this.pageIndex); 
//                 } 
//                 this.showBarInfo(); 
//             } 
//         } 

//         var pageObj = new pagerBar();
//     })(jQuery);
</script>
</body>
</html>
