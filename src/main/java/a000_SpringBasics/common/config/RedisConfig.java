package a000_SpringBasics.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/11/25 Tuesday
 **/

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    private final RedisProperties redisProperties;

    public RedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @PostConstruct
    public void printRedisProperties() {
        System.out.println("Redis Properties:");

        // 基本连接信息
        System.out.println("Host: " + redisProperties.getHost());
        System.out.println("Port: " + redisProperties.getPort());
        System.out.println("Password: " + redisProperties.getPassword());
        System.out.println("Database: " + redisProperties.getDatabase());
        System.out.println("Timeout: " + redisProperties.getTimeout());

        // 集群信息
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (cluster != null) {
            System.out.println("Cluster Enabled: true");
            List<String> nodes = cluster.getNodes();
            if (nodes != null && !nodes.isEmpty()) {
                System.out.println("Cluster Nodes:");
                for (String node : nodes) {
                    System.out.println("  - " + node);
                }
            }
            System.out.println("Cluster Max Redirects: " + cluster.getMaxRedirects());
        } else {
            System.out.println("Cluster Enabled: false");
        }

        // Sentinel 信息
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        if (sentinel != null) {
            System.out.println("Sentinel Enabled: true");
            System.out.println("Sentinel Master: " + sentinel.getMaster());
            List<String> nodes = sentinel.getNodes();
            if (nodes != null && !nodes.isEmpty()) {
                System.out.println("Sentinel Nodes:");
                for (String node : nodes) {
                    System.out.println("  - " + node);
                }
            }
        } else {
            System.out.println("Sentinel Enabled: false");
        }

        // Lettuce 连接池信息
        RedisProperties.Lettuce lettuce = redisProperties.getLettuce();
        if (lettuce != null) {
            System.out.println("Lettuce Connection Pool Enabled: true");
            RedisProperties.Pool pool = lettuce.getPool();
            if (pool != null) {
                System.out.println("  Lettuce Pool Max Active: " + pool.getMaxActive());
                System.out.println("  Lettuce Pool Max Idle: " + pool.getMaxIdle());
                System.out.println("  Lettuce Pool Min Idle: " + pool.getMinIdle());
                System.out.println("  Lettuce Pool Max Wait: " + pool.getMaxWait());
            }
        } else {
            System.out.println("Lettuce Connection Pool Enabled: false");
        }

        // Jedis 连接池信息
        RedisProperties.Jedis jedis = redisProperties.getJedis();
        if (jedis != null) {
            System.out.println("Jedis Connection Pool Enabled: true");
            RedisProperties.Pool pool = jedis.getPool();
            if (pool != null) {
                System.out.println("  Jedis Pool Max Active: " + pool.getMaxActive());
                System.out.println("  Jedis Pool Max Idle: " + pool.getMaxIdle());
                System.out.println("  Jedis Pool Min Idle: " + pool.getMinIdle());
                System.out.println("  Jedis Pool Max Wait: " + pool.getMaxWait());
            }
        } else {
            System.out.println("Jedis Connection Pool Enabled: false");
        }
        System.out.println(redisProperties.getHost());
        System.out.println(redisProperties.getPort());
    }


    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(
                new RedisClusterConfiguration(redisProperties.getCluster().getNodes()),
//                new RedisStandaloneConfiguration(redisProperties.getHost(),redisProperties.getPort())
                jedisPoolConfig
        );
        return jedisConnectionFactory;
    }

}
