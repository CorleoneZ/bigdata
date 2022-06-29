package com.jpa.data.repo;

import com.jpa.data.entity.WechatUser;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepo extends CrudRepository<WechatUser,String>, QuerydslPredicateExecutor<WechatUser> {

}
