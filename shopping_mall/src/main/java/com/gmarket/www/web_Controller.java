package com.gmarket.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

//md5 : 회원가입, 로그인, 패스워드 변경, 1:1문의, 자유게시판, 상품구매
@Controller
public class web_Controller extends md5_pass {
	
	PrintWriter pw =null;

	
	
	//DAO (@ModelAttribute), DAO 없이 : 자료형 객체 or @RequestParam
	@Resource(name="userselect")
	private user_select us;
	
	@PostMapping("/idsearch.do")
	public String idsearch(String[] uname, String uemail, HttpServletResponse res) throws Exception{	//아이디 찾기
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			if(uname[0] == null || uemail == null) {
				this.pw.print("<script>"
						+ "alert('올바른 접근 방식이 아닙니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}
			else {
				ArrayList<Object> onedata = us.search_id(uname[0], uemail);
				
			}
		} 
		catch (Exception e) {
			System.out.println(e);
			this.pw.print("<script>"
					+ "alert('Database 문제로 인하여 해당 정보가 확인 되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		finally {
			this.pw.close();
		}
		return null;
	}
	
	@PostMapping("/passmodify.do")
	public String passmodify() {	//패스워드 변경
		return null;
	}
	
	
	//@Resource(name="md5pass")
	//private md5_pass md;
	
	//패스워드 변경 여부를 체크(MD5)
	@GetMapping("/passwd.do")
	public String passwd() {
		String pwd = "a1234";
		String result = this.md5_making(pwd);
		System.out.println(result);
		
		return null;
	}
	
	//숙제
	@PostMapping("/ajaxok4.do")
	public String ajaxok4(@RequestBody String arr, HttpServletResponse res) throws Exception{
		//System.out.println(arr);
		JSONArray ja = new JSONArray(arr);
		//System.out.println(ja);
		JSONObject jo = new JSONObject(ja.get(0).toString());
		JSONObject jo2 = new JSONObject(ja.get(1).toString());
		JSONObject jo3 = new JSONObject(ja.get(2).toString());
		System.out.println(jo3.get("seq"));
		this.pw = res.getWriter();
		this.pw.print("ok");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/restapi.do")
	//@SessionAttribute : session이 이미 등록 되어 있는 상황일 경우 해당 정보를 가져올 수 있음
	public String restapi(@SessionAttribute(name="mid", required = false) String mid) throws Exception {
		if(mid==null) {
			System.out.println("로그인 해야만 결제 내역을 확인하실 수 있습니다.");
		}
		else {
			System.out.println("결제내역은 다음과 같습니다.");
		}
		return null;
	}
	
	//session을 쓰는 다른 방법
	//HttpSession : 해당 interface를 활용하여 세션을 빠르게 구현하는 방식
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam(value="",required = false) String mid, HttpSession session) {
		if(mid!=null || mid!="") {
			session.setAttribute("mid", mid);
			session.setMaxInactiveInterval(1800);
		}
		return null;
	}
	
	/*
	@PostMapping("/loginok.do")
	public String loginok(String mid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800);	//이거 안 쓰면 바로 로그아웃 됨
		return null;
	}
	*/
	
	@CrossOrigin(origins="*",allowedHeaders = "*")
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String arr, HttpServletResponse res) throws Exception{
		System.out.println(arr);
		String arr2 = arr.substring(1, arr.length()-1);
		System.out.println(arr2);
		JSONArray ja = new JSONArray(arr2);
		JSONArray ja2 = new JSONArray(ja.get(0).toString());
		JSONArray ja3 = new JSONArray(ja.get(1).toString());
		System.out.println(ja2);
		System.out.println(ja3);
		this.pw = res.getWriter();
		this.pw.print("ok");
		this.pw.close();
		return null;
	}
	
	//@RequestBody : JSON.stringfy
	@CrossOrigin(origins="*",allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data, HttpServletResponse res) throws Exception {
		System.out.println(all_data);	//{"all_data":[]}
		JSONObject jo = new JSONObject(all_data);	//{} 인식 시킨 후 key값으로 배열 체크
		JSONArray ja = new JSONArray(jo.get("all_data").toString());
		System.out.println(ja.get(0));
		JSONArray ja2 = (JSONArray)jo.get("all_data");
		System.out.println(ja2.get(0));
		
		//Front가 datatype을 JSON으로 받겠다고 해서 JSON으로 날려줌
		JSONObject result = new JSONObject();
		result.put("result", "ok");
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();
		return null;
	}
	
	/*
	@RequestBody : GET/POST(X) JSON기반일 경우에만 사용한다.(무조건 POST)
	@ResponseBody : 미디어타입, 파라미터 타입 단, 인자값에 미선언함(ex. public @ResponseBody String ajaxok() 이렇게는 가능함)
	*/
	//ajax 통신 CORS 방식
	@CrossOrigin(origins="*",allowedHeaders = "*")
	//@RequestParam (GET:문자,배열 , POST:문자)
	//배열을 이용하여 대표키로 전달 또는 대표키 없이 보조키로 전달 될 경우 사용할 수 있음.
	@GetMapping("/ajaxok.do")//(value="/ajaxok.do",produces="application/json")//이게 중요한게 아니다. 이거 굳이 안 써도 된다.
	public String ajaxok(@RequestParam(value="all_data") List<String> alldata, //value값이 key이름이기때문에 프론트 data 대표키랑 맞춰야된다.
		HttpServletResponse res) throws Exception {
		System.out.println(alldata);
		System.out.println(alldata.get(0));
		this.pw=res.getWriter();
		JSONObject jo= new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		this.pw.close();
		
		return null;
	}
	
}