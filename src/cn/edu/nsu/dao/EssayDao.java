package cn.edu.nsu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.edu.nsu.bean.Essay;
import cn.edu.nsu.bean.User;
import cn.edu.nsu.tools.ChangeCode;

public class EssayDao {
	private SessionFactory sessionFactory;
	public static String Tag = "essayDao";
	private Essay essay;
	private List<Essay> essays;
	private Session session;
	private Query query;
	private String hql;
	private int pageSize;
	private int lastPage;
	private String type;
	private String key;
	private static Map<Map<String,Object>,List<Essay>> cacheMap 
		= new HashMap<Map<String,Object>,List<Essay>>();
	public EssayDao(){
		System.out.println("初始化essayDao");
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		System.out.println("设置了pageSize=" + pageSize);
		EssayDao.buildCache(this);
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("设置了sessionFactory");
		this.sessionFactory = sessionFactory;
	}

	public List<Essay> getEssays() {
		return essays;
	}

	public void setEssays(List<Essay> essays) {
		this.essays = essays;
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
	
	private Essay getEssayByHql(String hql){
		try {
			query = getSession().createQuery(hql);
			setEssay((Essay)query.list().get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
			session=null;
		}
		return getEssay();
	}
	
	private List<Essay> getListByHql(String hql, int page) {
		int total;
		try {
			System.out.println(EssayDao.Tag + "开始执行hql "+hql);
			query = getSession().createQuery(hql);
			total = query.list().size();
			System.out.println("创建了query");
			if(page > 0){
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				essays = query.list();
				System.out.println("essay.size = "+essays.size());
				if (essays == null) {
					System.out.println("essays没有");
					return null;
				}
				lastPage = getLastPage(total);
				System.out.println("lastPage="+lastPage);
			}else{
				essays = query.list();
				lastPage = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
			session=null;
		}
		return essays;
	}

	private int getLastPage(int total) {
		if (total % pageSize == 0)
			lastPage = total / pageSize;
		else
			lastPage = total / pageSize + 1;
		return lastPage;
	}
	public List<Essay> getEssayListByType(String type,int page){
		
		if(type==null||type.equals(""))
			hql="from Essay";
		else{
			if(!EssayDao.cacheMap.isEmpty()){
				Map<Map<String,Object>,List<Essay>> map = EssayDao.cacheMap;
				
				Iterator it=map.keySet().iterator();    
				while(it.hasNext()){    
				     Map<String,Object> key;      
				     key=(Map<String,Object>)it.next();
				     	if(key.get("type1").equals(type) && Integer.parseInt(
				     			(key.get("page")==null?"":key.get("page").toString())
				     			)==page){
				     		setLastPage(Integer.parseInt(
				     			(key.get("lastPage")==null?"":key.get("lastPage").toString())
				     			));
				     		return map.get(key);
				     	}
				}
				
			}
			
			System.out.println("type="+type+"and page="+page+"没有被匹配到");
			type = utf8ToUnicode(type);
			hql="from Essay where type1 like '%"
			   +type+"%'";
		}
		  return getListByHql(hql, page);
	}
	public List<Essay> getEssayListBySearch(String key,int page){
		key = utf8ToUnicode(key);
		if(key == null || key.equals("")){
			hql = "from Essay";
		}
		else{
			hql = "from Essay where title like '%"+key+"%'";
			hql += " or type1 like '%"+key+"%'";
			hql += " or author like '%"+key+"%'";
		}
		return getListByHql(hql, page);
	}
	public Essay getEssayByAc(String ac){
		if(ac == null || ac.equals("")){
			return null;
		}
		hql = "from Essay where ac = '"+ac+"'";
		return getEssayByHql(hql);
	}
	public Essay getEssayById(int id){
		hql = "from Essay where id ="+id;
		return getEssayByHql(hql);
	}
	public int getEssayMaxId(){
		int defaultId = 0;
		hql = "from Essay where id=(select max(id) from Essay)";
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
		return defaultId;
	}
	public String utf8ToUnicode(String utf8){
		return ChangeCode.utf8ToUnicode(utf8);
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public Essay getEssay() {
		return essay;
	}
	public void setEssay(Essay essay) {
		this.essay = essay;
	}
	public static void buildCache(EssayDao essayDao){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String[] types = new String[]{"综合","工作","动漫","漫画","游戏"};
				System.out.println("正在通过静态方法建立map缓存");
				Map<Map<String,Object>,List<Essay>> map = EssayDao.cacheMap;
				for(int x = 0; x < types.length; x++){		
					int lastPage = 1;
					for(int i = 0; i <= lastPage; i++){
						List<Essay> essays1 = essayDao.getEssayListByType(types[x], i);
						Map<String,Object> tag = new HashMap<String,Object>();
						if(i == 1)
							lastPage = essayDao.getLastPage();
						tag.put("type1", types[x]);
						tag.put("page",i);
						tag.put("lastPage",lastPage);
						map.put(tag, essays1);
					}
				}
				System.out.println("map缓存建立完成");
			}
		}).start();
	}
}
