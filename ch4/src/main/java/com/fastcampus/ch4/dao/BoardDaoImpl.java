package com.fastcampus.ch4.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;


public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession session;
    String namespace="com.fastcampus.ch4.dao.BoardMapper.";


    @Override
    public BoardDto select(int bno) throws Exception{
        return session.selectOne(namespace+"select",bno);
    }
}
