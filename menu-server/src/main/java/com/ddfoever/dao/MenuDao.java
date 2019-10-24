package com.ddfoever.dao;

import com.ddfoever.entity.Menu;

import java.util.List;

public interface MenuDao {

    List<Menu> findAll(int pageNum,int pageSize);

    Menu getMenuById(long id);

    void saveOrUpdate(Menu menu);

    void deleteById(long id);
}
