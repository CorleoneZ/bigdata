import com.Application;
import com.jpa.data.entity.QScrmEvent;
import com.jpa.data.entity.QWechatUser;
import com.jpa.data.entity.WechatUser;
import com.jpa.data.repo.QueryRepo;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Query {

    @Resource
    QueryRepo queryRepo;

    @PersistenceContext
    EntityManager entityManager;

    /*@Test
    public void selectAllNameList() {
        QWechatUser wechatUser = QWechatUser.wechatUser;

        Iterable<WechatUser> list = queryRepo.findAll(wechatUser.nickname.in("1","2").or(wechatUser.headImageUrl.eq("1")));
        Iterator it1 = list.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

    }*/

    @Test
    public void test() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QWechatUser user = QWechatUser.wechatUser;
        QScrmEvent event = QScrmEvent.scrmEvent;

        QueryResults<Tuple> tupleQueryResults = queryFactory.from(user).join(event).on(user.openId.eq(event.openId))
                .select(user.openId,user.nickname)
                .where(user.nickname.containsIgnoreCase("sa").and(null))
                .orderBy(user.openId.desc())
                .fetchResults();

        System.out.println("tupleQueryResults size: " + tupleQueryResults.getTotal());
        for (Tuple t : tupleQueryResults.getResults()) {
            System.out.println("result: " + t);
        }

    }
}
