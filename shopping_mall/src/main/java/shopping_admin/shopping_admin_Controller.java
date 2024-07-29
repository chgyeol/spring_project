package shopping_admin;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class shopping_admin_Controller {

	PrintWriter pw = null;
	
	@Resource(name="admincompare")
	private admin_compare ac;
	
	//admin 테이블에서 아이디, 비밀번호 조회
	@PostMapping("/shopbag_admin/admin_main.do")
	public String admin_list(HttpServletResponse res, @ModelAttribute admin_dao dao, HttpSession hs) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		String result = ac.admindata(dao.getAid(),dao.getApass());
		this.pw = res.getWriter();
		if(result=="ok") {
			hs.setAttribute("aid", dao.getAid());
			hs.setMaxInactiveInterval(1800);
		}
		else {
			this.pw.print("<script>"
					+ "alert('아이디 혹은 비밀번호를 확인하세요.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
		return null;
	}
	

}
