package com.unstruct.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.List;

import com.unstruct.model.HdfsFileEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				Object o=rs.getObject(i);
				if(o instanceof Date){
					mapOfColValues.put(md.getColumnName(i),DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
				}else{
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));					
				}
			}
			array.add(mapOfColValues);
		}
		return array;
	}

	public static JSONArray formatHdfsFileListToJsonArray(List<HdfsFileEntity> hdfsFileEntityList){
		JSONArray jsonArray=new JSONArray();
		for(HdfsFileEntity hdfsFileEntity:hdfsFileEntityList){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id",hdfsFileEntity.getFileId());
			if(hdfsFileEntity.getParentId()==0){
				jsonObject.put("parent","#");
			}else {
				jsonObject.put("parent", hdfsFileEntity.getParentId());
			}
			jsonObject.put("text",hdfsFileEntity.getFileName());
			jsonArray.add(jsonObject);
		}
		return jsonArray;

	}
}
