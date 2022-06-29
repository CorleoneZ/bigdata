package newFeature.recordCase;

/**
 *
 */
public class Case {

    public static void main(String[] args) {
        UserInfo user1 = new UserInfo("8b70fe27ca3e41e0b239e6aaca8b880e","man",1,"common","truster","http://baidu.com","Mandarin");
        UserInfo user2 = UserInfo.builder()
                .openId("8b70fe27ca3e41e0b239e6aaca8b880f").gender("woman").groupId(2).group("common").nickname("happy").headImageUrl("http://baidu.com").language("English").build();
        System.out.println(user1 + "\n" + user2);


        WechatEvent event = new WechatEvent("8b70fe27ca3e41e0b239e6aaca8b880x", "man", 1, "common","howtodoinjava@gmail.com","http://baidu.com" ,"Serb(Serbian)");
        System.out.println("nickname: " + event.nickname() + "  headImageUrl: " + event.headImageUrl() + "\n" + event);
    }
}
