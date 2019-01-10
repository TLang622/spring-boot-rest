package com.example.controller;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Administrator on 2017/2/13.
 * 这是通过spring-boot-starter-service-rest和spring-boot-starter-service-jpa快速生成接口
 * http://127.0.0.1:8080/user    get:获取全部用户。post：json包含id就修改，不包含就新增
 * http://127.0.0.1:8080/user/{id}  get:通过主键查询。put:更新数据。 delete：删除
 * http://127.0.0.1:8080/user?page=1&size=3&sort=age,desc   分页排序
 */

//这里虽然是数据访问接口类，@RepositoryRestResource却直接生成了controller接口
@RepositoryRestResource(path = "user")
public interface UserRepositoryRest extends JpaRepository<User, Long> {

    //接口里面其实什么都不写就已经包含了基础的增删改查分页排序等接口
    //这里再自定义一个查询，http://127.0.0.1:8080/user/search/name?name=tes
    //也可以在其他controller函数里面使用UserRepositoryRest.findByName来查询数据
    @RestResource(path="name",rel="name")
    public User findByName(@Param("name") String name);
}
