package com.redis.connectpool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisPool {
	private static JedisPool pool = null;

	public static void main(String[] args) {
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.set("lnp8999","qwerfd6");
		jedis.expire("lnp8999999", 10);
		jedis.close();
		JedisUtil.shutdownJedisPool();
	}

}
