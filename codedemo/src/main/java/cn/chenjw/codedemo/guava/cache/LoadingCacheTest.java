package cn.chenjw.codedemo.guava.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LoadingCacheTest {
	static LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
			.maximumSize(100).expireAfterWrite(10, TimeUnit.SECONDS)
			.build(new CacheLoader<String, Object>() {

				@Override
				public Object load(String key) throws Exception {
					return createExpensiveGraph(key);
				}

				private Object createExpensiveGraph(String key) {
					if (key.equalsIgnoreCase("key")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String date = sdf.format(new Date());
						return "cache value:" + date;
					} else {
						return "null";
					}

				}
			});

	public static void main(String[] args) {

		String result = null;
		try {
			for (int i = 0; i < 10; i++) {

				result = cache.get("key").toString();
				System.out.println(result + "<<<输出结果");
				Thread.sleep(2000);
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
