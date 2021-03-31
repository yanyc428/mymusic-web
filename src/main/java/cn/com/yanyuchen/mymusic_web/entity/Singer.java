package cn.com.yanyuchen.mymusic_web.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 歌手
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "singer")
public class Singer {

    /**
     * 唯一id主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 歌手的名字
     */
    @Column
    private String name;

    /**
     * 类型 就是结合了地区和性别  文档有介绍  序列化忽略
     */
    @Column
    @JsonIgnore
    private Integer type;

    /**
     * 首字母 序列化忽略
     */
    @Column(length = 5)
    @JsonIgnore
    private String firstLetter;

    /**
     * 歌手的url
     */
    @Column
    private String url;

    /**
     * 来源 也就是平台  取名是历史遗留问题了 序列化忽略
     */
    @Column
    @JsonIgnore
    private Integer source;

    /**
     * 创建时间 序列化忽略
     */
    @CreationTimestamp
    @Column
    @JsonIgnore
    private Date createTime;

    /**
     * 更新时间 序列化忽略
     */
    @UpdateTimestamp
    @Column
    @JsonIgnore
    private Date updateTime;

}
