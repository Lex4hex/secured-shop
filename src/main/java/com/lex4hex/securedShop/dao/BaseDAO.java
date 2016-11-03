package com.lex4hex.securedShop.dao;

import java.util.List;

interface BaseDAO<Entity> {

    Entity findById(Integer id);

    List<Entity> findAll();

    void save(Entity entity);

    void update(Entity entity);

    void deleteById(Integer id);

}