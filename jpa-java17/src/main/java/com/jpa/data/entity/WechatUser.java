package com.jpa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wechat_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WechatUser {

    @Id
    @Column(name = "open_id")
    private String openId;

    @Column(name = "plat_id")
    private String platId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "seq_id")
    private long seqId;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "province_id")
    private int provinceId;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "realtime_country_id")
    private int realtimeCountryId;

    @Column(name = "realtime_province_id")
    private int realtimeProvinceId;

    @Column(name = "realtime_city_id")
    private int realtimeCityId;

    @Column(name = "gender")
    private int gender;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "language_id")
    private int languageId;

    @Column(name = "head_image_url")
    private String headImageUrl;

    @Column(name = "subscribe")
    private int subscribe;

    @Column(name = "subscribe_time")
    private long subscribeTime;

    @Column(name = "unsubscribe_time")
    private long unsubscribeTime;

    @Column(name = "tags")
    private int[] tags;

    @Column(name = "wechat_tags")
    private int[] wechatTags;

    @Column(name = "remark")
    private String remark;

    @Column(name = "lifecycle")
    private int lifecycle;

    @Column(name = "last_interaction_time")
    private long lastInteractionTime;

    @Column(name = "subscribe_scene")
    private int subscribeScene;

    @Column(name = "qr_scene")
    private long qrScene;

    @Column(name = "qr_scene_str")
    private String qrSceneStr;

    @Column(name = "update_time")
    private long updateTime;

}
