package shixi.module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import shixi.bean.File;
import shixi.dao.FileDao;

@At("/files")
@IocBean
public class FilesModule {

	@Inject
	private FileDao fileDao;

	@At("/")
	@Ok("jsp:Files")
	public void page(HttpServletRequest request) {
		request.setAttribute("list", fileDao.dao().query(File.class, null));
	}

	@AdaptBy(type = UploadAdaptor.class)
	@At("/upload")
	@Ok("redirect:/main/")
	public void upload(@Param("file") java.io.File file, @Param("name") String name, HttpSession session,
			HttpServletRequest request) throws FileNotFoundException {

		String username = (String) session.getAttribute("username");
		String suffix = Files.getSuffixName(file);
		File fileObj = new File();
		fileObj.setContent(new FileInputStream(file));
		fileObj.setCreate_time(new Date());
		fileObj.setCreate_user(username);
		fileObj.setName(name + "." + suffix);

		Files.rename(file, name + suffix);

		fileDao.dao().insert(fileObj);

	}

	@At("/download/?")
	@Ok("raw")
	public java.io.File download(Integer id, HttpServletResponse response) throws IOException {

		// File file = fileDao.dao().fetch(File.class, Cnd.where("id","=",id));
		Sql sql = Sqls.create("select content,name from t_file where id=@id");
		sql.params().set("id", id);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				File file = new File();
				rs.next();
				file.setContent(rs.getBinaryStream("content"));
				file.setName(rs.getString("name"));

				return file;
			}
		});

		fileDao.dao().execute(sql);

		File file = sql.getObject(File.class);

		java.io.File outFile = Files.createFileIfNoExists(file.getName());
		Files.write(outFile, file.getContent());

		return outFile;
	}
}
