package com.unstruct.dao;

import com.unstruct.model.HdfsFileEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
public class HdfsFileDaoTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHdfsFileList() throws Exception {

        HdfsFileDao hdfsFileDao=new HdfsFileDao();
        List<HdfsFileEntity> hdfsFileEntityList=hdfsFileDao.hdfsFileList();
        for (HdfsFileEntity hdfsFileEntity:hdfsFileEntityList){
            System.out.println(hdfsFileEntity.toString());
        }
    }
}