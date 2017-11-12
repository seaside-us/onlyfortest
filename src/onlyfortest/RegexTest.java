package onlyfortest;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		/*String s="\\we_are";
		//System.out.println("之前："+s);
		Matcher match=Pattern.compile("\\\\ \\w{6,20}",Pattern.MULTILINE).matcher(s);
		if(match.find()){
			System.out.println("匹配："+match.group());
		}*/
	/*	int maFrame=514;
		int start=0,end=0;
		int remainder=maFrame;//未分配余数帧数
		for (int i=0;i<3;i++) {
			start = end + 1;
			int per=maFrame*1/(i+2);
			if(2*per<=remainder){
				end=end+per;
				remainder-=per;
			}else{
				end=end+remainder;
				remainder=0;
			}
			if(start>=end){
				System.out.println("shotTime比例设置错误");
				break;
			}
			System.out.println(i+":"+start+"-"+end);
		}*/
		double a=(double)(1/4.0);
		System.out.println(a);
	}
}
