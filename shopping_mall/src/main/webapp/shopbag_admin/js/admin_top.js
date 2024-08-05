function go_page(url){
	top_frm.go_lo.value=url;
	top_frm.method="post";
	top_frm.action="./gopageok.do";
	top_frm.submit();
}