package a000_SpringBasics.service;

import io.lettuce.core.protocol.RedisCommand;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Chenyu Liu
 * @since 3/11/25 Tuesday
 **/

@Service
public class RedisService {

    @Autowired
    private JedisConnectionFactory redisConnectionFactory;

    @PostConstruct
    public String clusterInfo() {
        try(RedisConnection clusterConnection = redisConnectionFactory.getConnection()) {
            System.out.println(clusterConnection.ping());
            String random = String.valueOf(Math.random());
            clusterConnection.execute("SET", "james".getBytes(), random.getBytes());
        }
        return "2";
    }

    public String getKey(String key){
        try(RedisConnection connection = redisConnectionFactory.getConnection()){
            Object get = Objects.requireNonNull(connection.execute("GET", key.getBytes()));
            return String.valueOf(get);
        }
    }

    public void setKey(String key,String value){
        try(RedisConnection connection = redisConnectionFactory.getConnection()){
            Objects.requireNonNull(connection.execute("SET", key.getBytes(),value.getBytes()));
        }
    }
}
