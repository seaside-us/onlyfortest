package onlyfortest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Copy_2_of_Main {
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		int n = scanner.nextInt();
//		int m = scanner.nextInt();
//		int arr[] = new int[m];
//		for (int i = 0; i < m; i++) {
//			arr[i] = scanner.nextInt();
//		}
//
//		process(n, arr);
	String 	tmp="a";
	String tmp1="a"+"n";
	System.out.println(tmp==tmp1);
	}

	/**
	 * n是kolakoski序列需要输出的前n个数， m 是有m个数
	 * @param n
	 */
	public static void process(int n, int[] arr) {
		int[] table = new int[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int x : arr) {
			queue.add(x);
		}
		int i = 0;
		int group = 0;
		while (i < n) {
			int cur = queue.poll();
			queue.add(cur);
			if (table[group] == 0)
				table[group] = cur;
			int count = table[group];
			for (int j = 0; j < count; j++) {
				table[i] = cur;
				i++;
			}
			group++;
		}
		for(int k=0;k<n;k++){
			System.out.println(table[k]);
		}
	}
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	public static void resolve(TreeNode node,int sum){
		if(node!=null){
			sum-=node.val;
			resolve(node.left,sum);
			resolve(node.right,sum);
		}
	}
}