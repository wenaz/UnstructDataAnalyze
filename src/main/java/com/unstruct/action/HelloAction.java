package com.unstruct.action;

import com.opensymphony.xwork2.ActionSupport;
import com.unstruct.dao.HdfsFileDao;
import com.unstruct.model.HdfsFileEntity;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
public class HelloAction extends ActionSupport{

    HttpServletRequest request = ServletActionContext.getRequest();
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloAction.class);

    public String hello() {
        request.setAttribute("hello", "hello world!");

        HdfsFileDao hdfsFileDao=new HdfsFileDao();
        List<HdfsFileEntity> hdfsFileEntityList= null;
        try {
            hdfsFileEntityList = hdfsFileDao.hdfsFileList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (HdfsFileEntity hdfsFileEntity:hdfsFileEntityList){
            System.out.println(hdfsFileEntity.toString());
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        hello();
        return SUCCESS;
    }
}
