package onlyfortest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class English {
	static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("D:/已经摘取 最新 2108秋季口语20180615for音频1(4).xls");
		File output = new File("D:/data.txt");// 输出数据文件
		try {
			// 创建输入流，读取Excel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			BufferedWriter outputData = new BufferedWriter(new FileWriter(output));
			// jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index <= 0; index++) {// 此处只取index=0
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				// sheet.getRows()返回该页的总行数
				boolean Questioning=false;
				
				for (int i = 0; i < sheet.getRows(); i++) {//行
					// sheet.getColumns()返回该页的总列数
					int NumInQuest=0;
					Questioning=false;
					for (int j = 0; j < sheet.getColumns()-1;) {//列
						String cellinfo = sheet.getCell(j, i).getContents();
						if(cellinfo.startsWith("P_")){
							outputData.write("\n\n");
							Questioning=false;
							String title=cellinfo+"      "+sheet.getCell(++j, i).getContents();
							outputData.write(title+ "\n");
							String questId=sheet.getCell(++j, i).getContents();
							if(questId.startsWith("Q_")){//第三个单元格
								NumInQuest=0;
								Questioning=true;
								outputData.write(questId+ "\n");
							}
						}
						String cellAfter = sheet.getCell(++j, i).getContents();
						if(Questioning&&cellAfter!=null&&!cellAfter.trim().isEmpty()){//如果是一题多问，换行接着写听力原文
							outputData.write(String.valueOf(++NumInQuest)+ "\n");//问题中的语句条数							
							outputData.write(cellAfter+ "\n");
						}else if(!Questioning&&cellAfter!=null&&!cellAfter.trim().isEmpty()){						
							outputData.write(cellAfter+ "\n");
						}						
					}
				}
			}
			outputData.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
