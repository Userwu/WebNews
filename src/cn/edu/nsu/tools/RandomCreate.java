package cn.edu.nsu.tools;

public class RandomCreate {
	public static double defaultCreate(){
		return Math.random();
	}
	public static int create(int min,int max){
		int re = 0;
		re = (int)(min+(Math.random()*(max-min)));
		
		if(re > max || re < min){
			System.out.println("越界随机数："+re+" ====max=" +max + "  min="+min);
		}
		return re;
	}
}
