package kr.co.neodreams.herit.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis Cluster Config
 *  
 * @author JMS
 *
 */
@Component
/* @ConfigurationProperties(prefix="spring.redis.cluster") */
public class RedisClusterConfigurationProperties {
	/**
	 * spring.redis.cluster.nodes[0]=127.0.0.1:6379
	 */
	private List<String> nodes;
	
	public List<String> getNodes()
	{
		return nodes;
	}
	public void setNodes(List<String> nodes)
	{
		this.nodes = nodes;
	}
}
