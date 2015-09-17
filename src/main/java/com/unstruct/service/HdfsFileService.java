package com.unstruct.service;

import com.unstruct.dao.HdfsFileDao;
import com.unstruct.model.HdfsFileEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo-ang on 2015/9/17.
 */
@Service("hdfsFileService")
public class HdfsFileService {

    @Resource
    HdfsFileDao hdfsFileDao;

    public List<HdfsFileEntity> hdfsFileList(){
        return hdfsFileDao.hdfsFileList();
    }

}
