package com.unstruct.action;

import com.opensymphony.xwork2.ActionSupport;
import com.unstruct.model.HdfsFileEntity;
import com.unstruct.service.HdfsFileService;
import com.unstruct.util.HibernateUtil;
import com.unstruct.util.JsonUtil;
import com.unstruct.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
@Controller
@Scope("prototype")
@Namespace("/")
@Action("hdfsFile")
@Results({@Result(name ="success",location = "/front/index.jsp"),@Result(name = "error", location = "/front/error.jsp")})
public class HdfsFileAction extends ActionSupport{

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();
    private static final Logger LOGGER = LoggerFactory.getLogger(HdfsFileAction.class);

    @Resource
    HdfsFileService hdfsFileService;



    public String createTree(){
        List<HdfsFileEntity> hdfsFileEntityList= hdfsFileService.hdfsFileList();
        for (HdfsFileEntity hdfsFileEntity:hdfsFileEntityList){
            System.out.println("createTree:"+hdfsFileEntity.toString());
        }
        JSONArray jsonArray= JsonUtil.formatHdfsFileListToJsonArray(hdfsFileEntityList);
        try {
            ResponseUtil.write(response,jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    @Override
    public String execute() throws Exception {
        request.setAttribute("hdfsFileMessage", "Messsage from hdfsFileAction!");

        List<String> hdfsFileEntityFieldsList=HibernateUtil.getFieldsList(HdfsFileEntity.class, null);
        for (String name:hdfsFileEntityFieldsList){
            System.out.println(name);
        }



        List<HdfsFileEntity> hdfsFileEntityList= hdfsFileService.hdfsFileList();
        for (HdfsFileEntity hdfsFileEntity:hdfsFileEntityList){
            System.out.println(hdfsFileEntity.toString());
        }

        JSONArray jsonArray= JsonUtil.formatHdfsFileListToJsonArray(hdfsFileEntityList);
        JSONObject result=new JSONObject();
        result.put("data",jsonArray);


        request.setAttribute("title",hdfsFileEntityFieldsList);
        request.setAttribute("data",hdfsFileEntityList);
        return SUCCESS;
    }
}
