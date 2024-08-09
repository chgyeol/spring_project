<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/admin_list.js?v=2"></script>
</head>
<body>
<%@ include file="/shopbag_admin/admin_banner.jsp" %>
<%@ include file="/shopbag_admin/admin_top.jsp" %>
<%
if(!amaster.equals("Y")){
%>
<script>
alert("잘못된 접근입니다.");
history.go(-1);
</script>
<%
}
%>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
	<cr:if test="${ctn==0}">
	    <ol class="new_admin_none">
	        <li>신규 등록된 관리자가 없습니다.</li>
	    </ol>
	</cr:if>
	<cr:if test="${ctn>0}">
	    <cr:forEach var="admin" items="${lists}" varStatus="aidx">
			<ol class="new_admin_lists2" id="${adix.index}">
		        <li>${ctn-aidx.index}</li>
		        <li>${admin.getAname()}</li>
		        <li>${admin.getAid()}</li>
		        <li>${admin.getAphone()}</li>
		        <li>${admin.getAemail()}</li>
		        <li>${admin.getApart()}</li>
		        <li>${admin.getAposition()}</li>
		        <li>${admin.getAindate().substring(0,10)}</li>
		        <li>
		        <cr:if test="${admin.getAuse()=='N'}">
		            <input type="button" value="승인" class="new_addbtn1" title="승인" onclick="agree('${admin.getAid()}',this.value)">
		        </cr:if>
		        <cr:if test="${admin.getAuse()=='Y'}">
		            <input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="agree('${admin.getAid()}',this.value)">
		        </cr:if>
		        </li>
		    </ol>
    	</cr:forEach>
	</cr:if>
</section>
</main>
<form id="list_frm">
<input type="hidden" name="ause">
<input type="hidden" name="aid">
</form>
<%@ include file="/shopbag_admin/admin_bottom.jsp" %>
</body>
</html>