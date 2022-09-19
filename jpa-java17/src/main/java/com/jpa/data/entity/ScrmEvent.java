package com.jpa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fht_scrm_event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrmEvent {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "plat_id")
    private String platId;

    @Column(name = "openId")
    private String openId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "raw_data")
    private String rawData;

    @Column(name = "event_source")
    private String eventSource;

    @Column(name = "seq_id")
    private long seqId;

    @Column(name = "owner")
    private String owner;

    @Column(name = "agent_id")
    private int agentId;

    @Column(name = "form_id")
    private String formId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "business_id")
    private int businessId;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "event_time")
    private long eventTime;

    @Column(name = "event_value")
    private String eventValue;

    @Column(name = "event_row")
    private String eventRow;

    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "event_property1")
    private String eventProperty1;

    @Column(name = "event_property2")
    private String eventProperty2;

    @Column(name = "event_property3")
    private String eventProperty3;
}