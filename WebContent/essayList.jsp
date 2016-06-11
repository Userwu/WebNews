<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.opensymphony.xwork2.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="deal.js"></script>
<link rel="stylesheet" type="text/css" href="my.css" />
</head>
<body>
	<div class="mainer">
		<s:iterator value="essays" var="essay">
			<div class="essay-item">
				<div class="item-logo">
					<span class="a">^</span> <span class="b">AC</span>
				</div>
				<a href="essayReader.jsp?ac=<s:property value="#essay.ac"/>" target="_blank"
					title="发布于 2016-06-09 15:59:43" class="title"><s:property
						value="#essay.title" /></a>
				<p class="article-info">
					<a class="name"><s:property
							value="#essay.author" /></a> "&nbsp;/&nbsp;
					<s:property value="#essay.time" />
					&nbsp;/&nbsp;"
				</p>
				<div class="desc">
					文章号：
					<s:property value="#essay.ac" />
				</div>
			</div>
		</s:iterator>
		<div class="area-page">
			<%
				ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
				int lastPage = (int) vs.findValue("lastPage");
				int pnum = Integer.valueOf((String) vs.findValue("page"));
				String type1 = (String) vs.findValue("type1");
				String key = (String) vs.findValue("key");
				System.out.println("===============type1=" + type1);
				System.out.println("===============lastpage="+lastPage);
				System.out.println("===============key="+key);
				if (pnum != 1) {
			%>
			<a href="#top" class="pager"
				onclick="<% if(key==null)
						out.print("getListByTypeAndPage('" + type1 + "'," + 1 + ")");
					else
						out.print("getListByKeyAndPage('"+key+"',"+1+")");
						%>">首页</a>
			<%
				}
				for (int i = (pnum - 4<1?1:pnum-4); i <= (pnum + 4 > lastPage?lastPage:pnum+4) ; i++) {
			%>
			<a href="#top" class="pager <% if(i == pnum) out.print("active"); %>"
				onclick="<%
						if(key==null)
							out.print("getListByTypeAndPage('" + type1 + "'," + i + ")");
						else
							out.print("getListByKeyAndPage('"+key+"',"+i+")");
						%>">
				<%
					out.print(i);
				%>
			</a>
			<%
				}
				if (pnum != lastPage) {
			%>
			<a href="#top" class="pager"
				onclick="<%
						if(key==null)
							out.print("getListByTypeAndPage('" + type1 + "'," + lastPage + ")");
						else
							out.print("getListByKeyAndPage('"+key+"',"+lastPage+")");	
							%>">
				<%
					out.print("尾页");
				%>
			</a>
			<%
				}
			%>
		</div>
	</div>



</body>
</html>