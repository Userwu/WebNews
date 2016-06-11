<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header-top" class="header-top">
		<div class="header-top-cup">
			<h1 class="fl-only header-top-cup-logo">
				<a href="index.jsp"><img src="img/logo.png" width="88" height="27"></a>
			</h1>
			<ul class="fl-only header-top-cup-banner">
				<li id="1" ><a href="index.jsp" id="head-banner" >综合</a></li>
				<li id="2"><a href="index.jsp?key=工作" id="head-banner" >工作情感</a></li>
				<li id="3" ><a href="index.jsp?key=动漫" id="head-banner">动漫文化</a></li>
				<li id="4" ><a href="index.jsp?key=漫画" id="head-banner">漫画小说</a></li>
				<li id="5" ><a href="index.jsp?key=游戏" id="head-banner">游戏娱乐</a></li>
			</ul>
			<h1 class="fr-only mr">
				<input class="fl-only search" id="key" onkeydown="entersearch()"/>
				<input type="button" onclick="getListByPage(1)" class="fl-only searchBtn" />
			</h1>
			<div class="clear"></div>
		</div>
	</div>
	<div class="zhanwei"  id="top">
	</div>
</body>
</html>