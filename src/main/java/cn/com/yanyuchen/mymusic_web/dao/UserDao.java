package cn.com.yanyuchen.mymusic_web.dao;

import cn.com.yanyuchen.mymusic_web.entity.Song;
import cn.com.yanyuchen.mymusic_web.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;


/**
 * 用户User的DAO
 */
public class UserDao {

    /**
     * 根据id获取user实例
     * @param id 检索的id
     * @return 对应的USer实例
     */
    public User getUser(Long id) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        User user = session.get(User.class, id);

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return user;
    }

    /**
     * 根据userName获取user实例
     * @param userName 要检索的userName
     * @return 对应的一个User实例
     * @throws Exception 数据库错误需要抛出
     */
    public User getUser(String userName) throws Exception {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from user where userName = ?", User.class);

        nativeQuery.setParameter(1, userName);

        List<User> users = nativeQuery.getResultList();

        User result;

        if (users.size()==0){
            result =null;
        }else if(users.size()==1){
            result = users.get(0);
        }else{
            throw new Exception();
        }

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 根据userName和email获取user实例
     * @param userName 用户名userName
     * @param email 用户邮箱email
     * @return 符合条件的列表
     */
    public List<User> getUser(String userName, String email) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from user where userName = ? or email = ?", User.class);

        nativeQuery.setParameter(1, userName).setParameter(2, email);

        List<User> result = nativeQuery.getResultList();
        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 获得当前所有的用户列表
     * @return 用户列表
     */
    public List<User> getUsers() {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from user", User.class);

        List<User> result = nativeQuery.getResultList();
        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 增加一个user
     * @param user 要增加的user实例
     * @return 其实不需要返回  但我还是返回了
     */
    public User addUser(User user) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        session.save(user);

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return user;
    }

    /**
     * 更新一个user
     * @param user 要更新的user实例
     * @return 其实不需要返回  但我还是返回了
     */
    public User updateUser(User user) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        session.update(user);

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return user;
    }

}
