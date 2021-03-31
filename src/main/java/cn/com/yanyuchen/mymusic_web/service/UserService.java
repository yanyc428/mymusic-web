package cn.com.yanyuchen.mymusic_web.service;


import cn.com.yanyuchen.mymusic_web.dao.UserDao;
import cn.com.yanyuchen.mymusic_web.entity.User;

import java.util.List;

/**
 *用户的service
 */
public class UserService {


    /**
     * 实例化dao
     */
    private final UserDao userDao = new UserDao();

    /**
     * 根据id返回user
     * @param id 用户id
     * @return user实例
     */
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * 根据用户名返回user
     * @param userName 用户名
     * @return user实例
     * @throws Exception 数据库查询异常要抛出  后续捕捉
     */
    public User getUser(String userName) throws Exception {
        return userDao.getUser(userName);
    }

    /**
     * 根据userName和password来获取user
     * @param userName 用户名
     * @param password 密码
     * @return 符合条件的user实例
     */
    public User checkUser(String userName, String password) {
        // 这里是一个列表  用来遍历
        List<User> users = userDao.getUser(userName, userName);
        for (User user :
                users) {
            // 检查密码
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 注册用户
     * @param user 要注册的user实例
     * @return 是否注册成功  成功返回true
     */
    public boolean registerUser(User user) {
        try {
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新用户
     * @param user 要更新的user实例
     * @return 是否更新成功  成功返回true
     */
    public boolean updateUser(User user) {
        try {
            userDao.updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到用户列表
     * @return 用户列表
     */
    public List<User> getUsers() {
        try {
            return userDao.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
