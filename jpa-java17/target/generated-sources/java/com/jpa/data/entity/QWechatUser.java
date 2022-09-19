package com.jpa.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWechatUser is a Querydsl query type for WechatUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWechatUser extends EntityPathBase<WechatUser> {

    private static final long serialVersionUID = 1611521496L;

    public static final QWechatUser wechatUser = new QWechatUser("wechatUser");

    public final NumberPath<Integer> cityId = createNumber("cityId", Integer.class);

    public final NumberPath<Integer> countryId = createNumber("countryId", Integer.class);

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Integer> groupId = createNumber("groupId", Integer.class);

    public final StringPath headImageUrl = createString("headImageUrl");

    public final NumberPath<Integer> languageId = createNumber("languageId", Integer.class);

    public final NumberPath<Long> lastInteractionTime = createNumber("lastInteractionTime", Long.class);

    public final NumberPath<Integer> lifecycle = createNumber("lifecycle", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath openId = createString("openId");

    public final StringPath platId = createString("platId");

    public final NumberPath<Integer> provinceId = createNumber("provinceId", Integer.class);

    public final NumberPath<Long> qrScene = createNumber("qrScene", Long.class);

    public final StringPath qrSceneStr = createString("qrSceneStr");

    public final NumberPath<Integer> realtimeCityId = createNumber("realtimeCityId", Integer.class);

    public final NumberPath<Integer> realtimeCountryId = createNumber("realtimeCountryId", Integer.class);

    public final NumberPath<Integer> realtimeProvinceId = createNumber("realtimeProvinceId", Integer.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Long> seqId = createNumber("seqId", Long.class);

    public final NumberPath<Integer> subscribe = createNumber("subscribe", Integer.class);

    public final NumberPath<Integer> subscribeScene = createNumber("subscribeScene", Integer.class);

    public final NumberPath<Long> subscribeTime = createNumber("subscribeTime", Long.class);

    public final ArrayPath<int[], Integer> tags = createArray("tags", int[].class);

    public final StringPath unionId = createString("unionId");

    public final NumberPath<Long> unsubscribeTime = createNumber("unsubscribeTime", Long.class);

    public final NumberPath<Long> updateTime = createNumber("updateTime", Long.class);

    public final StringPath userId = createString("userId");

    public final ArrayPath<int[], Integer> wechatTags = createArray("wechatTags", int[].class);

    public QWechatUser(String variable) {
        super(WechatUser.class, forVariable(variable));
    }

    public QWechatUser(Path<? extends WechatUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWechatUser(PathMetadata metadata) {
        super(WechatUser.class, metadata);
    }

}

