package cn.com.yanyuchen.mymusic_web.enumItem;

/**
 * 地区枚举类
 */
public enum Area {
    /**
     * UNK 未知
     * CHN 华语
     * AME 欧美
     * JPN 日语
     * KOR 韩语
     * OTH 其他
     * M 男歌手
     * F 女歌手
     * G 组合
     * 以下为其组合
     */
    UNK(0),
    CHN_M(11), CHN_F(12), CHN_G(13),
    AME_M(21), AME_F(22), AME_G(23),
    JPN_M(31), JPN_F(32), JPN_G(33),
    KOR_M(41), KOR_F(42), KOR_G(43),
    OTH_M(51), OTH_F(52), OTH_G(53);


    private Integer name;

    /**
     *  构造函数
     * @param name
     */
    Area(Integer name) {
        this.name = name;
    }

    /**
     * 返回对应的整数码
     * @return 对应的整数码
     */
    public Integer number(){
        return this.name;
    }

    public static Area getEnumType (int val) {
        for (Area type : Area .values()) {
            if (type.number() == val) {
                return type;
            }
        }
        return null;
    }
}
