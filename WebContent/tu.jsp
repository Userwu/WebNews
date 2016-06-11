<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/reset.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery_slide.js"></script>
</head>

<body>

	
	<a href="javascript:void(0)" tip="0" class="i_btn prev_L"></a>
	<a href="javascript:void(0)" tip="1" class="i_btn next_R"></a>
	
	<ul class="slide_img">
		<% int i = 0; %>
		<s:iterator value="randomList" var="essayrandom">
			<li <%if(i==0){%> class=\"on\" <%} i++;%>>
				<a href="essayReader.jsp?ac=<s:property value="#essayrandom.ac"/>" target="_blank"><s:property value="#essayrandom.firstImgUrl" escape="false"/> </a>
				<div class="info">
					<span><s:property value="#essayrandom.title"/> </span>
					<p>作者：<s:property value="#essayrandom.author"/></p>
					<p><s:property value="#essayrandom.time"/></p>
				</div>
			</li>
		</s:iterator>
	</ul>

<script type="text/javascript">
$(function(){

	var newopt={
		w2:"120",
		h2:"180"
	};
	
	i_slide($(".container_image"),newopt);
	
});
</script>
</body>
</html>
