package cn.com.yanyuchen.mymusic_web.service;

import cn.com.yanyuchen.mymusic_web.dao.SingerDao;
import cn.com.yanyuchen.mymusic_web.entity.Singer;
import cn.com.yanyuchen.mymusic_web.enumItem.Area;
import cn.com.yanyuchen.mymusic_web.enumItem.Platform;

import java.util.List;

/**
 * 歌手的service
 */
public class SingerService {

    /**
     * 实例化dao
     */
    private final SingerDao singerDao = new SingerDao();

    /**
     * 获取歌手列表
     *
     * @param gender      性别
     * @param firstLetter 首字母
     * @param platform    平台
     * @param type        类型
     * @param page        页码
     * @return 歌手列表
     */
    public List<Singer> getSingers(String gender, String firstLetter, String platform, String type, int page) {
        // 根据参数得到area的枚举 对应type字段
        Area area = Area.getEnumType(Integer.parseInt(gender) + Integer.parseInt(type) * 10);
        Platform plat;
        if (platform.equals("0")) {
            plat = Platform.QQMusic;
        } else if (platform.equals("1")) {
            plat = Platform.WangYiYunMusic;
        } else {
            plat = Platform.KuGouMusic;
        }
        // 获取开始的索引
        int start = (page - 1) * 100;
        // 数据库开始查询
        try {
            return singerDao.getSingers(area, firstLetter, plat, start, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取歌手的数目  具体注释同上  这里不再注释
     *
     * @param gender      性别
     * @param firstLetter 首字母
     * @param platform    平台
     * @param type        类型
     * @return 歌手的数目
     */
    public long countSingers(String gender, String firstLetter, String platform, String type) {
        Area area = Area.getEnumType(Integer.parseInt(gender) + Integer.parseInt(type) * 10);
        Platform plat;
        if (platform.equals("0")) {
            plat = Platform.QQMusic;
        } else if (platform.equals("1")) {
            plat = Platform.WangYiYunMusic;
        } else {
            plat = Platform.KuGouMusic;
        }
        try {
            return singerDao.countSingers(area, firstLetter, plat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
