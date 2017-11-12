package redis;

import java.util.concurrent.locks.ReadWriteLock;

import redis.clients.jedis.Jedis;

public class RedisExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Jedis jedis=new Jedis("127.0.0.1",6379);
			jedis.auth("root");
			 //设置字符串数据  
	        jedis.set("myKey", "testStr");  
	        //通过key输出缓存内容  
	        ReadWriteLock a;
	        System.out.println("输出内容为：" + jedis.get("myKey"));  
			System.out.println("ping:"+jedis.ping());
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
