function option_save(){
	var spb = siteinfo_frm.spbank;
	var spa = siteinfo_frm.spacount;
	if((spb!="" && spa=="") || (spb=="" && spa!="")){
		alert("계좌 정보를 입력할 경우 은행명과 계좌번호는 필수입니다.");
	}
	else if(siteinfo_frm.sppointmax<=siteinfo_frm.sppointmin){
		alert("결제 최대 포인트는 결제 최소 포인트보다 커야 합니다.");
	}
	else{
		siteinfo_frm.method="post";
		siteinfo_frm.action="./admin_siteinfo.do";
		siteinfo_frm.submit();
	}
}

function not_save(){
	if(confirm("변경 사항을 저장하지 않고 벗어나시겠습니까?")){
		location.reload();
	}
}