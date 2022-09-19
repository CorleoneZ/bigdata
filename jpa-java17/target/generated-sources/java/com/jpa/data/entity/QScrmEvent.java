package com.jpa.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScrmEvent is a Querydsl query type for ScrmEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrmEvent extends EntityPathBase<ScrmEvent> {

    private static final long serialVersionUID = 1431703080L;

    public static final QScrmEvent scrmEvent = new QScrmEvent("scrmEvent");

    public final NumberPath<Integer> agentId = createNumber("agentId", Integer.class);

    public final NumberPath<Integer> businessId = createNumber("businessId", Integer.class);

    public final StringPath contactId = createString("contactId");

    public final StringPath customerId = createString("customerId");

    public final StringPath eventContent = createString("eventContent");

    public final StringPath eventId = createString("eventId");

    public final StringPath eventProperty1 = createString("eventProperty1");

    public final StringPath eventProperty2 = createString("eventProperty2");

    public final StringPath eventProperty3 = createString("eventProperty3");

    public final StringPath eventRow = createString("eventRow");

    public final StringPath eventSource = createString("eventSource");

    public final NumberPath<Long> eventTime = createNumber("eventTime", Long.class);

    public final StringPath eventType = createString("eventType");

    public final StringPath eventValue = createString("eventValue");

    public final StringPath formId = createString("formId");

    public final StringPath openId = createString("openId");

    public final StringPath owner = createString("owner");

    public final StringPath platId = createString("platId");

    public final StringPath rawData = createString("rawData");

    public final NumberPath<Long> seqId = createNumber("seqId", Long.class);

    public final NumberPath<Integer> staffId = createNumber("staffId", Integer.class);

    public final StringPath unionId = createString("unionId");

    public final StringPath userId = createString("userId");

    public QScrmEvent(String variable) {
        super(ScrmEvent.class, forVariable(variable));
    }

    public QScrmEvent(Path<? extends ScrmEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScrmEvent(PathMetadata metadata) {
        super(ScrmEvent.class, metadata);
    }

}

