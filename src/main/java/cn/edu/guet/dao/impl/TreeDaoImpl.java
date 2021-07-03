package cn.edu.guet.dao.impl;

import cn.edu.guet.bean.Tree;
import cn.edu.guet.dao.ITreeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreeDaoImpl implements ITreeDao {
    @Override
    public List<Tree> getAllTree() {
        List<Tree> treeList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC&useSSL=false";
        /*String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "niruoanhao0918";*/
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("即将登陆数据库");
            conn = DriverManager.getConnection(url, "root", "ft1234");
            System.out.println("登陆数据库成功！");
            sql = "SELECT * FROM tb_tree";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Tree tree = new Tree();
                tree.setTreeId(rs.getString("TREEID"));
                tree.setParentId(rs.getString("PARENTID"));
                tree.setTitle(rs.getString("TITLE"));
                tree.setUrl(rs.getString("URL"));
                //tree.setIsParent(rs.getString("ISPARENT"));
                treeList.add(tree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return treeList;
    }
}
