package com.seungjoo.menu.model.dao;

import com.seungjoo.menu.model.dto.MenuDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.seungjoo.common.JDBCTemplate.close;

public class MenuDao {
    private Properties prop = new Properties();
    public MenuDao(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/kangbroo/mapper/menu-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MenuDto> selectAllMenu(Connection conn){
        // select문 => 여러행조회 => ResultSet => List<MenuDto>
        List<MenuDto> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectAllMenu");

        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();

            while(rset.next()){
                list.add(new MenuDto(
                        rset.getInt("menu_code"),
                        rset.getString("menu_name"),
                        rset.getInt("menu_price"),
                        rset.getString("category_name"),
                        rset.getString("orderable_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close(rset);
            close(pstmt);
        }

        return list;

    }

    public int insertMenu(Connection conn, MenuDto menu){
        // insert문 => 처리된 행수(삽입된 행수) => int
        int result = 0;

        PreparedStatement pstmt = null;
        String query = prop.getProperty("insertMenu");

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2, menu.getMenuPrice());
            pstmt.setInt(3, Integer.parseInt(menu.getCategory()));
            pstmt.setString(4, menu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close(pstmt);
        }

        return result;

    }

}
