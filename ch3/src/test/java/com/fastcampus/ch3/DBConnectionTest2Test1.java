package com.fastcampus.ch3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test1  {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    DataSource ds;

    @Test
    public void Delete() throws Exception{
        User user=new User("s1sf","1234","asd","das@",new Date(),"face",new Date());
        deleteAll();
        insertUser(user);
        int rowCnt=deleteUser(user.getId());
        assertTrue(rowCnt==1);
    }

    @Test
    public void insertUserTest() throws Exception{
        User user=new User("s1sf","1234","asd","das@",new Date(),"face",new Date());
        deleteAll();
        int rowCnt=insertUser(user);
        assertTrue(rowCnt==1);
    }

    @Test
    public void selectUserTest() throws Exception{
        User user=new User("s1sf","1234","asd","das@",new Date(),"face",new Date());
        deleteAll();
        int rowCnt=insertUser(user);
        User user2=selectUser("s1sf");

        assertTrue(user2.getId().equals("s1sf"));
    }

    public int updateUser(User user)throws Exception{
        Connection conn=ds.getConnection();
        String sql="update user_info set "
    }

    public int deleteUser(String id) throws Exception{
        Connection conn= ds.getConnection();
        String sql="delete from user_info where id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        int n=pstmt.executeUpdate();
        return n;

    }
    public User selectUser(String id) throws Exception{
        Connection conn=ds.getConnection();

        String sql = "select * from user_info where id=?";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            User user=new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getDate(7).getTime()));
            return user;
        }
        return null;
    }

    public void deleteAll() throws Exception{
        Connection conn=ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate();

    }

    // 사용자 정보를 user info 저장하는 메서드
    public int insertUser(User user) throws Exception{
        Connection conn=ds.getConnection();

        String sql = "insert into user_info values (?,?,?,?,?,?,now())";
//        insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//        values ('as2112df','1234','smiet','aaa@dsf.msdkl.com','2011-11-13','facebook',now());
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getPwd());
        pstmt.setString(3,user.getName());
        pstmt.setString(4,user.getEmail());
        pstmt.setDate(5,new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6,user.getSns());

        int rowCnt =pstmt.executeUpdate();
        return rowCnt;
    }

    @Test
    public void springJdbcConnectionTest() throws Exception{
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null);// 괄호안에 조건식이 true 성공 아님 실패

    }
}