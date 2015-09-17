package com.unstruct.dao;

import com.unstruct.model.HdfsFileEntity;
import com.unstruct.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
@Repository
public class HdfsFileDao extends SuperDao{
    public List<HdfsFileEntity> hdfsFileList(){

        List<HdfsFileEntity> hdfsFileEntityList =null;
//        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        StringBuffer sb=new StringBuffer("from HdfsFileEntity h");
        Query query=session.createQuery(sb.toString());
        hdfsFileEntityList=(List<HdfsFileEntity>)query.list();
        return hdfsFileEntityList;
    }
}
