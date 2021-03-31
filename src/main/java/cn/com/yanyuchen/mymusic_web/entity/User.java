package cn.com.yanyuchen.mymusic_web.entity;


import cn.com.yanyuchen.mymusic_web.util.NumericBooleanSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    /**
     * 唯一id 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名  唯一
     */
    @Column(length = 15, nullable = false, unique = true)
    private String userName;

    /**
     * 性别 json序列化的时候变成0和1
     */
    @Column
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean gender;

    /**
     * 头像url 长度255
     */
    @Column
    private String avatar;

    /**
     * 邮箱 不能空
     */
    @Column(length = 50, nullable = false)
    private String email;

    /**
     * 加密后的密码  序列化忽略
     */
    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 权限  是否管理员  json序列化转换为0和1
     */
    @Column
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean auth;

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
     * 用户喜欢的歌曲  多对多  序列化忽略
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name = "t_user_song",
            joinColumns = {@JoinColumn(name = "user_id")},//JoinColumns定义本方在中间表的主键映射
            inverseJoinColumns = {@JoinColumn(name = "song_id")})//inverseJoinColumns定义另一在中间表的主键映射
    private List<Song> songs;

    /**
     * 重写str
     * @return str
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", auth=" + auth +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
