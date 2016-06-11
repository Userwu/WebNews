<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String ac = (String)request.getParameter("ac");
	if(ac == null || ac.equals("")){
		response.sendRedirect("index.jsp");
	}
	System.out.println("得到的ac号 为"+ac);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章阅读-<%=ac %></title>
<script type="text/javascript" src="jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="deal.js"></script>
<link rel="stylesheet" type="text/css" href="my.css" />
<link type="text/css" rel="stylesheet" href="css/reset.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery_slide.js"></script>
</head>
<body onload="getEssayByAc('<%=ac %>')">
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="content.jsp"></jsp:include>
	<div class="return-btn">
		<a href="#top">^</a>
	</div>
</body>
</html>