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
                    <form class="search_form" method="post" action="#"> 
                        <p class="input">
                          <input type="text" name="query" class="query_value" /> 
                          <label for="query" id="for_query" class="keyword">查询条件</label>
                        </p>
                        <p class="query_btn">
                          <input type="submit" id="go" alt="Search" title="Search" value="查询"/>
                        </p>
                        <span class="new"></span> 
                    </form> 
                    <div class="newfile"><a href="/rollbook/new">新增</a></div>
                </div>
                <div class="query_res">
                  <input type="hidden" id="cur_page">
                  <input type="hidden" id="show_per_page">
                  <table class="tableData">
                    <thead>
                        <tr class="">
                            <th style="width:10%">名称</th>
                            <th style="width:16%">开始时间</th>
                            <th style="width:16%">结束时间</th>
                            <th style="width:11%">总人数</th>
                            <th style="width:20%" colspan=3>操作</th>
                        </tr>
                    </thead>
                    <tbody id="content_table">
                    <#if rollBooks?? && rollBooks?size &gt; 0>
                    	<#list rollBooks as rollBook>
                    		<tr>
	                            <td>${rollBook.name!''}</td>
	                            <td>${rollBook.validStartTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                            <td>${rollBook.validEndTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	                            <td>${rollBook.userCount!'0'}</td>
	                            <td><a href="/rollbook/userlist?rid=${rollBook.id}">名单</a></td>
	                            <td><a href="/rollbook/view?rid=${rollBook.id}">查看</a></td>
	                            <td><a href="/rollbook/rolllist?rid=${rollBook.id}">点名历史</a></td>
	                            <td><a href="/rollbook/del?rid=${rollBook.id}&page=${page}">删除</a></td>
                        	</tr>
                    	</#list>
                    </#if>
                        <tr>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                        </tr>
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
<script type="text/javascript" src="./jQuery.js"></script>
<script type="text/javascript">
    (function(){
        $('.query_value').focus(function(){
            $('#for_query').css('display','none');
        }).blur(function(){
            $('#for_query').css('display','block');            
        });

    })(jQuery);
// </script>
// <script type="text/javascript">
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
// </script>
</body>
</html>
