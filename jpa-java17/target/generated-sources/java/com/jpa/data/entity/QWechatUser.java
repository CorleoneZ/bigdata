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

    public final StringPath gender = createString("gender");

    public final StringPath headImageUrl = createString("headImageUrl");

    public final NumberPath<Integer> languageId = createNumber("languageId", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath openId = createString("openId");

    public final StringPath platId = createString("platId");

    public final StringPath userId = createString("userId");

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

