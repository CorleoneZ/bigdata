import com.Application;
import com.jpa.data.entity.WechatUser;
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

    @Test
    public void testTableCount() {
        long count = wechatUserRepo.count();
        System.out.println("wechat_user table count: " + count);
    }

    @Test
    public void createAndSaveWechatUser() {
        /*for (int i = 10; i < 20; i++) {
            WechatUser user = new WechatUser("" + i + "","" + i + "","" + i + "","" + i + "","" + i + "",i,"" + i + "");
            wechatUserRepo.save(user);
        }*/
        /*WechatUser user = new WechatUser("2","2","2","2","2",1,"2");
        wechatUserRepo.save(user);*/
    }

 /*   @Test
    public void query() {
        List<WechatUser> users = wechatUserRepo.findByPlatIdOrUserId("1","2");
        System.out.println(users);
    }*/

    @Test
    public void testPaging() {
        Page<WechatUser> all = wechatUserRepo.findAll(PageRequest.of(0,2));
        System.out.println(all.getTotalPages());
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("languageId").descending();
        Iterable<WechatUser> all = wechatUserRepo.findAll(sort);
        System.out.println(all);
    }

    @Test
    public void testJPQL() {
        List<WechatUser> list = wechatUserRepo.findAll();
        System.out.println(list);
    }
}
