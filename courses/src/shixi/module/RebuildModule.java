package shixi.module;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import shixi.bean.Rebuild;
import shixi.dao.RebuildDao;

@At("/rebuild")
@IocBean
public class RebuildModule {
	
	@Inject
	private RebuildDao rebuildDao;

	@At("/")
	@Ok("jsp:Rebuild")
	public void page(HttpServletRequest request,HttpSession session) {
		String username = (String) session.getAttribute("username");
		int level = (int) session.getAttribute("level");
		
		Cnd cnd = null;
		if(level == 3){
			cnd = Cnd.where("create_user", "=", username);
		}
		List<Rebuild> list = rebuildDao.dao().query(Rebuild.class,cnd );
		request.setAttribute("list", list);
	}
	
	@At("/apply")
	@Ok("json")
	public void apply(HttpSession session){
		
		String username = (String) session.getAttribute("username");
		Rebuild rebuild = new Rebuild();
		rebuild.setCreate_time(new Date());
		rebuild.setCreate_user(username);
		rebuild.setResult("未处理");
		rebuildDao.dao().insert(rebuild);
	}
	
	@At("/process")
	@Ok("json")
	public void process(Integer id,String result){
		
		rebuildDao.dao().update(Rebuild.class, Chain.make("result", result),Cnd.where("id", "=", id));
	}


}
