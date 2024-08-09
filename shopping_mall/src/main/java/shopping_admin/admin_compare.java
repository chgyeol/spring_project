package shopping_admin;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("admincompare")
public class admin_compare {

	@Resource(name="template2")
	private SqlSessionTemplate tm;
	Map<String, Object> allm = null;

	
	//어드민 멤버 승인 여부 변경
	public int admin_app(String ause, String aid) {
		this.allm = new HashMap<String, Object>();
		this.allm.put("ause", ause);
		this.allm.put("aid", aid);
		int result = tm.update("Shopbag_admin.admin_app",this.allm);
		//System.out.println(result);
		return result;
	}
	
	//어드민 멤버 수(master 제외)
	public int admin_ctn() {
		this.allm = new HashMap<String, Object>();
		this.allm.put("part", 2);
		int ctn = tm.selectOne("Shopbag_admin.a_count",this.allm);
		return ctn;
	}
	
	//어드민 멤버 목록(master 제외)
	public List<admin_dao> admin_list(){
		this.allm = new HashMap<String, Object>();
		this.allm.put("part", 2);
		List<admin_dao> data = tm.selectList("Shopbag_admin.a_all",this.allm);
		return data;
	}
	
	//어드민 계정 추가
	public int add_master_insert(admin_dao dao) {
		int result = tm.insert("Shopbag_admin.add_master",dao);
		return result;
	}
	
	//어드민 아이디 중복 체크
	public String admin_id_dupl(String i_aid) {
		String result = "";
		Map<String, String> data = new HashMap<String, String>();
		data.put("part", "1");
		data.put("aid", i_aid);
		int ctn = tm.selectOne("Shopbag_admin.a_count",data);
		if(ctn==0) {
			result="yes";
		}
		else if(ctn==1) {
			result="no";
		}
		else {
			result="error";
		}
		return result;
	}
	
	//어드민 비번 암호화(sha2 16진법)
	public String adminpw(String apass) {
		String s_apass = "";
		try {
			MessageDigest sha2 = MessageDigest.getInstance("SHA-256");
			sha2.update(apass.getBytes());
			byte[] sh = sha2.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : sh) {
				String word = String.format("%x", w);
				sb.append(word);
			}
			s_apass = sb.toString();
			//System.out.println(s_apass);
		}
		catch (Exception e) {
			//System.out.println(e);
			s_apass = "error";
		}
		return s_apass;
	}
	
	//어드민 아이디로 멤버 정보 가져오기
	public void adminmember(List<admin_dao> adl){
		this.allm = new HashMap<String, Object>();
		this.allm.put("aidx", adl.get(0).getAidx());
		this.allm.put("aid", adl.get(0).getAid());
		this.allm.put("aname", adl.get(0).getAname());
		this.allm.put("ause", adl.get(0).getAuse());
		this.allm.put("amaster", adl.get(0).getAmaster());
		//System.out.println(adl.get(0).getAidx());
	}
	
	//어드민 아이디, 비번 비교
	public String admindata(String aid, String apass){
		String result = "";
		Map<String, String> data = new HashMap<String, String>();
		data.put("part", "1");
		data.put("aid", aid);
		int ctn = tm.selectOne("Shopbag_admin.a_count",data);
		if(ctn==1){
			List<admin_dao> adl = tm.selectList("Shopbag_admin.a_all",data);
			String c_apass = adl.get(0).getApass();
			String s_apass = adminpw(apass);
			if(s_apass.equals("error")) {
				result="error";
				//System.out.println(result);
			}
			else if(s_apass.equals(c_apass)) {
				this.adminmember(adl);
				result="ok";
				//System.out.println(result);
			}
			else {
				result="no";
				//System.out.println(result);
			}
		}
		else if(ctn==0){
			result = "no";
			//System.out.println(result);
		}
		else {
			result="error";
		}
		//System.out.println(result);
		return result;
	}
	
}
