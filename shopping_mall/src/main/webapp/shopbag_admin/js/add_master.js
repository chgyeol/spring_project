sessionStorage.setItem("aid_dupl","");
function duplicate_check(){
	var i_aid = master_frm.aid.value;
	if(i_aid==""){
		alert("아이디를 입력하세요.");
	}
	else{
		var http = new XMLHttpRequest();
		var result;
		http.onreadystatechange = function(){
			if(http.readyState ==4 && http.status == 200){
				result= this.response;
				if(result =="yes"){
					alert("사용가능한 아이디 입니다.");
					sessionStorage.setItem("aid_dupl","ok");
				}
				else if(result =="no"){
					alert("이미 가입된 아이디 입니다.");
					sessionStorage.setItem("aid_dupl","no");
				}
				else{
					alert("올바른 값이 전달되지 않았습니다.");
					sessionStorage.setItem("aid_dupl","no");
				}
			}
		}
		http.open("post","./duplicatecheckok.do",true);
		http.setRequestHeader("content-type","application/x-www-form-urlencoded");
		http.send("i_aid="+i_aid);
	}
}

function admin_resist(){
	if(sessionStorage.getItem("aid_dupl")!="ok"){
		alert("사용 가능한 아이디를 입력해주세요.");
	}
	else if(master_frm.aid.value==""||master_frm.apass.value==""){
		alert("아이디와 패스워드는 필수값입니다.");
	}
	else if(master_frm.apass.value!=document.getElementById("apasscon").value){
		alert("패스워드가 일치하지 않습니다.");
	}
	else if(master_frm.aname.value==""||master_frm.aemail.value==""){
		alert("담당자 이름과 이메일은 필수값입니다.");
	}
	else if(!master_frm.aemail.value.includes("@")){
		alert("올바른 형식의 이메일을 작성해주세요.");
	}
	else if(document.getElementById("tel1").value==""||document.getElementById("tel2").value==""||document.getElementById("tel3").value==""){
		alert("휴대폰 번호를 올바르게 작성하세요.");
	}
	else if(master_frm.apart.value==""||master_frm.aposition.value==""){
		alert("담당자 부서와 직책은 필수값입니다.");
	}
	else{
		master_frm.aphone.value=document.getElementById("tel1").value+document.getElementById("tel2").value+document.getElementById("tel3").value;
		sessionStorage.removeItem("aid_dupl");
		master_frm.method="post";
		master_frm.action="./add_master.do";
		master_frm.submit();
	}
}

function admin_goback(){
	if(confirm("등록 취소 하시겠습니까?")){
		sessionStorage.removeItem("aid_dupl");
		location.href="./";
	}
}