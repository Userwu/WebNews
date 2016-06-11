package crawler;

public class Wen {
	//static final String Sql = "INSERT INTO `essay` (ac,key,title,author,time,content,tag) VALUES ('##','##','##','##','##','##');";
	private String ac = "";
	private String key = "";
	private String title = "";
	private String author = "";
	private String time = "";
	private String content = "";
	private String tag = "";
	private String url = "";
	
	
	public String[] getAll(){
		String[] ttt = new String[7];
		ttt[0] = ac;
		ttt[1] = key;
		ttt[2] = title;
		ttt[3] = author;
		ttt[4] = time;
		ttt[5] = content;
		ttt[6] = tag;
		
		return ttt;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
