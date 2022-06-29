package newFeature.recordCase;

public record WechatEvent(String openId,
                          String gender,
                          int groupId,
                          String group,
                          String nickname,
                          String headImageUrl,
                          String language) {
}
