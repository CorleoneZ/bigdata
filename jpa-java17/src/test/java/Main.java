import com.Application;
import com.jpa.data.entity.ScrmEvent;
import com.jpa.data.entity.WechatUser;
import com.jpa.data.repo.EventRepo;
import com.jpa.data.repo.WechatUserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Main {

    @Resource
    private WechatUserRepo wechatUserRepo;

    @Resource
    private EventRepo eventRepo;

    @Test
    public void testTableCount() {
        long count = wechatUserRepo.count();
        System.out.println("wechat_user table count: " + count);
    }

    @Test
    public void createAndSaveWechatUser() {
        /*WechatUser user = WechatUser.builder()
                .openId("001").nickname("sam").build();
        WechatUser user2 = WechatUser.builder()
                .openId("002").nickname("jack").build();*/

        ScrmEvent event1 = ScrmEvent.builder()
                .eventId("1").openId("001").eventSource("wechat").build();
        ScrmEvent event2 = ScrmEvent.builder()
                .eventId("2").openId("002").eventSource("scene").build();
        eventRepo.save(event1);
        eventRepo.save(event2);
    }

    @Test
    public void query() {
        WechatUser user = wechatUserRepo.findByOpenId("12");
        System.out.println(user);

    }
}
