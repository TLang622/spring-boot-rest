package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.AuthProvider;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Administrator on 2017/2/10.
 * 生成rest接口
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    //@Autowired自动装配
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    /*PathVariable获取路径参数*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable Long id) {
        User userEntity = userRepository.findUserById(id);
        return userEntity;
    }

    /*PathParam参数获取*/
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getUser2(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "name", required = false) String name) {
        if(id != null) {
            User userEntity = userRepository.findUserById(id);
            return userEntity;
        }else if(name != null) {
            List<User> userEntitys = userRepository.findUserByName(name);
            return  userEntitys;
        }else {
            List<User> userEntitys = userRepository.findAllUsers();
            return  userEntitys;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addUser(@RequestBody User userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object updateUser(@RequestBody User userEntity) {
        Long id = userEntity.getId();
        if (id == null) {
            return "not found user";
        }
        User user = userRepository.findUserById(id);
        user.setName(userEntity.getName());
        user.setAge(userEntity.getAge());
        user.setPassword(userEntity.getPassword());
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delUser(@PathVariable Long id) {
        User userEntity = userRepository.findUserById(id);
        userRepository.delete(id);
        return userEntity;
    }

    //事务管理例子
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addUser2(@RequestBody User userEntity, @PathVariable Long id) {
        String str =userService.AddUser(userEntity, id);
        return str;
    }
}
