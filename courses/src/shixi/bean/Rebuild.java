package shixi.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Table;

@Table("t_rebuild")
public class Rebuild {

	private Integer id;
	
	private String create_user;
	
	private Date create_time;
	
	private String result;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
