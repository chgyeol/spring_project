<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./js/admin_top.js"></script>
<form id="top_frm">
<nav class="navcss">
    <div class="nav_div">
        <ol>
        <% 
        //amaster : admin_banner에서 갖고옴
        if(amaster.equals("Y")){ 
        %>
            <li title="쇼핑몰 관리자 리스트" onclick="go_page('관리자 리스트')">쇼핑몰 관리자 리스트</li>
        <%} %>
            <li title="쇼핑몰 회원관리" onclick="go_page('회원관리')">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리" onclick="go_page('상품관리')">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정" onclick="go_page('기본설정')">쇼핑몰 기본설정</li>
            <li title="쇼핑몰 공지사항" onclick="go_page('공지사항')">쇼핑몰 공지사항</li>
        </ol>
        	<input type="hidden" name="go_lo">
    </div>

</nav>
</form>