package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 * 这是通过spring-boot-starter-service-jpa定义数据访问接口类
 */

public interface UserRepository extends JpaRepository<User, Long> {
    //通过id查找数据
    User findUserById(long id);
    //通过名字查找数据，返回数组
    List<User> findUserByName(String name);
    //通过sql语句
    @Query(value = "select * from user", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "select * from user where name=:name and password=:password LIMIT 1", nativeQuery = true)
    User findUserByNameAndPass(@Param("name") String name, @Param("password") String password);
}
