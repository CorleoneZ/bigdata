package com.kafka.producer;

/**
 * {"user_id":63401,"item_id":6244,"cat_id":143,"action":"pv","province":3,"ts":1573445919}
 * {"user_id":9164,"item_id":2817,"cat_id":611,"action":"fav","province":28,"ts":1573420486}
 * {"user_id":63401,"item_id":6244,"cat_id":143,"action":"pv","province":3,"ts":1573445919}
 */

public class KafkaData {
    private int user_id;
    private int item_id;
    private int cat_id;
    private String action;
    private int province;
    private int ts;

    public KafkaData() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public KafkaData(int user_id, int item_id, int cat_id, String action, int province, int ts) {
        this.user_id = user_id;
        this.item_id = item_id;
        this.cat_id = cat_id;
        this.action = action;
        this.province = province;
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "KafkaData{" +
                "user_id=" + user_id +
                ", item_id=" + item_id +
                ", cat_id=" + cat_id +
                ", action='" + action + '\'' +
                ", province=" + province +
                ", ts=" + ts +
                '}';
    }
}
