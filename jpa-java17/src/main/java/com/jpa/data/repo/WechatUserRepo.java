package com.jpa.data.repo;

import com.jpa.data.entity.WechatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface WechatUserRepo extends CrudRepository<WechatUser,String> {

    @Query(value = "select w from WechatUser w", nativeQuery = true)
    List<WechatUser> findAll();

    WechatUser findByOpenId(String openId);

}