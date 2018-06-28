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
		File file = new File("D:/�Ѿ�ժȡ ���� 2108�＾����20180615for��Ƶ1(4).xls");
		File output = new File("D:/data.txt");// ��������ļ�
		try {
			// ��������������ȡExcel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			BufferedWriter outputData = new BufferedWriter(new FileWriter(output));
			// jxl�ṩ��Workbook��
			Workbook wb = Workbook.getWorkbook(is);
			// Excel��ҳǩ����
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index <= 0; index++) {// �˴�ֻȡindex=0
				// ÿ��ҳǩ����һ��Sheet����
				Sheet sheet = wb.getSheet(index);
				// sheet.getRows()���ظ�ҳ��������
				boolean Questioning=false;
				
				for (int i = 0; i < sheet.getRows(); i++) {//��
					// sheet.getColumns()���ظ�ҳ��������
					int NumInQuest=0;
					Questioning=false;
					for (int j = 0; j < sheet.getColumns()-1;) {//��
						String cellinfo = sheet.getCell(j, i).getContents();
						if(cellinfo.startsWith("P_")){
							outputData.write("\n\n");
							Questioning=false;
							String title=cellinfo+"      "+sheet.getCell(++j, i).getContents();
							outputData.write(title+ "\n");
							String questId=sheet.getCell(++j, i).getContents();
							if(questId.startsWith("Q_")){//��������Ԫ��
								NumInQuest=0;
								Questioning=true;
								outputData.write(questId+ "\n");
							}
						}
						String cellAfter = sheet.getCell(++j, i).getContents();
						if(Questioning&&cellAfter!=null&&!cellAfter.trim().isEmpty()){//�����һ����ʣ����н���д����ԭ��
							outputData.write(String.valueOf(++NumInQuest)+ "\n");//�����е��������							
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
