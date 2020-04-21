package com.demo.dao;

import com.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/11 22:30
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
