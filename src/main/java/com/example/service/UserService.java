package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

//注册bean，或者用@Service也行
@Component
public class UserService {
    //自动装配
    @Autowired
    private UserRepository userRepository;

    //事务注解
    @Transactional
    public String AddUser(User userEntity, Long id) {
        try {
            userRepository.save(userEntity);
            User user = userRepository.findUserById(id);
            //当传的id在数据库找不到数据，下面就会报空指针错误
            System.out.println(user.getName());
        }catch (Exception e) {
            //捕捉错误，然后触发事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            return "fail";
        }

        return "success";
    }
}
