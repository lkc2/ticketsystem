package com.example.ticketsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.mapper.UserMapper;
import com.example.ticketsystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // Redis 快取 user 查詢
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

    // 改寫 getById
    public User getById(Long id) {
        String redisKey = "user:" + id;
        // 先查 redis
        User user = (User) redisTemplate.opsForValue().get(redisKey);
        if (user != null) {
            return user; // 快取命中
        }
        // 查資料庫
        user = super.getById(id);
        if (user != null) {
            redisTemplate.opsForValue().set(redisKey, user); // 寫入快取
        }
        return user;
    }

    // 建議：刪除/更新用戶時，記得刪掉快取
    public boolean updateById(User user) {
        boolean updated = super.updateById(user);
        if (updated) {
            String redisKey = "user:" + user.getId();
            redisTemplate.delete(redisKey);
        }
        return updated;
    }

    public boolean removeById(Long id) {
        boolean removed = super.removeById(id);
        if (removed) {
            String redisKey = "user:" + id;
            redisTemplate.delete(redisKey);
        }
        return removed;
    }
}