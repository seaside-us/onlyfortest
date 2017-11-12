package onlyfortest;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;

/*public class test {

 public static void main(String[] args) {
 // TODO Auto-generated method stub
 System.out.println(convert(123));
 }


 *//**
 * 七进制转十进制
 * <p>
 * 未进行详细测试
 * @param num
 * @return 
 */
/*
 public static int convert(int num){
 int ret=0;
 int count=0;
 while(true){
 if(num!=0){
 ret=(int) (ret+Math.pow(7,count)*(num%10));
 num=num/10;
 count++;
 }else break;

 }
 return ret;
 }

 }
 */

/*import sun.net.TelnetInputStream;
 import sun.net.TelnetOutputStream;*/
//import sun.net.ftp.FtpClient;
/**
 * Created by Administrator on 2017/3/16.
 */

public class CopyOfMain {
	 public static int MoreThanHalfNum_Solution(int [] array) {
		 Queue que;
		 int b=0;
		 
	        int m=0;int count=0;
	        for(int i=0;i<array.length;i++){
	            if(count==0){
	                m=array[i];
	                count++;
	            }else{
	                if(array[i]==m){
	                 	count++;
	            	}else
	                	count--;
	            }
	                
	        }
	         // 判断result是否符合条件，即出现次数大于数组长度的一半
	        int times = 0;
	        for(int i=0;i< array.length;i++)
	        {
	            if(array[i] == m) ++times;
	        }
	         
	        return (times > array.length/2) ? m : 0;
	    }
	public static <E> void main(String[] args) {
		/*Scanner scan=new Scanner(System.in);
		int[] arr=new int[9];int count=0;
		while(scan.hasNextInt()){
			arr[count]=scan.nextInt();
			count++;
			if(count==9)
			System.out.println(MoreThanHalfNum_Solution(arr));
		}*/
		byte i=127;
		System.out.println(++i);System.out.println(++i);

	}

	public static int median3(Integer[] a, int left, int right) {
		Integer center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0) {
			a[center] = a[center] ^ a[left];
			a[left] = a[center] ^ a[left];
			a[center] = a[center] ^ a[left];
		}
		if (a[right].compareTo(a[left]) < 0) {
			a[right] = a[right] ^ a[left];
			a[left] = a[right] ^ a[left];
			a[right] = a[right] ^ a[left];
		}
		if (a[right].compareTo(a[center]) < 0) {
			a[right] = a[center] ^ a[right];
			a[center] = a[center] ^ a[right];
			a[right] = a[center] ^ a[right];
		}

		a[center] = a[center] ^ a[right - 1];
		a[right - 1] = a[center] ^ a[right - 1];
		a[center] = a[center] ^ a[right - 1];

		return a[right - 1];

	}

	static void quickSort2(Integer a[], int left, int right) {

		int pivot;
		if (right - left >= 2)
			pivot = median3(a, left, right);
		else
			pivot = a[left];
		int i = left;
		int j = right - 1;
		for (;;) {
			while (a[++i].compareTo(pivot) < 0) {
			}
			while (a[--j].compareTo(pivot) < 0) {
			}
			if (i < j) {
				a[i] = a[i] ^ a[j];
				a[j] = a[i] ^ a[j];
				a[i] = a[i] ^ a[j];
			} else
				break;
		}

		a[i] = a[i] ^ a[right - 1];
		a[right - 1] = a[i] ^ a[right - 1];
		a[i] = a[i] ^ a[right - 1];

		quickSort2(a, left, i - 1);
		quickSort2(a, i + 1, right);

	}

	/* 快速排序 */
    static int partition(Integer arr[],int low,int high){
        int temp,pivot = arr[high];
         while(low < high){        
             /* 查找顺序要和基准选取方向相反；
              pivot = arr[high] 则必须先从low开始；
               反之 pivot = arr[low]， 则必须先从high开始查找 */
            while(arr[low] <= pivot && low < high)
                low++; 
            arr[high] = arr[low];
            while(arr[high] >= pivot && low < high)
                high--;  
            arr[low] = arr[high];
        }
        // 校准，将基准移至正确位置
        arr[low] = pivot;
        System.out.println("low="+low+";high="+high);
        return low;
    }

     static void  quickSort(Integer []arr,int low,int high){
         if(low < high){
          int pivotpos = partition(arr,low,high);
          quickSort(arr,low,pivotpos-1);
          quickSort(arr,pivotpos+1,high);
         }
    }

}

