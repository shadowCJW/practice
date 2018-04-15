package cn.chenjw.codedemo.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

public class RedisClient {
	private Jedis jedis;// /非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池

	public RedisClient() {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		//jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		jedisPool =new JedisPool(config, "127.0.0.1", 6379, 10000, "123456");
	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);

	}

	public void keyOperate() {
		System.out.println("======================key==========================");
		//jedis.flushDB();// 清空库中数据
		System.out.println("判断key999键是否存在：" + jedis.exists("key999"));
		System.out.println("新增key001,value001键值对："
				+ jedis.set("key001", "value001"));
		System.out.println("判断key001是否存在：" + jedis.exists("key001"));
		System.out.println("=========");
		System.out.println("系统中所有键如下：");
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("=========");
		System.out.println("新增key002,value002键值对："+jedis.set("key002", "value002"));
		System.out.println("系统中删除key002: "+jedis.del("key002"));
        System.out.println("判断key002是否存在："+jedis.exists("key002"));
        System.out.println("=========");
        System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 3));
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        try{ 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e){ 
        } 
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        System.out.println("移除key001的生存时间："+jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
        try{ 
            Thread.sleep(4000); 
        } 
        catch (InterruptedException e){ 
        } 
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
        System.out.println("查看key所储存的值的类型："+jedis.get("key001"));
	}
	public static void main(String[] args) {
		//new RedisClient().keyOperate();
		//new RedisClient().stringOperate();
		//new RedisClient().listOperate();
		//new RedisClient().setOperate();
		//new RedisClient().sortedSetOpserate();
		new RedisClient().hashOperate();
		
	}

	private void hashOperate() {
		jedis.hsetnx("hash", "key1", "v1");
		jedis.hsetnx("hash", "key2", "v2");
		jedis.hsetnx("hash", "key3", "v3");
		jedis.hsetnx("hash", "key4", "v4");
		jedis.hdel("hash", "key5");
		System.out.println("新增key5和4的整形对："+jedis.hincrBy("hash", "key5", 5));//也算是修改吧
		System.out.println("所有值："+jedis.hvals("hash"));
		System.out.println("所有的key："+jedis.hkeys("hash"));
		
		System.out.println("删除key4"+jedis.hdel("hash", "key4"));
		System.out.println("所有值："+jedis.hvals("hash"));
		
		System.out.println("pan判断key6是否存在："+jedis.hexists("hash", "key6"));
		
		System.out.println("获取key1he key2d的值"+jedis.hmget("hash", "key1","key2"));
		
	}

	private void sortedSetOpserate() {
		jedis.zadd("zset", 7.0, "z1");
		jedis.zadd("zset", 8.0, "z2");
		jedis.zadd("zset", 4.0, "z3"); 
		jedis.zadd("zset", 6.0, "z4");
		System.out.println("zset集合中的所有元素："+jedis.zrange("zset", 0, -1));//按照权重值排序
		jedis.zrem("zset", "z4");
		System.out.println("zset集合中的所有元素："+jedis.zrange("zset", 0, -1));//按照权重值排序
		System.out.println("zset中的个数："+jedis.zcard("zset"));
		System.out.println("统计摸个权重范围的个数："+jedis.zcount("zset", 2.0, 7.0));
		System.out.println("c查看z3的权重："+jedis.zscore("zset", "z3"));
	}

	private void setOperate() {
		jedis.sadd("sets", "s1");
		jedis.sadd("sets", "s2");
		jedis.sadd("sets", "s3");
		System.out.println("查看sets集合中的所有元素:"+jedis.smembers("sets"));
		jedis.srem("sets", "s3");
		System.out.println("查看删除s3后sets集合中的所有元素:"+jedis.smembers("sets"));
		System.out.println("判断s4是否在sets里面:"+jedis.sismember("sets", "s4"));
		//bainli
		Set<String> set = jedis.smembers("sets");
		
		System.out.println("sets2中添加元素："+jedis.sadd("sets2", "s1")); 
	    System.out.println("sets2中添加元素："+jedis.sadd("sets2", "s5"));
	    System.out.println("set:"+jedis.smembers("sets"));
	    System.out.println("set2:"+jedis.smembers("sets2"));
	    System.out.println("sets和sets2交集："+jedis.sinter("sets", "sets2"));
        System.out.println("sets和sets2并集："+jedis.sunion("sets", "sets2"));
        System.out.println("sets和sets2差集："+jedis.sdiff("sets", "sets2"));//sets中有而sets2没有的值
        
	}

	private void listOperate() {
	    jedis.del("list");
		jedis.lpush("list", "aa");
		jedis.lpush("list", "bb");
		jedis.lpush("list", "cc");
		jedis.lpush("list", "dd");
		jedis.lpush("list", "ee");
		System.out.println("listd的所有元素："+jedis.lrange("list", 0, -1));
		System.out.println("删除一个元素后："+jedis.lrem("list", 1, "aa"));
		System.out.println("listd的所有元素："+jedis.lrange("list", 0, -1));
		 // 删除区间以外的数据 
		System.out.println("删除下标0-3区间之外的元素："+jedis.ltrim("list", 0, 2));
		System.out.println("listd的所有元素："+jedis.lrange("list", 0, -1));
		
		// 修改列表中指定下标的值 
		jedis.lset("list", 0, "eeeee");
		System.out.println("修改下标wei0的值："+jedis.lrange("list", 0, 0));
		System.out.println("数组长度："+jedis.llen("list"));
		
		// 排序 
        /*
         * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
         * 会出现"ERR One or more scores can't be converted into double"
         */
		SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0,3);//排序后显示前三个
        System.out.println("排序后："+jedis.sort("list", sortingParameters));
        
        // 获取列表指定下标的值 
        System.out.println("获取下标为2的元素："+jedis.lindex("list", 2)+"\n");
	}

	private void stringOperate() {
		//删除
		jedis.set("aa", "aa");
		System.out.println("删除aa前："+jedis.get("aa"));
		jedis.del("aa");
		System.out.println("删除aa后："+jedis.get("aa"));
		//修改，一般set多一次原来的只就会修改
		jedis.set("aa", "aa");
		System.out.println("追加aa前："+jedis.get("aa"));
		jedis.append("aa","+xxxxx");
		System.out.println("追加aa后："+jedis.get("aa"));
		//同时新增修改和查询多个mset，mget
		jedis.mset("b","bb","c","cc");
		System.out.println("一次性取出吧，b，c:"+jedis.mget("b","c"));
		jedis.del(new String[]{"b","c"});
		System.out.println("一次性删除b，c:"+jedis.mget("b","c"));
		
		//新增键值对时防止覆盖原先值
		System.out.println("setNx aa前："+jedis.get("aa"));
		jedis.setnx("aa", "aa666");
		System.out.println("setNx aa后："+jedis.get("aa"));
		
		System.out.println("=============超过有效期键值对被删除=============");
        // 设置key的有效期，并存储数据 
		 System.out.println("新增key303，并指定过期时间为2秒"+jedis.setex("key303", 2, "key303-2second"));
		 System.out.println("获取key303对应的值："+jedis.get("key303")); 
	        try{ 
	            Thread.sleep(3000); 
	        } 
	        catch (InterruptedException e){ 
	        } 
	        System.out.println("3秒之后，获取key303对应的值："+jedis.get("key303")); 
		
	        System.out.println("=============获取原值，更新为新值一步完成=============");
	        System.out.println("key302原值："+jedis.getSet("key302", "value302-after-getset"));
	        System.out.println("key302新值："+jedis.get("key302"));
	        
	        System.out.println("=============获取子串=============");
	        System.out.println("获取key302对应值中的子串："+jedis.getrange("key302", 5, 7)); 
		
	}

}
