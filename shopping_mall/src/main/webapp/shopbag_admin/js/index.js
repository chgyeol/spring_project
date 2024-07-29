function admin_login(){
	if(admin_login_frm.aid.value=="" || admin_login_frm.apass.value==""){
		alert("아이디와 비밀번호를 모두 입력하세요.");
		return false;
	}
	else{
		admin_login_frm.method="post";
		admin_login_frm.action="./admin_main.do";
		admin_login_frm.submit();
	}
}

function add_master_req(){
	location.href="./add_master.jsp";
}

function find_admin_idpw(){
	location.href="";
}