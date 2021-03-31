package cn.com.yanyuchen.mymusic_web.service;


import cn.com.yanyuchen.mymusic_web.dao.SongDao;
import cn.com.yanyuchen.mymusic_web.dao.UserDao;
import cn.com.yanyuchen.mymusic_web.entity.Song;
import cn.com.yanyuchen.mymusic_web.entity.User;

import java.util.List;

/**
 * 歌曲的service
 */
public class SongService {

    /**
     * 实例化dao
     */
    private final SongDao songDao = new SongDao();
    private final UserDao userDao = new UserDao();

    /**
     * 获得指定数目的随即歌曲
     *
     * @param num  数目
     * @param user 当前的用户
     * @return 歌曲列表
     */
    public List<Song> getRandomSongs(int num, User user) {
        List<Song> songList = songDao.getSongByNum(num);
        // 如果user是null 就不用检查是否喜欢  否则循环遍历设置like属性
        if (user != null) {
            for (Song song :
                    songList) {
                List<User> users = song.getUsers();
                for (User u :
                        users) {
                    if (u.getId().equals(user.getId())) {
                        song.setLike(true);
                        // 找到了就可以退出循环了
                        break;
                    }
                }
            }

        }
        return songList;
    }

    /**
     * 喜欢一首歌曲
     *
     * @param userId 用户的id
     * @param songId 歌曲的id
     * @return 是否喜欢成功  成功返回true
     */
    public boolean loveASong(Long userId, Long songId) {
        /*
        先拿到实体
        user 和song
         */
        User user = userDao.getUser(userId);
        Song song = songDao.getSong(songId);

        // 遍历  如果已经喜欢了  就返回flase
        for (Song s :
                user.getSongs()) {
            if (s.getId().equals(songId)) {
                return false;
            }
        }
        /*
        设置user对象中的songs
        利用hibernate的功能直接更新
         */
        List<Song> songs = user.getSongs();
        songs.add(song);
        user.setSongs(songs);
        userDao.updateUser(user);
        return true;
    }

    /**
     * 取消喜欢一首歌曲
     *
     * @param userId 用户id
     * @param songId 歌曲id
     * @return 取消成功返回true
     */
    public boolean unloveASong(Long userId, Long songId) {
        // 拿到用户喜欢的歌曲
        User user = userDao.getUser(userId);
        List<Song> songs = user.getSongs();

        // 遍历 找到对应的歌曲  并取消喜欢
        for (Song s :
                songs) {
            if (s.getId().equals(songId)) {
                /*
                设置user对象中的songs
                利用hibernate的功能直接更新
                 */
                songs.remove(s);
                user.setSongs(songs);
                userDao.updateUser(user);
                return true;
            }
        }
        // 遍历一遍都没找到  返回false
        return false;
    }

    /**
     * 获取一个user喜欢的歌曲
     * @param userId user的id
     * @param start 开始index
     * @param end 结束index
     * @return 歌曲列表
     */
    public List<Song> getLovedSongs(Long userId, Integer start, Integer end) {
        // 拿出用户喜欢的歌曲
        User user = userDao.getUser(userId);
        List<Song> songs = user.getSongs();
        // 设置一下like
        for (Song s :
                songs) {
            s.setLike(true);
        }
        // 返回一个切片
        return songs.subList(start, end);
    }

    /**
     * 喜欢歌曲的数目
     * @param userId 用户id
     * @return 数目
     */
    public Integer countLovedSongs(Long userId) {
        // 比较简单 就不注释了
        User user = userDao.getUser(userId);
        List<Song> songs = user.getSongs();
        return songs.size();
    }

    /**
     * 根据检索词获取歌曲
     * @param word 检索词
     * @param user 当前user
     * @param page 页码
     * @return 歌曲列表
     */
    public List<Song> getSongs(String word, User user, Integer page) {
        // 计算一下开始index
        Integer start = (page - 1) * 10;
        // 拿到对应的歌曲列表
        List<Song> songs = songDao.getSongs(word, start, 10);
        // 如果没登陆就不设置like属性  否则根据user来查询一次并设置
        if (user != null) {
            for (Song song :
                    songs) {
                List<User> users = song.getUsers();
                for (User u :
                        users) {
                    if (u.getId().equals(user.getId())) {
                        song.setLike(true);
                        // 提前退出循环
                        break;
                    }
                }
            }

        }
        return songs;
    }

    /**
     * 根据检索词的歌曲数目
     * @param word 检索词
     * @return 歌曲的数目
     */
    public Integer countSongs(String word) {
        return songDao.countSongs(word);
    }

}
