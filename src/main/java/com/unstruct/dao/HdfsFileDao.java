package com.unstruct.dao;

import com.unstruct.model.HdfsFileEntity;
import com.unstruct.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
public class HdfsFileDao {
    public List<HdfsFileEntity> hdfsFileList()throws Exception{
        List<HdfsFileEntity> hdfsFileEntityList =null;
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        StringBuffer sb=new StringBuffer("from HdfsFileEntity h");
        Query query=session.createQuery(sb.toString());
        hdfsFileEntityList=(List<HdfsFileEntity>)query.list();
        return hdfsFileEntityList;
    }
}
