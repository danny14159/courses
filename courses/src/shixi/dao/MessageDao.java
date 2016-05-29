package shixi.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import shixi.bean.Message;

@IocBean(fields = { "dao" })
public class MessageDao  extends IdEntityService<Message>{

}
