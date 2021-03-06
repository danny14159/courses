package shixi.module;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.web.ajax.Ajax;
import org.nutz.web.ajax.AjaxReturn;

import shixi.bean.User;
import shixi.service.UserService;

@IocBean
@At("/users")
public class LoginModule {
	@Inject
	private UserService userServiceImpl;		

	@POST
	@At("/login")
	@Ok("json")
	public AjaxReturn login(@Param("username") String username,
			@Param("password") String password, HttpSession session) {
		User user = userServiceImpl.queryByName(username);
		AjaxReturn result = Ajax.fail();
		if (user == null){
			result.setMsg("该用户不存在");
			
		}else if ((user.getPassword()).equals(password)) {
			session.setAttribute("username", username);
			session.setAttribute("level", user.getLevel());
			result.setOk(true).setMsg("登陆成功");
		} else {
			result.setMsg("密码错误");
		}
		return result;
	}

	@At("/logout")
	@Ok("jsp:/login")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@At("/")
	public void toLogin() {
	}
}
