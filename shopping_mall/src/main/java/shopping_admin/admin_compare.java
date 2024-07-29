package shopping_admin;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("admincompare")
public class admin_compare {

	@Resource(name="template2")
	private SqlSessionTemplate tm;
	
	//어드민 아이디, 비번 비교
	public String admindata(String aid, String apass){
		String result = "";
		int ctn = tm.selectOne("Shopbag_admin.a_count",aid);
		if(ctn>0){
			String c_apass = tm.selectOne("Shopbag_admin.a_pw",aid);
			if(c_apass.equals(apass)) {
				result="ok";
			}
			else {
				result="no";
			}
		}
		else {
			result = "no";
		}
		return result;
	}
	
}
