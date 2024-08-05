<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/add_master.js"></script>
</head>
<body>
    <header class="admin_title_add">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR ADD</p>
    </header>
<form id="master_frm">
    <section class="admin_bgcolor_add">
        <div class="admin_login_add">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" class="add_input1" placeholder="생성할 관리자 아이디를 입력하세요" name="aid">
                <button type="button" onclick="duplicate_check()" class="btn_button">중복체크</button>
                </li>
                <li>
                    <input type="password" class="add_input1" placeholder="접속할 패스워드를 입력하세요" name="apass">
                    <input type="password" class="add_input1" placeholder="동일한 패스워드를 입력하세요" id="apasscon">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" class="add_input1" placeholder="담당자 이름을 입력하세요" name="aname">
                </li>
                <li>
                <input type="text" class="add_input1 emails" placeholder="담당자 이메일을 입력하세요" name="aemail">
                </li>
                <li class="font_color1">
                <input type="text" id="tel1" class="add_input1 hp1" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" id="tel2" class="add_input1 hp2" placeholder="1234" maxlength="4">
                -
                <input type="text" id="tel3" class="add_input1 hp2" placeholder="5678" maxlength="4">
                <input type="hidden" name="aphone">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name="apart">
                        <option value="">담당자 부서를 선택하세요</option>
                        <option value="임원">임원</option>
                        <option value="전산팀">전산팀</option>
                        <option value="디자인팀">디자인팀</option>
                        <option value="CS팀">CS팀</option>
                        <option value="MD팀">MD팀</option>
                    </select>
                    <select class="add_select1" name="aposition">
                        <option value="">담당자 직책을 선택하세요</option>
                        <option value="대표">대표</option>
                        <option value="부장">부장</option>
                        <option value="팀장">팀장</option>
                        <option value="과장">과장</option>
                        <option value="대리">대리</option>
                        <option value="사원">사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            <span class="admin_addbtn">
                <button type="button" onclick="admin_resist()" class="btn_button btncolor1" title="관리자 등록">관리자 등록</button>
                <button type="button" onclick="admin_goback()" class="btn_button btncolor2" title="관리자 취소">등록 취소</button>
            </span>
        </div>
    </section>
    </form>
    <footer class="admin_copy">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
</html>