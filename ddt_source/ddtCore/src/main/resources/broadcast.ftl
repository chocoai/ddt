{ "touser": 
	[
		<#if openIdList?? && openIdList?size &gt; 0>
			<#list openIdList as openId>
				${openId!''}
				<#if openId_index + 1 &lt; openIdList?size>
					,
				</#if>
			</#list>
		</#if>
	], "msgtype": "text", "text": { "content": "${content!''}"}

}