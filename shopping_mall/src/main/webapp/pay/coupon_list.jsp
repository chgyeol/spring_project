<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 페이징 사용법(프론트)</title>
</head>
<body>
<p>쿠폰리스트 총 개수 <span id="total"></span></p> <!-- jstl못 쓰니까 span으로 밖에 못 쓴다 -->
<table border="1">
<thead>
	<tr>
		<th>번호</th>
		<th>쿠폰명</th>
		<th>할인율</th>
		<th>사용 유/무</th>
		<th>만료기한</th>
	</tr>
</thead>
<tbody id="list">

</tbody>
</table>
<!-- 페이지 번호 출력 -->
<table border="1">
<tr id="pages">

</tr>
</table>
</body>
<%
Date today =new Date(); //util
%>
<script src="./coupon_list.js?v=<%=today%>"></script>
</html>