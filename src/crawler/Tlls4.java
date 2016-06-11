package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class Tlls4 {
	
	static final String Sql = "INSERT INTO `essay` (ac,type1,title,author,time,content,tag) VALUES ('##','##','##','##','##','##','##');";
	static final String acfun = "http://www.acfun.tv";
	public static final String[] uri = {"http://www.acfun.tv/v/list110/index_#.htm",
										"http://www.acfun.tv/v/list73/index_#.htm",
										"http://www.acfun.tv/v/list74/index_#.htm",
										"http://www.acfun.tv/v/list75/index_#.htm",
										"http://www.acfun.tv/v/list164/index_#.htm",
										};
	public static String img_t = "<img src=\"/img/##\" />";
	public static int[] page = new int[5];
	public static String[] key = {"综合","工作.情感","动漫文化","漫画小说","游戏"};
	public static String base_path = "D:\\ddbb\\";
	public static String save_path = "sql.txt";
	public static String cach_Done = "cach_Done.txt";
	public static String save_img = "img\\";
	public static CallBcak callBcak;
	
	public Tlls4(CallBcak callBcak){
		this.callBcak = callBcak;
	}
	
	public static void Start(){
		init();
		for(int i = 0;i < 5; i ++){
			String the_url = uri[i];
			int the_page = page[i];
			ArrayList<String> list;
			int the_count = key.length+1; 
			while(the_count < 5){    // 修改
				String content = HttpUtil.sendGet(the_url.replace("#", String.valueOf(the_page)), "UTF-8");
				list = getWenzhangURL(content);
				haha(list,key[i],callBcak);
				the_count++;
			}
			callBcak.run("end");
		}
	}
	public static void haha(ArrayList<String> list,String type,CallBcak callBcak){
		int the_start = 0,the_end = 0;
		if(list  == null)
			return;
		for(String uu:list){
			StringBuilder sb = new StringBuilder();
			Wen wen = new Wen();
			wen.setKey(type);
			wen.setTag("TEST");
			wen.setUrl(acfun + uu);
			wen.setAc(uu.substring(3).trim());
			if(FindCach(wen.getAc()))
				continue;
			String content = HttpUtil.sendGet(wen.getUrl(), "UTF-8");
			the_start = content.indexOf("txt-title-view_1");
			the_start = content.indexOf(">", the_start);
			the_end = content.indexOf("<", ++the_start);
			wen.setTitle(content.substring(the_start, the_end));
			the_start = content.indexOf("发布于",the_end);
			the_end = content.indexOf("<", the_start);
			wen.setTime(content.substring(the_start, the_end));
			
//			while((the_start = content.indexOf("<p align=\"center\">",the_end)) != -1){
//				the_start = content.indexOf(">",the_start);
//				the_end = content.indexOf("</p>", ++the_start);
//				String sls = content.substring(the_start,the_end);
//				if(sls.indexOf("img") != -1){
//					int start = sls.indexOf("http");
//					int end = sls.indexOf("\"", start);
//					sls = sls.substring(start, end);
//					int oow = sls.lastIndexOf(".");
//					String filename = MMDD55.GetMD5Code(sls) + sls.substring(oow, sls.length());
//					ImgDown(sls,filename);
//					sls = img_t.replace("##", filename);
//				}
//				sb.append(sls);
//				the_start = the_end;
//			}
//			if("".equals(sb.toString().trim()))
//			while((the_start = content.indexOf("<span style=\"font-size: medium;\">",the_end)) != -1){
//				the_start = content.indexOf(">",the_start);
//				the_end = content.indexOf("</span>", ++the_start);
//				String sls = content.substring(the_start,the_end);
//				if(sls.indexOf("img") != -1){
//					int start = sls.indexOf("http");
//					int end = sls.indexOf("\"", start);
//					sls = sls.substring(start, end);
//					int oow = sls.lastIndexOf(".");
//					String filename = MMDD55.GetMD5Code(sls) + sls.substring(oow, sls.length());
//					ImgDown(sls,filename);
//					sls = img_t.replace("##", filename);
//				}
//				sb.append(sls);
//				the_start = the_end;
//			}
//			else 
			if("".equals(sb.toString().trim())){
				int start = content.indexOf("area-part-view");
				start = content.indexOf("wenzhang", start);
				int end = content.indexOf("<h2>", start);
				int lli;
				String sls = null;
				String temp = content.substring(start, end);
				start = end = 0;
				while((start = temp.indexOf("<",end)) != -1){
					if(start-end >= 2 &&end != 0){
						sls = temp.substring(end+1, start);
						if(sls.trim().length()>=1)
						sb.append(sls + "<br/>");
					}
					end = temp.indexOf(">", start);
					if(end == -1)
						end = temp.length();
					lli= temp.indexOf("img", start);
					if(lli != -1&&lli < end){
						sls = temp.substring(lli, end);
						int start1 = sls.indexOf("http");
						int end1 = sls.indexOf("\"", start1);
						if(start1 ==-1 || end1 > end)
							continue;
						sls = sls.substring(start1, end1);
						int oow = sls.lastIndexOf(".");
						String filename = MMDD55.GetMD5Code(sls) + sls.substring(oow, sls.length());
						ImgDown(sls,filename);
						sls = img_t.replace("##", filename);
						sb.append(sls + "<br/>");
					}
				}
			}
			if("".equals(sb.toString().trim()))
				continue;
			addToTXT(sb.toString(),wen.getAc()+".txt");
			wen.setContent(sb.toString());
			the_start = content.indexOf("<nobr>",the_end);
			the_end = content.indexOf("</nobr>",the_start);
			wen.setAuthor(content.substring(the_start+6, the_end));
			if(callBcak != null)
				callBcak.run("cc",wen);
			String sqlcmd = getsql(wen.getAll());
			addToSql(sqlcmd);
			addToCach(wen.getAc());
		}
		
	}
	
	public interface CallBcak{
		public void run(Object... obj);
	}
	public static Boolean ImgDown(String sl,String filename){
		byte[] ii = new byte[1024*64];
		int lenth = 0;
		URL url;
		FileOutputStream fos = null;
		InputStream is  = null;
		try {
			url = new URL(sl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			is = conn.getInputStream();
			File img = new File(save_img+filename);
			if(img.exists())
				return true;
			fos = new FileOutputStream(img);
			while((lenth = is.read(ii, 0, ii.length)) > 0)
				fos.write(ii, 0, lenth);
			return true;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}
	public static ArrayList<String> getWenzhangURL(String content){
		ArrayList<String> list = new ArrayList<String>();
		int the_start = 0,the_end = 0;
		the_start = content.indexOf("本区推荐");
		the_end = content.indexOf("合作", the_start);
		if(the_end == -1 || the_start == -1)
			return null;
		content = content.substring(the_start, the_end);
		the_start = 0;
		while((the_start = content.indexOf("href", the_start)) != -1){
			the_start = content.indexOf("\"", the_start);
			the_end = content.indexOf("\"", ++the_start);
			list.add(content.substring(the_start, the_end));
			the_start = the_end;
		}
		return list;
	}
	
	public static String getsql(String[] test){
		String ssqqll = Sql;
		ssqqll = ssqqll.replaceFirst("##", test[0]);
		for(int i = 1 ; i < 7 ;i++)
			ssqqll = ssqqll.replaceFirst("##", toUnicode(test[i]));
		return ssqqll;
	}
	public static String toUnicode(String tt){
		StringBuilder unicode = new StringBuilder();
		for(int i = 0 ;i < tt.length(); i++)
			unicode.append("\\\\u" + Integer.toHexString(tt.charAt(i)));
		return unicode.toString();
	}
	private static void init(){
		
		for(int i : page)
			i = 1;
		save_path = base_path + save_path;
		cach_Done = base_path + cach_Done;
		save_img = base_path+ save_img;
		File file = new File(base_path);
		if(!file.exists())
			file.mkdir();
		File sqlfile = new File(save_path);
		try {
			if(!sqlfile.exists())
				sqlfile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String nextpage(int what){
		page[what]++;
		return uri[what].replaceAll("#",String.valueOf(page[what]));
	}
	public static Boolean FindCach(String ac){
		File file = new File(cach_Done);
		BufferedReader bf = null;
		try {
			if(!file.exists())
				file.createNewFile();
			file.createNewFile();
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			while((line = bf.readLine()) != null)
				if(ac.equals(line.trim())){
					return true;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bf != null)
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	return false;
	}
	public static Boolean addToCach(String ac){
		ac = ac.trim();
		FileWriter writer = null;
		if(FindCach(ac))
			return false;
		File file = new File(cach_Done);
		try {
			writer = new FileWriter(file, true);      ////修改
			writer.write(ac+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}
	public static Boolean addToSql(String sql){
		FileWriter writer = null;
		File file= new File(save_path);

		try {
			writer = new FileWriter(file, true);
			writer.write(sql + "\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
	public static Boolean addToTXT(String content,String filename){
		FileWriter writer = null;
		File file= new File(base_path+"\\content\\" + filename);
		if(file.exists())
			return true;
		try {
			writer = new FileWriter(file, true);
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
	
}
