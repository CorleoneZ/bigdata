package newFeature.recordCase;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;

public record WechatEvent(String openId,
                          String gender,
                          int groupId,
                          String group,
                          String nickname,
                          String headImageUrl,
                          String language) {

    public WechatEvent {
    }

    /*public static void main(String[] args) {
        WechatEvent wechatEvent = new WechatEvent("","",1);
    }*/
}
