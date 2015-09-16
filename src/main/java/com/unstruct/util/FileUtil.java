package com.unstruct.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static String getFileType(File file) {
		String fileName = file.getName();
		if (fileName.endsWith("doc")) {
			return "doc";
		}
		return null;
	}

	public static boolean isTextfile(File file) {
		String fileName = file.getName();
		if (fileName.toLowerCase().endsWith(".txt")) {
			return true;
		} else if (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx")) {
			return true;
		} else if (fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx")) {
			return true;
		} else if (fileName.toLowerCase().endsWith(".ppt") || fileName.toLowerCase().endsWith(".pptx")) {
			return true;
		} else if (fileName.toLowerCase().endsWith(".pdf")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param path  路径可以是目录或者当个文件的绝对路径，遇到文件夹递归处理，将子目录文件保存到同一个fileList
	 * @return 返回目录下所有的文件列表或者只有包含一个文件的列表
	 */
	public static List<File> getFileList(String path,List<File> fileList) {
		File file = new File(path);
//		List<File> fileList = new ArrayList<File>();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (isTextfile(files[i])) {
					fileList.add(files[i]);
				}else if(files[i].isDirectory()){
					getFileList(files[i].getPath(),fileList);
				}
			}
		} else {
			fileList.add(file);
		}
		return fileList;
	}
	
	public static void main(String[] args) {
	
	/*	List<File> fileList=new ArrayList<File>();
		getFileList("/home/spark/input",fileList);
		for (File file : fileList) {
			System.out.println(file.getName());
		}
		System.out.println("total: "+fileList.size());*/
		String path=System.getProperty("user.dir");
		System.out.println(path);
	}
}
