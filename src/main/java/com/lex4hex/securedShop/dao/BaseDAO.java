package com.lex4hex.securedShop.dao;

import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@EnableAsync(proxyTargetClass = true)
interface BaseDAO<Entity> {

    Entity findById(Integer id);

    List<Entity> findAll();

    void save(Entity entity);

    void update(Entity entity);

    void deleteById(Integer id);

}