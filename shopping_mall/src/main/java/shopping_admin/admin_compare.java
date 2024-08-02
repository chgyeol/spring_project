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
		this.allm.put("amaster", adl.get(0).getAmaster());
		//System.out.println(adl.get(0).getAidx());
	}
	
	//어드민 아이디, 비번 비교
	public String admindata(String aid, String apass){
		String result = "";
		int ctn = tm.selectOne("Shopbag_admin.a_count",aid);
		if(ctn==1){
			List<admin_dao> adl = tm.selectList("Shopbag_admin.a_all",aid);
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
