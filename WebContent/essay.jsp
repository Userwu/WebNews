<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p id="title_1">
		<span class="txt-title-view_1"><s:property value="essay.title" /></span> 
		<span id="txt-info-title_1" class="txt-info-title_1"><span>作者:</span><span
			class="pts"><s:property value="essay.author"/> </span>&nbsp;/&nbsp;&nbsp;<span
			class="time"><s:property value="essay.time"/></span>&nbsp;/&nbsp;&nbsp;</span>
	</p>
	<div class="essay-area">
		<s:property value="essay.content" escape="false"/>
	</div>
</body>
</html>