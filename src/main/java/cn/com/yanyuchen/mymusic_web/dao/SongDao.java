package cn.com.yanyuchen.mymusic_web.dao;


import cn.com.yanyuchen.mymusic_web.entity.Song;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * 歌曲song的DAO
 */
public class SongDao {

    /**
     * 获取指定数目的随即歌曲列表
     *
     * @param number 指定的数目
     * @return 歌曲列表
     */
    public List<Song> getSongByNum(int number) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<Song> nativeQuery = session.createNativeQuery("select * from song where source<2 ORDER BY RAND() LIMIT ?", Song.class);


        nativeQuery.setParameter(1, number);

        List<Song> result = nativeQuery.getResultList();
        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 根据歌曲id获取song实例
     *
     * @param id 歌曲id
     * @return 对应的Song实例
     */
    public Song getSong(Long id) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        Song song = session.get(Song.class, id);

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return song;
    }

    /**
     * 根据词语来检索歌曲列表
     *
     * @param word  检索词
     * @param start 开始的索引
     * @param max   每一页的数目
     * @return 对应的歌曲列表
     */
    public List<Song> getSongs(String word, Integer start, Integer max) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<Song> nativeQuery = session.createNativeQuery("select * from song where name like ? or album like ?", Song.class);


        nativeQuery.setParameter(1, "%" + word + "%").setParameter(2, "%" + word + "%").setFirstResult(start).setMaxResults(max);

        List<Song> result = nativeQuery.getResultList();

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 返回指定检索词的歌曲数目
     *
     * @param word 检索词
     * @return 歌曲的数目
     */
    public Integer countSongs(String word) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<Song> nativeQuery = session.createNativeQuery("select * from song where name like ? or album like ?", Song.class);


        nativeQuery.setParameter(1, "%" + word + "%").setParameter(2, "%" + word + "%");

        List<Song> result = nativeQuery.getResultList();

        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result.size();
    }


}
