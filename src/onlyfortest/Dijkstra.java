package onlyfortest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dijkstra {
	static int M = 10000000;// (此路不通)
	static int size = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 邻接矩阵
		int[][] graph = new int[5][5];
		String[] stations= new String[5];
		File file = new File("F:/bjut_ai_maya/subway.txt");
		int count = 0;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// 每行数据分割后数组第一个元素为站名,but 第一行全是站名
				if (count == 0) {// 第一行为所有站点名
					System.out.println("第" + count + "行：" + line);
					for(int i=0;i<lineData.length;i++){
						stations[i]=lineData[i].trim();
					}
				} else {
					for (int i = 1; i < lineData.length; i++) {
						int dis = Integer.valueOf(lineData[i]);
						if (dis == -1) {
							graph[count - 1][i - 1] = M;
						} else
							graph[count - 1][i - 1] = dis;
					}
				}
				count++;
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < size; i++) {
			int[] shortPath = new int[size];
			shortPath = Dijsktra(graph, i);
			for (int m = 0; m < shortPath.length; m++)
				System.out.println("从" + stations[i] + "出发到" + stations[m] + "的最短距离为：" + shortPath[m]);
		}

	}

	public static int[] Dijsktra(int[][] weight, int start) {
		// 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
		// 返回一个int[] 数组，表示从start到它的最短路径长度
		int n = weight.length; // 顶点个数
		int[] shortPath = new int[n]; // 存放从start到其他各点的最短路径
		String[] path = new String[n]; // 存放从start到其他各点的最短路径的字符串表示
		for (int i = 0; i < n; i++)
			path[i] = new String(start + "-->" + i);
		int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出

		// 初始化，第一个顶点求出
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count <= n - 1; count++) // 要加入n-1个顶点
		{
			int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			System.out.println("k=" + k);
			// 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
			shortPath[k] = dmin;
			visited[k] = 1;
			// 以k为中间点，修正从start到未访问各点的距离
			for (int i = 0; i < n; i++) { // System.out.println("k="+k);
				if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}
		for (int i = 0; i < n; i++)
			System.out.println("从" + start + "出发到" + i + "的最短路径为：" + path[i]);
		System.out.println("=====================================");

		return shortPath;
	}
}