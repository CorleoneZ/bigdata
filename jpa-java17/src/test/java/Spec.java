import com.Application;
import com.jpa.data.entity.WechatUser;
import com.jpa.data.repo.SpecificationRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Iterator;
import java.util.List;

//CriteriaBuilder.In<String> in = criteriaBuilder.in(userId);
//in.value("1").value("2").value("3");
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Spec {

    @Resource
    private SpecificationRepo repo;

    @Test
    public void test() {
        List<WechatUser> list = repo.findAll(new Specification<WechatUser>() {
            @Override
            public Predicate toPredicate(Root<WechatUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> openId = root.get("openId");
                Path<Integer> languageId = root.get("languageId");
                //Path<String> userId = root.get("")

                Predicate p1 = criteriaBuilder.equal(openId,"1");
                Predicate p2 = criteriaBuilder.equal(languageId,1);
                Predicate back = criteriaBuilder.or(p1,p2);

                return back;
            }
        });
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
            System.out.println(it1.next());
        }
    }
}
