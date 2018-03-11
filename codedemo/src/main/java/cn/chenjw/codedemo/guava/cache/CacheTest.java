package cn.chenjw.codedemo.guava.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheTest {
	/**
	 * expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
	 * expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
	 * refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
	 */

	final static Cache<String, Object> lc = CacheBuilder.newBuilder()
			.maximumSize(100).expireAfterWrite(10, TimeUnit.SECONDS).build();
	final static String key = "time";

	public static void main(String[] args) {
		Object s = null;
		for (int i = 0; i < 10; i++) {
			try {
				s = lc.get(key, new Callable<Object>() {

					@Override
					public Object call() throws Exception {
						return getQueryData(key);
					}
				});
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			s = lc.asMap().get(key);
			System.out.println("打印" + "结果为：" + s);
			try {
				Thread.sleep(2000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	protected static String getQueryData(String key) {
		if (key.endsWith(CacheTest.key)) {

			try {
				Thread.sleep(3000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("不使用缓存，重新查询");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			lc.put(key, date);
			return date;
		}
		return "key不存在！";
	}

}
