package com.rexen.rest.app.cache;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author GavinHacker
 * @since Created in 下午3:36 2019/4/19
 */
public class SessionCacheManager {

    static Logger logger = Logger.getLogger(SessionCacheManager.class);

    public static void setAttribute(String key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            logger.info("NOTE:session default timeout is [" + session.getTimeout() + "] milliseconds.");
            session.setAttribute(key, value);
        }
    }

    public static Object getAttribute(String key){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            logger.info("NOTE:session default timeout is [" + session.getTimeout() + "] milliseconds.");
            return session.getAttribute(key);
        }
        return null;
    }
}
