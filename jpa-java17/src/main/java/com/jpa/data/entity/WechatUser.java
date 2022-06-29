package com.jpa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "wechat_user")
@NoArgsConstructor
@AllArgsConstructor
public class WechatUser {

    @Id
    @Column(name = "plat_id")
    private String platId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "head_image_url")
    private String headImageUrl;

}
