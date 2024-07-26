package com.gmarket.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class web_Controller {
	
	PrintWriter pw =null;
	
	
	
	@CrossOrigin(origins="*",allowedHeaders = "*")
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String arr) {
		System.out.println(arr);
		/*
		JSONArray ja2 = new JSONArray();
		ja2.put(new JSONArray(ja.get(0)));
		ja2.put(new JSONArray(ja.get(1)));
		*/
		//System.out.println(ja);
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