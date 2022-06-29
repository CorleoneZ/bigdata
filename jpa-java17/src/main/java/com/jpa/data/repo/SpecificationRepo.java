package com.jpa.data.repo;

import com.jpa.data.entity.WechatUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepo extends CrudRepository<WechatUser, Long>, JpaSpecificationExecutor<WechatUser> {

}
