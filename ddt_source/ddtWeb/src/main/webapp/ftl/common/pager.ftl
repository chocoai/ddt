<#function supply url="">
	<#if url?contains("?")>
		<#return url + '&'/>
	<#else>
		<#return url + '?'/>
	</#if>
</#function>
<#function getRange total index>
	<#if total&lt;10>
		<#if total&lt;3><#return []/><#else><#return [2,total-1]/></#if>
	</#if>
	<#local start=index-3/>
	<#local end=index+3/>
	<#if start&lt;2>
		<#local end=end+2-start/>
		<#local start=2/>
	</#if>
	<#if end&gt;total-1>
		<#local start=start-(end-(total-1))/>
		<#local end=total-1/>
	</#if>
	<#return [start, end]/>
</#function>
<#macro lpager total index=1 url="">
  <#if total??&&total&gt;1>
  <div id="page_nav">
  	<#if index==1>
	  	<a href="javascript:void(0)">&laquo;</a>
        <a class="page currentPage"  href="#">1</a>
  	<#else>
  	    <a href="${supply(url)}page=${index-1}">&laquo;</a>
        <a href="${supply(url)}page=1">1</a>
  	</#if>
  	<#local range=getRange(total,index)/>
  	<#if range?size&gt;0>
	  	<#local start=range[0]/>
	  	<#local end = range[1]/>
	  	<#if start&gt;2><a class="page" href="#">...</a></#if>
	  	<#list start..end as x>
	  		<#if index==x>
	  			<a class="page currentPage" href="#">${x}</a>
	  		<#else>
	  		    <a class="page currentPage" href="${supply(url)}page=${x}">${x}</a>
	  		</#if>
	  	</#list>
	  	<#if end&lt;total-1><a class="page" href="#">...</a></#if>
	</#if>
	<#if index==total>
	  	<a class="page currentPage" href="#">${total}</a>
        <a class="page" href="#">&raquo;</a>
  	<#else>
  		<a href="${supply(url)}page=${total}">${total}</a>
        <a href="${supply(url)}page=${index+1}">&raquo;</a>
  	</#if>
  </div>
  </#if>
</#macro>