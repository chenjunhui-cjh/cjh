package com.rexen.rest.app.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.apache.commons.lang3.SerializationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author GavinHacker
 * @since Created in 下午3:57 2019/4/18
 */
@Component
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    /**
     * session 在redis过期时间是30分钟30*60
     * */
    private static int expireTime = 1800;

    private static String prefix = "rest-shiro-session:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建session，保存到数据库
     * */
    @Override
    protected Serializable doCreate(Session session) {
        Session session1 = null;
        if(session != null && session.getId() != null) {
            session1 = doReadSession(session.getId());
            logger.info("Session exist.");
            return session1.getId();
        }
        Serializable sessionId = this.generateSessionId(session);
        logger.debug("创建session:{}", sessionId);
        this.assignSessionId(session, sessionId);
        String key = prefix + sessionId.toString();
        redisTemplate.opsForValue().set(key, session);
        redisTemplate.expire(key, (session.getTimeout()/1000)*2, TimeUnit.SECONDS);
        return sessionId;
    }

    /**
     * 获取session
     * */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        logger.debug("获取session开始:{}", sessionId);
        try {
            session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
//            logger.debug("获取session结束:{}", session.getId());
        }catch (Exception e){
            logger.warn("Read session exception:", e);
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            throw new UnknownSessionException("session or session id is null");
        }
        logger.debug("更新session:{}", session.getId());
        String key = prefix + session.getId().toString();
        redisTemplate.opsForValue().set(key, session);
        redisTemplate.expire(key, (session.getTimeout()/1000)*2, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        logger.debug("删除session:{}", session.getId());
        redisTemplate.delete(prefix + session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        try {
            Set<String> keys = redisTemplate.keys("*");
            if (keys != null && keys.size() > 0) {
                for (String key:keys) {
                    Session s = (Session) redisTemplate.opsForValue().get(key);
                    sessions.add(s);
                }
            }
        } catch (SerializationException e) {
            logger.error("get active sessions error.");
        }
        return sessions;
    }
}
