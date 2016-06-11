package cn.edu.nsu.bean;

import cn.edu.nsu.tools.ChangeCode;

public class Essay {
	private int id;
	private String ac;
	private String title, author, time, content, tag, type1;
	private String firstImgUrl;
	private String charset = "unicode";
	private int times = 1;

	public String getCharset() {
		return charset;
	}

	public void setFirstImgUrl(String firstImgUrl) {
		this.firstImgUrl = firstImgUrl;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void unicodeToUtf8() {

		if (this.getCharset().equals("UTF8")) {
			return;
		}
		this.setTitle(ChangeCode.unicodeToUtf8(this.getTitle()));
		this.setAuthor(ChangeCode.unicodeToUtf8(this.getAuthor()));
		this.setType1(ChangeCode.unicodeToUtf8(this.getType1()));
		this.setTime(ChangeCode.unicodeToUtf8(this.getTime()));
		this.setContent(ChangeCode.unicodeToUtf8(this.getContent()).replaceAll("/img", "img"));
		this.setTag(ChangeCode.unicodeToUtf8(this.getTag()));
		setFirstImgUrl();
		// System.out.println("本essay转第"+times+"次码");
		times++;
		this.setCharset("UTF8");
	}

	public String getFirstImgUrl() {

		return firstImgUrl;
	}

	public void setFirstImgUrl() {
		int start = getContent().indexOf("<img ");
		int end = getContent().indexOf("\" />");
		if (start < 0 || start > getContent().length()) {
			firstImgUrl = "<img src=\"img/default.png\"/>";
			return;
		}
		while (end <= start && end <= getContent().length()) {
			end = getContent().indexOf("\" />", end + 1);
		}
		if (end < 0)
			firstImgUrl = "<img src=\"img/default.png\"/>";
		else
			firstImgUrl = getContent().substring(start, end + 4);
		StringBuffer str = new StringBuffer(firstImgUrl);
		end = str.indexOf("/>");
		str.insert(end - 1, "onerror=\"nofind()\"");
		firstImgUrl = str.toString();
	}
}
