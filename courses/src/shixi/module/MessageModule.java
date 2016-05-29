package shixi.module;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import shixi.bean.Message;
import shixi.dao.MessageDao;

/**留言功能
 * @author
 *
 */
@At("/message")
@IocBean
public class MessageModule {
	
	@Inject
	private MessageDao messageDao;

	@At("/")
	@Ok("jsp:Message")
	public void page(HttpServletRequest request,HttpSession session) {
		String username = (String) session.getAttribute("username");
		int level = (int) session.getAttribute("level");
		
		Cnd cnd = null;
		if(level == 3){
			cnd = Cnd.where("username", "=", username);
		}
		List<Message> list = messageDao.dao().query(Message.class,cnd );
		request.setAttribute("list", list);
	}
	
	@At("/insert")
	@Ok("redirect:/main/")
	public void insert(@Param("..") Message message,HttpSession session){
		message.setCreate_time(new Date());
		message.setUsername((String) session.getAttribute("username"));
		messageDao.dao().insert(message);
	}
	

}
