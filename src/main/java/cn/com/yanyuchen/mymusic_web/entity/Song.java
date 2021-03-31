package cn.com.yanyuchen.mymusic_web.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 歌曲实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "song")
public class Song {

    /**
     * 唯一主键 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 歌曲的名字
     */
    @Column
    private String name;

    /**
     * 歌曲的歌手  这是一个多对一  也就是一个歌手可以有多个歌曲
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "singerId")
    private Singer singer;

    /**
     * 所属专辑
     */
    @Column
    private String album;

    /**
     * 来源 也就是平台  取名是历史遗留问题了 序列化忽略
     */
    @Column
    @JsonIgnore
    private Integer source;

    /**
     * 歌曲可到达url
     */
    @Column
    private String url;

    /**
     * 所属专辑url
     */
    @Column
    private String albumUrl;

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

    /**
     * 喜欢该歌曲的user  这里应该是多对多  很好解释  序列化忽略
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name = "t_user_song",
            joinColumns = {@JoinColumn(name = "song_id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "user_id")})//inverseJoinColumns定义另一在中间表的主键映射
    private List<User> users;


    /**
     * 是否喜欢这个歌曲  这个不需要序列化
     */
    @Transient
    private boolean like;

    /**
     * 重写一个str
     *
     * @return str格式化
     */
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer=" + singer +
                ", album='" + album + '\'' +
                ", source=" + source +
                ", url='" + url + '\'' +
                ", albumUrl='" + albumUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", like=" + like +
                '}';
    }
}
