package cn.com.yanyuchen.mymusic_web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 基础DAO  用来生成数据库配置文件  和产生数据库session对象
 */
public class BaseDao {

    // 配置
    private static final Configuration configuration = new Configuration().configure();
    //session工厂
    private static final SessionFactory sessionFactory = configuration.buildSessionFactory();

    /**
     * 拿到一个session
     * @return session对象
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
