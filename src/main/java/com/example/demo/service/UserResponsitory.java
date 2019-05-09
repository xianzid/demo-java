package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 继承实现JPA一般方法
 */
public interface UserResponsitory extends JpaRepository<User, Long> {

    /**
     * 增加特殊参数的查询方法
     * JPQL面向对象语言
     * @param userName
     * @return
     */
    @Cacheable("userList")
    @Transactional(timeout = 10)
    public User findByUserName(String userName);

    /**
     * 若缓存有，直接读取；若没有，读取后存缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user", key = "#id")
    public User findUserById(Long id);

    /**
     * 保存DB并放缓存
     * @param user
     */
    @Transactional
    @CachePut(cacheNames = "user" , key = "#user.id")
    public User saveUser(User user);

    /**
     * 清除缓存
     * @param password
     * @param id
     * @return
     */
    @Transactional
    @CacheEvict(cacheNames = "user", key = "#id")
    public int updatePasswordById(String password, Long id);


}
