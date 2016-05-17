package shixi.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import shixi.bean.File;

@IocBean(fields = { "dao" })
public class FileDao  extends IdEntityService<File>{

}
