package com.jpa.data.repo;

import com.jpa.data.entity.ScrmEvent;
import com.jpa.data.entity.WechatUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<ScrmEvent,String> {


}
