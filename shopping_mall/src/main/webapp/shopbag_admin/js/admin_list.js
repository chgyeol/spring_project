function agree(aid,choose){
	if(choose=="승인"){
		if(confirm("관리자 승인을 진행 하시겠습니까?")){
			list_frm.ause.value="Y";
			list_frm.aid.value=aid;
			list_frm.method="post";
			list_frm.action="./admin_approve.do";
			list_frm.submit();
		}
	}
	else if(choose=="미승인"){
		if(confirm("관리자 승인해제 하시겠습니까?")){
			list_frm.ause.value="N";
			list_frm.aid.value=aid;
			list_frm.method="post";
			list_frm.action="./admin_approve.do";
			list_frm.submit();
		}
	}
	else{
		alert("error");
	}
}