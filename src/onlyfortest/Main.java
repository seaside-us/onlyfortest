package onlyfortest;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Main {
	public static void main(String[] args) {
		String a="a";
		final String b="a";
		String c=a+"b";
		String d=b+"b";
		String e="ab";
		System.out.println(c==e);
		System.out.println(c==d);
		System.out.println(a==b);
		Executors ha;
	}
	LinkedHashMap e;
	Executor ad=Executors.newCachedThreadPool();
	AbstractQueuedSynchronizer a;//
	//递归创建节点，返回下一层递归的节点作为该层递归的左右节点
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		TreeNode t=new TreeNode(0);
		if(t1!=null||t2!=null){
			t.val=(t1!=null?t1.val:0)+(t2!=null?t2.val:0);
			t.left=mergeTrees(t1!=null?t1.left:null,t2!=null?t2.left:null);
			t.right=mergeTrees(t1!=null?t1.right:null,t2!=null?t2.right:null);
		}else 
			t=null;
		return t;
	}

}
