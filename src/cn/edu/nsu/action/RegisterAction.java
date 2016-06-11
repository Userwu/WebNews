package cn.edu.nsu.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.nsu.bean.User;
import cn.edu.nsu.dao.UserDao;

public class RegisterAction  extends ActionSupport{
	public static String Tag = "RegisterAction";
	private int id,jurisdiction,level;
	private String name,password,email,phone;
	private UserDao userDao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String str = INPUT;
		System.out.println(RegisterAction.Tag+"¥¶¿Ìexecute name="+name);
		User user = userDao.setRegisterDefaultMessage(new User());
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		if(userDao.addUser(user))
			str = SUCCESS;
		return str;
	}
}
