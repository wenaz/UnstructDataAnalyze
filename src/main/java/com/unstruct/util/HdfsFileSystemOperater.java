package com.unstruct.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsFileSystemOperater {

	private static String uri="hdfs://127.0.0.1:9000/";
	private static final Configuration conf;
	private static FileSystem fs;
	
	static{
//		final String HADOOP_CONF_DIR = "file:///home/spark/opt/hadoop-2.6.0/etc/hadoop/";
		final String HADOOP_CONF_DIR = System.getProperty("user.dir")+"\\src\\main\\resources\\";
		System.out.println(HADOOP_CONF_DIR);
		conf=new Configuration();
		conf.addResource(new Path(HADOOP_CONF_DIR + "core-site.xml"));
		conf.addResource(new Path(HADOOP_CONF_DIR + "hdfs-site.xml"));
		conf.addResource(new Path(HADOOP_CONF_DIR + "yarn-site.xml"));
		conf.addResource(new Path(HADOOP_CONF_DIR + "mapred-site.xml"));
	/*	String HBASE_CONF_DIR = "/usr/java/hbase-0.90.3/conf/";
		conf.addResource(new Path(HBASE_CONF_DIR + "hbase-site.xml"));*/
		try {
			fs=FileSystem.get(URI.create(uri),conf);
			System.out.println("configuration init completed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param localSrc path支持文件或文件夹，若是文件夹整个上传，dst可以省略不写hdfs：//，在fs已设置
	 * @param hdfsDst 弱势上传文件 dst path名应包括文件名，若是文件夹则是文件夹名
	 */
	public static boolean copyFromLocalFile(String localSrc,String hdfsDst){
		Path src=new Path(localSrc);
		Path dst=new Path(hdfsDst);
		try {
			if(!(new File(localSrc).exists())){
				System.out.println("local file["+src.toUri().toString()+"] isn't exist");
				return false;
			}
			fs.copyFromLocalFile(src, dst);
			System.out.println("upload file["+src.toUri().toString()+"] with conf "+conf.get("fs.defaultFS")+" to "+dst.toUri().toString());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean downloadFile(String hdfsSrc,String localdst){
		Path src=new Path(hdfsSrc);
		FSDataInputStream in=null;
		BufferedOutputStream out=null;
		
		try {
			if(!fs.exists(src)){
				System.out.println("hdfs file["+src.toUri().toString()+"] isn't exist");
				return false;
			}else if (fs.isDirectory(src)) {
				System.out.println("hdfs file["+src.toUri().toString()+"] isn't not a file,it should be a file");
				return false;
			}
			in=fs.open(src);
			out=new BufferedOutputStream(new FileOutputStream(localdst));
			IOUtils.copyBytes(in, out, 4096,true);
			System.out.println("download file["+src.toUri().toString()+"] with conf "+conf.get("fs.defaultFS")+" to "+localdst);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public static List<String> listFile(String dirname){
		Path path=new Path(dirname);
		List<String> fileList=null;
		try {
			fileList=new ArrayList<String>();
			FileStatus files[]=fs.listStatus(path);
			for (FileStatus fileStatus : files) {
				fileList.add(fileStatus.getPath().toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileList;
	}
	
	/**
	 * 
	 * @param pathName 设置了delete参数为true，path可以是文件夹，可递归删除
	 * @return 
	 */
	public static boolean deleteFile(String pathName){
		Path path=new Path(pathName);
		try {
			if(fs.exists(path)){
				fs.delete(path,true);
				System.out.println("delete file["+path.toUri().toString()+"] success!");
				return true;
			}else{
				System.out.println("file["+path.toUri().toString()+"] is not exist!");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	//以下是备用的常用hdfs操作函数
	public boolean mkdir(String dirName){
		Path path=new Path(dirName);
		try {
			if(fs.exists(path)){
				System.out.println("dir["+path.toUri().toString()+"] is exist!");
				return false;
			}
			fs.mkdirs(path);
			System.out.println("new dir["+path.toUri().toString()+"] success");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		copyFromLocalFile("d:\\dir", "/user/lenovo/example/");
		List<String> fileList=listFile("/user/lenovo/example");
		for (String file : fileList) {
			System.out.println(file);
		}
		downloadFile("/user/lenovo/example/file1.txt", "d:\\download.txt");
//		deleteFile("/user/lenovo/example/");
	}
}
