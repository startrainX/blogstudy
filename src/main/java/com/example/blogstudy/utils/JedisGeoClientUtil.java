package com.example.blogstudy.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

@Component
public class JedisGeoClientUtil {

    @Autowired
    private JedisPool jedisPool;

    public void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * @Description:添加地理位置的坐标
     * @Param: [坐标类型，位置经度，位置纬度，位置信息]
     * @Return: java.lang.Long
     * @Author: CCA
     * @Date: 2021/2/24 10:15
     */
    public Long geoAdd(String key, double lng, double lat, String memberName) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, lng, lat, memberName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }


    /**
     * @Description:批量添加地理位置的坐标
     * @Param: [坐标类型, 批量添加的数据<位置信息，位置的经纬度>]
     * @Return: java.lang.Long
     * @Author: CCA
     * @Date: 2021/2/24 10:24
     */
    public Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * @Description:根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度+匹配位置信息与给定地理位置的距离+从近到远排序）
     * @Param: [坐标类型, 经度, 纬度, 范围（m）]
     * @Return: java.util.List<redis.clients.jedis.GeoRadiusResponse>
     * @Author: CCA
     * @Date: 2021/2/24 10:52
     */
    public List<GeoRadiusResponse> geoRadius(String key, double lng, double lat, double radius) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadius(key, lng, lat, radius, GeoUnit.M, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * @Description:根据给定地理位置信息获取指定范围内的地理位置集合（返回匹配位置的经纬度+匹配位置信息与给定地理位置的距离+从近到远排序）
     * @Param: [坐标类型, 位置信息, 范围（m）]
     * @Return: java.util.List<redis.clients.jedis.GeoRadiusResponse>
     * @Author: CCA
     * @Date: 2021/2/24 11:02
     */
    public List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadiusByMember(key, member, radius, GeoUnit.M, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * @Description:根据坐标类型返回所有地理位置信息
     * @Param: [坐标类型]
     * @Return: java.util.List<redis.clients.jedis.GeoRadiusResponse>
     * @Author: CCA
     * @Date: 2021/2/24 13:18
     */
    public List<GeoRadiusResponse> geoRadiusByAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadius(key, 119.712100625D, 29.0977551713D, 1000, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * @Description:删除指定坐标类型
     * @Param: [坐标类型]
     * @Return: java.lang.Long
     * @Author: CCA
     * @Date: 2021/2/24 11:19
     */
    public Long geoDel(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                return jedis.del(key);
            }
            return 1L;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * @Description:删除指定坐标类型下的位置信息
     * @Param: [坐标类型, 位置信息]
     * @Return: java.lang.Long
     * @Author: CCA
     * @Date: 2021/2/24 11:22
     */
    public Long geoDel(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                return jedis.zrem(key, member);
            }
            return 1L;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return null;
    }
}
