package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {

    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;

    @Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
    public void insertA1WithoutTx() throws Exception{
        a1Dao.insert(1,100);
        insertB1WithTx();
        a1Dao.insert(1,200);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception{
        b1Dao.insert(1,100);
        b1Dao.insert(2,100);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertB1WithTx() throws Exception {
        b1Dao.insert(2,200);

    }

    // @Transactional //RuntimException, Error만 rollback
    @Transactional(rollbackFor = Exception.class) //-Exception rollback
    public void insertA1WithTxFail() throws Exception{
        a1Dao.insert(1,100);
       // throw new RuntimeException();
        throw new Exception();
       // a1Dao.insert(1,200);
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception{
        a1Dao.insert(1,100);
        a1Dao.insert(2,200);
    }


}
