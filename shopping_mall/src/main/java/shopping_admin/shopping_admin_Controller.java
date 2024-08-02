package shopping_admin;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class shopping_admin_Controller {

	PrintWriter pw = null;
	
	@Resource(name="admincompare")
	private admin_compare ac;
	
	
	//admin 로그아웃
	@GetMapping("/shopbag_admin/admin_logout.do")
	public String admin_logout() {
		System.out.println("logouttest");
		return "./index";
	}
	
	//admin 테이블에서 아이디, 비밀번호 조회
	@PostMapping("/shopbag_admin/admin_main.do")
	public String admin_list(HttpServletResponse res, @RequestParam("aid") String aid, @RequestParam("apass") String apass, HttpSession hs) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		String result = ac.admindata(aid, apass);
		this.pw = res.getWriter();
		if(result=="ok") {
			hs.setAttribute("aidx", ac.allm.get("aidx"));
			//System.out.println(ac.allm.get("aidx"));
			hs.setAttribute("aid", ac.allm.get("aid"));
			hs.setAttribute("aname", ac.allm.get("aname"));
			hs.setAttribute("amaster", ac.allm.get("amaster"));
			hs.setMaxInactiveInterval(1800);
		}
		else if(result=="error") {
			this.pw.print("<script>"
					+ "alert('DB에서 오류가 발생했습니다.');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
		}
		else {
			this.pw.print("<script>"
					+ "alert('아이디 혹은 비밀번호를 확인하세요.');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
		}
		return "./admin_list";
	}
	

}
