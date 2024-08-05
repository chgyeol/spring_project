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
	
	
	//admin 메뉴 이동
	@PostMapping("/shopbag_admin/gopageok.do")
	public String gopageok(@RequestParam("go_lo") String go_lo, HttpServletResponse res) throws Exception{
		String lc = "";
		//System.out.println(go_lo);
		switch (go_lo) {
		case "관리자 리스트":
			lc="/admin_list";
			break;
		case "회원관리":
			lc="/shop_member_list";
			break;
		case "상품관리":
			lc="/product_list";
			break;
		case "기본설정":
			lc="/admin_siteinfo";
			break;
		case "공지사항":
			lc="/notice_list";
			break;

		default:
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('오류가 발생했습니다.');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
			break;
		}
		return lc;
	}
	
	//admin 계정 등록 요청
	@PostMapping("/shopbag_admin/add_master.do")
	public void add_master(@ModelAttribute admin_dao dao, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		String s_apass = ac.adminpw(dao.getApass());
		dao.setApass(s_apass);
		try {
			int result = ac.add_master_insert(dao);
			if(result>0) {
				this.pw.write("<script>"
					+ "alert('정상적으로 계정 등록 요청이 완료 되었습니다.');"
					+ "location.href='./';"
					+ "</script>");
			}
		} 
		catch (Exception e) {
			System.out.println(e);
			this.pw.write("<script>"
				+ "alert('DB 오류로 인하여 요청이 완료되지 않았습니다.');"
				+ "history.go(-1);"
				+ "</script>");
		}
		finally {
			this.pw.close();
		}
	}
	
	//admin 아이디 중복확인
	@PostMapping("/shopbag_admin/duplicatecheckok.do")
	public void duplicatecheckok(@RequestParam("i_aid") String i_aid, HttpServletResponse res) throws Exception{
		String result = ac.admin_id_dupl(i_aid);
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();
	}
	
	//admin 로그아웃
	@GetMapping("/shopbag_admin/admin_logout.do")
	public void admin_logout(HttpSession hs, HttpServletResponse res) throws Exception{
		hs.removeAttribute("aidx");
		hs.removeAttribute("aid");
		hs.removeAttribute("aname");
		hs.removeAttribute("amaster");
		this.pw = res.getWriter();
		this.pw.write("<script>"
				+ "location.href='./';"
				+ "</script>");
		this.pw.close();
	}
	
	//admin 테이블에서 아이디, 비밀번호 조회
	@PostMapping("/shopbag_admin/admin_main.do")
	public String admin_list(HttpServletResponse res, @RequestParam("aid") String aid, @RequestParam("apass") String apass, HttpSession hs) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			String result = ac.admindata(aid, apass);
			if(result=="ok") {
				if(ac.allm.get("ause").equals("Y")) {
					hs.setAttribute("aidx", ac.allm.get("aidx"));
					//System.out.println(ac.allm.get("aidx"));
					hs.setAttribute("aid", ac.allm.get("aid"));
					hs.setAttribute("aname", ac.allm.get("aname"));
					hs.setAttribute("ause", ac.allm.get("ause"));
					hs.setAttribute("amaster", ac.allm.get("amaster"));
					hs.setMaxInactiveInterval(1800);
				}
				else {
					this.pw.print("<script>"
							+ "alert('아직 계정이 승인 되지 않았습니다.');"
							+ "history.go(-1);"
							+ "</script>");
					this.pw.close();
				}
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
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return "/shop_member_list";
	}

}
