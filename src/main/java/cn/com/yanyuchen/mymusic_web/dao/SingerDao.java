package cn.com.yanyuchen.mymusic_web.dao;

import cn.com.yanyuchen.mymusic_web.entity.Singer;
import cn.com.yanyuchen.mymusic_web.enumItem.Area;
import cn.com.yanyuchen.mymusic_web.enumItem.Platform;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * 歌手表的DAO
 */
public class SingerDao {


    /**
     * 获取歌手列表
     *
     * @param area        地区
     * @param firstLetter 首字母
     * @param platform    平台
     * @param start       开始的索引
     * @param max         每一页的item数
     * @return 歌手列表
     */
    public List<Singer> getSingers(Area area, String firstLetter, Platform platform, int start, int max) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<Singer> nativeQuery = session.createNativeQuery("select * from singer where type=? and firstLetter=? and source=?", Singer.class);

        nativeQuery.setParameter(1, area.number()).setParameter(2, firstLetter).setParameter(3, platform.ordinal()).setFirstResult(start).setMaxResults(max);

        List<Singer> result = nativeQuery.getResultList();
        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }

    /**
     * 获取歌手数目
     *
     * @param area        地区
     * @param firstLetter 首字母
     * @param platform    平台
     * @return 数目
     */
    public long countSingers(Area area, String firstLetter, Platform platform) {
        Session session = BaseDao.getSession();
        Transaction tr = session.beginTransaction();
        ////////////start sql //////////////

        NativeQuery<Singer> nativeQuery = session.createNativeQuery("select * from singer where type=? and firstLetter=? and source=?", Singer.class);

        nativeQuery.setParameter(1, area.number()).setParameter(2, firstLetter).setParameter(3, platform.ordinal());

        long result = nativeQuery.stream().count();
        ////////////end  sql //////////////
        tr.commit();
        session.close();
        return result;
    }
}
