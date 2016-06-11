package cn.edu.nsu.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.nsu.bean.Essay;
import cn.edu.nsu.dao.EssayDao;

public class EssayAction extends ActionSupport{
	public static String Tag = "EssayAction";
	private Essay essay;
	private String ac;
	private EssayDao essayDao;
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public Essay getEssay() {
		//System.out.println("通過essayAction取得essay");
		return essay;
	}
	public void setEssay(Essay essay) {
		//System.out.println("设置了essay");
		this.essay = essay;
	}
	public EssayDao getEssayDao() {
		return essayDao;
	}
	public void setEssayDao(EssayDao essayDao) {
		//System.out.println("设置了essayDao");
		this.essayDao = essayDao;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("正在使用EssayAction+ac="+ac);
		
		essay = essayDao.getEssayByAc(ac);
		essay.unicodeToUtf8();
		System.out.println(EssayAction.Tag +"取出来的文章是："+essay.getTitle());
		return SUCCESS;
	}
}
