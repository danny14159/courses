package shixi.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.nutz.trans.Atom;

import shixi.bean.ScoreStat;
import shixi.bean.Student;

@IocBean(fields = { "dao" })
public class StudentDao extends IdEntityService<Student> {
	/**
	 * 插入一条记录
	 * 
	 * @param stu
	 */
	public void add(Student stu) {
		dao().insert(stu);
	}

	/**
	 * 批量插入学生信息
	 * 
	 * @param list
	 */
	public void batch(List<Student> list) {
		dao().fastInsert(list);

	}

	/**
	 * 按id删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		Student student = new Student();
		student.setId(id);
		student.setDel(1);
		Daos.ext(dao(), FieldFilter.create(getEntityClass(), true)).update(student);
	}

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 */
	public void update(final Student student) {
        if( null != student ){
            Daos.ext(dao(), FieldFilter.create(getEntityClass(), true)).update(student);
        }
	}

	/**
	 * 查询所有
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return 所有学生信息(分页)
	 */
	public QueryResult query(int pageNumber, int pageSize) {
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<Student> list = dao().query(Student.class,null, pager);
		pager.setRecordCount(dao().count(Student.class));
		return new QueryResult(list, pager);
	}

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return 对应id的学生信息
	 */
	public List<Student> queryById(int id) {
		return dao().query(Student.class, Cnd.where("id", "=", id));
	}

	/**
	 * 按at_class分页查询
	 * 
	 * @param atclass
	 * @param pageNumber
	 * @param pageSize
	 * @return 对应班级的学生信息（分页）
	 */
	public QueryResult querystu(int atclass, int pageNumber, int pageSize) {
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<Student> listByClass = dao().query(Student.class,
				Cnd.where("at_class", "=", atclass).and("del","=","0"), pager);
		pager.setRecordCount(dao().count(Student.class,
				Cnd.where("at_class", "=", atclass).and("del","=","0")));
		return new QueryResult(listByClass, pager);
	}

	
	/**
	 * 按at_class查询
	 * 
	 * @param at_class
	 * @return 对应班级的学生信息（不分页）
	 */
	public List<Student> queryByClass(int at_class) {
		return dao().query(Student.class, Cnd.where("at_class", "=", at_class).and("del","=","0"));
	}
	/**
	 * 统计班级人数
	 * @param at_class
	 * @return
	 */
	public int queryNumber(int at_class){
		List<Student> students=dao().query(Student.class, Cnd.where("at_class", "=", at_class).and("del","=","0"));
		return students.size();
	}
	/**
	 * 按at_grade查询所有学生
	 * queryByGrade
	 * @param at_grade
	 * @return List<Student>
	 */
    public List<Student> queryByGrade(int at_grade){
    	Sql sql = Sqls
				.create("SELECT ts.* from t_stu ts join t_class tc on ts.at_class=tc.id where tc.at_grade=@at_grade and ts.del=0");
		sql.params().set("at_grade", at_grade);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao().getEntity(Student.class));
		dao().execute(sql);
		List<Student> list = sql.getList(Student.class);
		return list;
    }
	/**
	 * 
	 * 按name查询
	 * 
	 * @param name
	 * @param pageNumber
	 * @param pageSize
	 * @return 对应名字的学生信息（分页）
	 */
	public QueryResult queryByName(String name, int pageNumber, int pageSize) {
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<Student> listByName = dao().query(Student.class,
				Cnd.where("name", "like", "%" + name + "%"), pager);
		pager.setRecordCount(dao().count(Student.class,
				Cnd.where("name", "like", "%" + name + "%")));
		return new QueryResult(listByName, pager);
	}
	/**
	 * 查询所有
	 * @return 所有学生信息(不分页)
	 */
	public List<Student> queryall(){
		return dao().query(Student.class, null);
	}
}
