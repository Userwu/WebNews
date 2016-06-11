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
	<div class="mainer">
		<s:iterator value="randomList" var="randomEssay">
			<div class="item">
				<a class="title" href="essayReader.jsp?ac=<s:property value="#randomEssay.ac"/>" target="_blank">
					<s:property value="#randomEssay.title"/> 
				</a>
			</div>
		</s:iterator>
	</div>
	
</body>
</html>