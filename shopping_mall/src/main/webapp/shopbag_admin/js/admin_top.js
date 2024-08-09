function go_page(url){
	top_frm.method="get";
	top_frm.action="./gopageok.do";
	//alert(url);
	top_frm.go_lo.value=url;
	top_frm.submit();
}