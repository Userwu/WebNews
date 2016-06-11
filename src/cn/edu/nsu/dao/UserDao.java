package cn.edu.nsu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cn.edu.nsu.bean.Essay;
import cn.edu.nsu.bean.User;

public class UserDao {
	public static String Tag = "userDao";
	private SessionFactory sessionFactory;
	private User user;
	private List<User> users;
	private Session session;
	private Query query;
	private String hql;
	private int pageSize;
	private int lastPage;
	public SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println(UserDao.Tag+"设置了sessionFactory");
		this.sessionFactory = sessionFactory;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Session getSession() {
		if(session == null || !session.isOpen()){
			session = sessionFactory.openSession();
		}
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	private User getUserByHql(String hql){
		try {
			query = getSession().createQuery(hql);
			setUser((User)query.list().get(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			session = null;
		}
		return getUser();
	}
	private List<User> getUsersByHql(String hql,int page){
		int total;
		try {
			query = getSession().createQuery(hql);
			total = query.list().size();
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			users = query.list();
			if (users == null || users.size() == 0) {
				return null;
			}
			lastPage = getLastPage(total);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			session = null;
		}
		return users;
	}
	private int getLastPage(int total) {
		if (total % pageSize == 0)
			lastPage = total / pageSize;
		else
			lastPage = total / pageSize + 1;
		return lastPage;
	}
	public User getUserByName(String name){
		System.out.println(UserDao.Tag+"除了getUserByName name="+name);
		if(name==null || name==""){
			return null;
		}
		hql = "from User where name = '"+name+"'";
		return getUserByHql(hql);
	}
	public User getUserById(int id){
		if(id<0){
			return null;
		}
		hql = "from User where id = '"+id+"'";
		return getUserByHql(hql);
	}
	public boolean addUser(User user){
		if(user == null)
			return false;
		try{
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			System.out.println(UserDao.Tag+"通过 session添加了一个id为"+user.getId()+"的用户");
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();//回滚事务
		}finally {
			if(session != null)
				session.close();
			session = null;
		}
		return true;
	}
	public User setRegisterDefaultMessage(User user){
		int defaultId = 0;
		int defaultJurisdiction = 0;
		int defalltLevel = 0;
		hql = "from User where id=(select max(id) from User)";
		try{
			query = getSession().createQuery(hql);
			defaultId = ((User)query.list().get(0)).getId() + 1;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
			session = null;
		}
		user.setId(defaultId);
		user.setJurisdiction(defaultJurisdiction);
		user.setLevel(defalltLevel);
		return user;
	}
}
