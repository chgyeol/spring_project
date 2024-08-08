var pageno = ""; // 페이지 번호
var uri=window.location.search;	//웹 URL에 있는 ?에 있는 파라미터 값 가져오기
//console.log(uri);
if(uri==""){ //최초 접속시
	pageno = 1 ;
}
else{	//페이지 번호를 클릭 시
	pageno = uri.split("?page=")[1];	//페이지 번호만 추출하는 코드
}
//console.log(pageno);

function ajax_data(){
	var http;
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http.status==200){
			//여기서 null로 날라오면 신규글이 없다는 조건문과 null이아니면 신규글이 올라왔다는 조건문을 걸어서 서버 트래픽 올라가지않게 해야됨
			html_code(JSON.parse(this.response));
		}
	}
	http.open("GET","./coupon_api.do",true);
	http.send();
}
ajax_data();

//JSON데이터를 HTML로 출력
function html_code(data){
	
	var datano = 2;	// 1페이지당 2개의 데이터
	//pageno: 1번=0,2번=2,3번=4,4번=6
	var startpage=(pageno-1) * datano;	//데이터 배열 시작하는 노드번호
	//console.log(startpg);
	var endpage= datano*pageno; //데이터 배열의 끝나는 노드 번호//페이지에 맞는 노드 번호를 지정
	
	var ea=data.length; // API Data 총 배열 개수
	var result = document.getElementById("list");
	document.getElementById("total").append(ea);
	var pagehtml=document.getElementById("pages");
	
	//페이징 출력
	var pgtotal= Math.ceil(ea/datano); // 소수점 올림하여 페이지 번호를 생성
	//console.log(pgtotal);
	//정통은 스토리지에 담아서 하는거다. 이렇게만 보내면 페이지를 새로 누울 때마다 계속 ajax로 쏴서 트래픽이 증가해서 서버터짐.
	for(var p=1; p<=pgtotal; p++){ //반복문을 이용하여 페이지 번호를 출력하는 코드
		pagehtml.innerHTML +=`<td><a href='./coupon_list.jsp?page=`+p+`'>`+p+`</a></td>`;
	}
	
	//데이터를 출력
	data.forEach(function(a,b,c){ //b:노드번호
		//console.log(b);
		
		if(b >= startpage && b < endpage){	
		result.innerHTML += `
		<tr>
		<td>`+(ea-b)+`</td>
		<td>`+data[b]["cpname"]+`</td>
		<td>`+data[b]["cprate"]+`</td>
		<td>`+data[b]["cpuse"]+`</td>
		<td>`+data[b]["cpdate"]+`</td>
		</tr>
		`;
		}
	});
}