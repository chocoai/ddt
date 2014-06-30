<xml>
	 <ToUserName><![CDATA[${toUser}]]></ToUserName>
 	 <FromUserName><![CDATA[${fromUser}]]></FromUserName> 
 	 <CreateTime>${createTime}</CreateTime>
	 <MsgType><![CDATA[news]]></MsgType>
	 <ArticleCount><#if items?? && items?size &gt; 0>${items.size}</#if></ArticleCount>
	 <Articles>
	 <#if items?? && items.size &gt; 0>
	 	<#list items as item>
	 		<item>
				 <Title><![CDATA[${item.title}]]></Title> 
				 <Description><![CDATA[${item.description}]]></Description>
				 <PicUrl><![CDATA[${item.picUrl}]]></PicUrl>
				 <Url><![CDATA[${item.url}]]></Url>
			 </item>
	 	</#list>
	 </#if>
	 </Articles>
 </xml>