<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String key = (String)request.getParameter("key");
System.out.println("得到的key 为"+key);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<script type="text/javascript" src="jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="deal.js"></script>
<link rel="stylesheet" type="text/css" href="my.css" />
<link type="text/css" rel="stylesheet" href="css/reset.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery_slide.js"></script>
</head>
<body onload="getListByKeyAndPage('<%=key %>',1)">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="mainer">
		<div class="mainer-inner">
			<div class="area">
				<p>搜索结果</p>
				<div class="area-inner" id="searchList">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>