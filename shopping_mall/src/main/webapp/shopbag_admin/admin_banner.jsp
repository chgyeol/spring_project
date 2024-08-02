<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="headercss">
    <div class="header_div">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <%
        //어드민 사용자 정보(top,bottom)
        HttpSession hs = request.getSession();
        String aidx = String.valueOf(hs.getAttribute("aidx"));
        String aid = (String)hs.getAttribute("aid");
        String aname = (String)hs.getAttribute("aname");
        String amaster = (String)hs.getAttribute("amaster");
        %>
        <%
        if(amaster.equals("Y")){
        %>
        <p>최고관리자 <%=aname%>님 <a href="#">[개인정보 수정]</a> <a onclick="logoutok()">[로그아웃]</a></p>
    	<%
    	}
        else if(amaster.equals("N")){
        %>
        <p><%=aname%> 관리자 <a href="#">[개인정보 수정]</a> <a onclick="logoutok()">[로그아웃]</a></p>
        <%
        }
        else{
        %>
        <p>배너 로딩 오류</p>
        <%
        }
        %>
    </div>
</header>