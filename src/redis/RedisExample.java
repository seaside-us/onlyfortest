package redis;

import java.util.concurrent.locks.ReadWriteLock;

import redis.clients.jedis.Jedis;

public class RedisExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Jedis jedis=new Jedis("127.0.0.1",6379);
			jedis.auth("root");
			 //�����ַ�������  
	        jedis.set("myKey", "testStr");  
	        //ͨ��key�����������  
	        ReadWriteLock a;
	        System.out.println("�������Ϊ��" + jedis.get("myKey"));  
			System.out.println("ping:"+jedis.ping());
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
