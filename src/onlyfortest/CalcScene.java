package onlyfortest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class CalcScene {
	static String[] data = new String[44];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("F:/bjut_ai_maya/scene.txt");
		int count = 1;
		double centerX = 0, centerY = 0, centerZ = 0, halfX = 0, halfY = 0, halfZ = 0;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while ((line = buffer.readLine()) != null) {
				String tmp = count + ".	";
				if (line.startsWith(tmp)) {
					/*if(count==26){
						System.out.println("debug");
					}
					System.out.println(count);*/
					String line1 = buffer.readLine();
					String line2 = buffer.readLine();
					String[] data1 = line1.split(" ");
					String[] data2 = line2.split(" ");
					centerX = (Double.valueOf(data1[0]) + Double.valueOf(data2[0]))/2;
					centerY = (Double.valueOf(data1[1]) + Double.valueOf(data2[1]))/2;
					centerZ = (Double.valueOf(data1[2]) + Double.valueOf(data2[2]))/2;
					halfX = (Math.abs(Double.valueOf(data1[0])) + Math.abs(Double.valueOf(data2[0]))) / 2;
					halfY = (Math.abs(Double.valueOf(data1[1])) + Math.abs(Double.valueOf(data2[1]))) / 2;
					halfZ = (Math.abs(Double.valueOf(data1[2])) + Math.abs(Double.valueOf(data2[2]))) / 2;

					data[count - 1] = line+ ":" +String.valueOf(centerX) + "  " + String.valueOf(centerY) + "  "
							+ String.valueOf(centerZ) + "  " + String.valueOf(halfX) + "  " + String.valueOf(halfY) + "  "
							+ String.valueOf(halfZ);
					count++;
					line = buffer.readLine();
				}

			}
			buffer.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			int i = 0;
			while (i < 44) {
				writer.write(data[i]+"\r");
				i++;
			}
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
