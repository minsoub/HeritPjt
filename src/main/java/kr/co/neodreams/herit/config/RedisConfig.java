package kr.co.neodreams.herit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;*/

/*import redis.clients.jedis.JedisPoolConfig;*/

@Configuration
public class RedisConfig {

	/**
	 * Redis Cluster 구성 설정
	 */
	@Autowired
	private RedisClusterConfigurationProperties clusterProperties;
	
	/**
	 * JedisPool 관련 설정
	 * 
	 * @return
	 */
	/*
	 * @Bean public JedisPoolConfig jedisPoolConfig() { return new
	 * JedisPoolConfig(); }
	 */
	/**
	 * Redis Cluster 구성 설정 - Cluster 구성
	 * 
	 * @param jedisPoolConfig
	 * @return
	 */
	/*
	 * @Bean public RedisConnectionFactory jedisConnectionFactory(JedisPoolConfig
	 * jedisPoolConfig) { return new JedisConnectionFactory(new
	 * RedisClusterConfiguration(clusterProperties.getNodes()), jedisPoolConfig); }
	 */
}
