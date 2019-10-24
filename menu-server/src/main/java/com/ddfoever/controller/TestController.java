package com.ddfoever.controller;

import com.ddfoever.dao.MenuDao;
import com.ddfoever.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuDao menuDao;

    @GetMapping("/port")
    public String getPort(){
        return "当前的端口号："+this.port;
    }

    @GetMapping("/menu/{pageNum}/{pageSize}")
    public List<Menu> findAll(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        List<Menu> menus = menuDao.findAll(pageNum,pageSize);
        return menus;
    }
}
