package com.ibe6.menu.model.service;

import com.ibe6.menu.model.dao.MenuDao;
import com.ibe6.menu.model.dto.MenuDto;

import java.sql.Connection;
import java.util.List;

import static com.ibe6.common.JDBCTemplate.*;

public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<MenuDto> selectMenuList(){
        Connection conn = getConnection();
        List<MenuDto> list = menuDao.selectAllMenu(conn);
        close(conn);
        return list;
    }

    public int registMenu(MenuDto menu){
        Connection conn = getConnection();
        int result = menuDao.insertMenu(conn, menu);
        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }

}