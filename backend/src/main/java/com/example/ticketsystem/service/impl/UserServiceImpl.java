package com.example.ticketsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.mapper.UserMapper;
import com.example.ticketsystem.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public List<User> findAllAgents() {
        return lambdaQuery().eq(User::getRole, "agent").list();
    }

    @Override
    public boolean resetPassword(Long userId, String newPassword) {
        User user = getById(userId);
        if (user == null) return false;
        user.setPassword(newPassword);
        return updateById(user);
    }

}
