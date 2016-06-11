package cn.edu.nsu.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.nsu.bean.User;
import cn.edu.nsu.dao.UserDao;

public class LoginAction extends ActionSupport{
	public static String Tag = "loginAction";
	private String name,password;
	private UserDao userDao;
	private User user;
	@Override
	public String execute() throws Exception {
		String str = INPUT;
		System.out.println(LoginAction.Tag+"处理execute name="+name);
		setUser(userDao.getUserByName(name));
		if(getUser() != null || getUser().getPassword().equals(password)){
			str = SUCCESS;
		}
		return str;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		System.out.println(LoginAction.Tag+"设置了UserDao");
		
		this.userDao = userDao;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
