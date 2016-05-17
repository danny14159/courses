package shixi.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import shixi.bean.Rebuild;


@IocBean(fields = { "dao" })
public class RebuildDao  extends IdEntityService<Rebuild>{

}
