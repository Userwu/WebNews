package cn.edu.nsu.action;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nsu.bean.Essay;
import cn.edu.nsu.dao.EssayDao;
import cn.edu.nsu.tools.RandomCreate;

import com.opensymphony.xwork2.ActionSupport;

public class EssayListAction extends ActionSupport{
	private List<Essay> essays;
	private String type1;
	private String type2;
	private String page;
	private int lastPage;
	private EssayDao essayDao;
	private String key;
	private String need;
	public static int tag = 0;
	
	private List<Essay> randomList;
	private int id,num;
	private String row;
	public EssayListAction(){
		tag++;
	}
	public List<Essay> getEssays() {
		return essays;
	}
	public void setEssays(List<Essay> essays) {
		this.essays = essays;
	}
	public String getType1() {
		System.out.println("取得type1 ="+type1);
		return type1;
	}
	public void setType1(String type) {
		
		this.type1 = type;
	}
	public EssayDao getEssayDao() {
		return essayDao;
	}
	public void setEssayDao(EssayDao essayDao) {
		this.essayDao = essayDao;
	}
	public String searchList(){
		System.out.println("执行搜索："+page);
		int p = Integer.valueOf(page);
		String str;
		System.out.println("key = "+key);
		essays = essayDao.getEssayListBySearch(key, p);
		setLastPage(essayDao.getLastPage());
		str = "TYPELIST";
		if(essays == null){
			return ERROR;
		}
		for(Essay e :essays){
			e.unicodeToUtf8();
		}
		return str;
	}
	public String getList(){
		System.out.println("action"+EssayListAction.tag+"getList开始执行");
		String str;
		if(type1 == null || type1.equals("")){
			
			type1 = "综合";
		}
		String[] types = new String[]{"综合","游戏","工作","动漫","漫画"};
		boolean flag = false;
		for(String t:types){
			if(t.equals(type1)){
				flag = true;
				break;
			}
		}
		if(flag == false){
			type1 = "综合";
		}
		int p = Integer.valueOf(page);
		System.out.println("list列表：type1="+type1 + "page="+p);
		essays = essayDao.getEssayListByType(type1, p);
		setLastPage(essayDao.getLastPage());
		str = "TYPELIST";
		if(essays.isEmpty()){
			return ERROR;
		}
		for(Essay e :essays){
			e.unicodeToUtf8();
		}
		return str;
	}
	public String getRandom(){
		System.out.println("action"+EssayListAction.tag+"getRandom开始:row="+row+"need="+need+"type2="+type2);
		List<Essay> list = new ArrayList<Essay>(essayDao.getEssayListByType(type2, 0));
		randomList = new ArrayList<Essay>();
		num = Integer.parseInt(getRow());
		int random = 1;
		for(int i = 0; i < num;i++){
			int x = list.size();
			if(x != list.size()){
				System.out.println(x+">"+list.size());
			}
			if(list.size() == 0)
				break;
			if(x != list.size()){
				System.out.println(x+">"+list.size());
			}
			if (list.size()+i > num){
				
					random = RandomCreate.create(0, list.size()-1);
					if(x != list.size()){
						System.out.println(x+">"+list.size());
					}
			}
			if(x != list.size()){
				System.out.println(x+">"+list.size());
			}
			try{
				randomList.add(list.get(random));
				if(x != list.size()){
					System.out.println(x+">"+list.size());
				}
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(list.size() + "  "+random);
				random = 0;
			}
			list.remove(random);
		}
		for(Essay e :randomList){
			e.unicodeToUtf8();
		}
		init();
		return need;
	}
	public void init(){
		
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getKey() {
		return key;
	}
	public List<Essay> getRandomList() {
		return randomList;
	}
	public int getId() {
		return id;
	}
	public int getNum() {
		return num;
	}
	public String getRow() {
		return row;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setRandomList(List<Essay> randomList) {
		this.randomList = randomList;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

}
