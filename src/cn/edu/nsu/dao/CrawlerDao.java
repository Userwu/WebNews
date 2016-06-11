package cn.edu.nsu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cn.edu.nsu.bean.Essay;
import crawler.Tlls4;
import crawler.Tlls4.CallBcak;
import crawler.Wen;;

public class CrawlerDao {
	private SessionFactory sessionFactory;
	private List<Essay> essays;
	private Session session;
	private Query query;
	
	public CrawlerDao(){
		System.out.println("初始化CrawlerDao");
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("爬虫开始执行");
				new Tlls4(new CallBcak() {
					@Override
					public void run(Object... obj) {
						String cmd = (String) obj[0];
						switch (cmd) {
						case "cc":
							Wen wen = (Wen) obj[1];
							Essay essay = wenToEssay(wen);
							essays.add(essay);
							break;
						case "end":
							add();  //爬虫结束,开始添加数据库
							break;
						default:
							break;
						}
						

					}
				}).Start();
			}
		}).start();
	}
	public List<Essay> getEssays() {
		return essays;
	}
	public void setEssays(List<Essay> essays) {
		this.essays = essays;
	}
	public Session getSession() {
		if(session == null || !session.isOpen())
			session = sessionFactory.openSession();
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
	public void add(){
		if(essays != null){
			Transaction tr = getSession().beginTransaction();
		for(Essay essay:essays)
			getSession().save(essay);
		tr.commit();
		System.out.println("获取到" +essays.size() +"条新数据");
		}else
			System.out.println("没有获取到新数据,");
	}
	
	private Essay wenToEssay(Wen wen){
		Essay essay = new Essay();
		essay.setAc(wen.getAc());
		essay.setAuthor(Tlls4.toUnicode(wen.getAuthor()));
		essay.setTime(Tlls4.toUnicode(wen.getTime()));
		essay.setContent(Tlls4.toUnicode(wen.getContent()));
		essay.setTag(Tlls4.toUnicode(wen.getTag()));
		essay.setTitle(Tlls4.toUnicode(wen.getTitle()));
		essay.setType1(Tlls4.toUnicode(wen.getKey()));
		return essay;
	}
	
}
