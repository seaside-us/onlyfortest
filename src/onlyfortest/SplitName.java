package onlyfortest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.LinkedList;

public class SplitName {
	static final int stationNum = 318;// 站点总数
	static LinkedList<String> stationsName=new LinkedList<String>();

	public static void main(String[] args) {
		File file = new File("F:/bjut_ai_maya/subwayName.txt");//E:\subwayStation\20180416
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// 每行数据分割
				for (int i = 1; i < lineData.length; i++) {
					stationsName.add(lineData[1]);
				}
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}