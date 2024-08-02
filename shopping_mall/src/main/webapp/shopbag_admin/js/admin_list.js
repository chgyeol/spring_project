function logoutok(){
	if(confirm("로그아웃 하시겠습니까?")){
		banner_frm.method="get";
		banner_frm.action="./admin_logout.do";
		banner_frm.submit();
	}
}