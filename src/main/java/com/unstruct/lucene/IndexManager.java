package com.unstruct.lucene;

import com.unstruct.util.FileUtil;
import com.unstruct.util.GetContentUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IndexManager {

	private static final String INDEX_DIR="d:\\_index\\unstructindex";
	
	public static boolean createIndex(String path){
		Date startDate=new Date();
		
		List<File> fileList=new ArrayList<File>();
		FileUtil.getFileList(path,fileList);
		Analyzer analyzer=new StandardAnalyzer();
		Path indexPath=FileSystems.getDefault().getPath(INDEX_DIR);
		Directory directory=null;
		try {
			directory = FSDirectory.open(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content="";
		for(File file:fileList){
			content=GetContentUtil.getContent(file);					
			IndexWriter iWriter=null;
			try {
				IndexWriterConfig conf=new IndexWriterConfig(analyzer);
				iWriter=new IndexWriter(directory, conf);
				Document doc=new Document();
				doc.add(new Field("fileName", file.getName(),TextField.TYPE_STORED));
				doc.add(new Field("path", file.getPath(),TextField.TYPE_STORED));
				doc.add(new Field("content", content, TextField.TYPE_STORED));
				
				iWriter.addDocument(doc);
				System.out.println("文件："+file.getName()+"索引已建立");
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(iWriter!=null){
						iWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Date endDate=new Date();
		System.out.println("createIndex time: "+(endDate.getTime()-startDate.getTime())/1000.0+" s");
		return true;
	}
	
	
	public static List<String> searchIndex(String searchText){
		Date startDate=new Date();
		List<String> hitFileNameList=new ArrayList<String>();
		List<String> hitFilePathList=new ArrayList<String>();
		try {
			Analyzer analyzer=new StandardAnalyzer();
			Path indexPath=FileSystems.getDefault().getPath(INDEX_DIR);
			Directory directory=FSDirectory.open(indexPath);
			DirectoryReader directoryReader=DirectoryReader.open(directory);
			IndexSearcher indexSearcher=new IndexSearcher(directoryReader);
			QueryParser parser=new QueryParser("content", analyzer);
			Query query=parser.parse(searchText);
			ScoreDoc[] hits=indexSearcher.search(query, null,1000).scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc=indexSearcher.doc(hits[i].doc);
				hitFileNameList.add(hitDoc.get("fileName"));
				hitFilePathList.add(hitDoc.get("path"));
			}
			QueryParser parser2=new QueryParser("fileName", analyzer);
			Query query2=parser2.parse(searchText);
			ScoreDoc[] hits2=indexSearcher.search(query2, null,1000).scoreDocs;
			for (int i = 0; i < hits2.length; i++) {
				Document hitDoc=indexSearcher.doc(hits2[i].doc);
				if(!hitFileNameList.contains(hitDoc.get("fileName"))){
					hitFileNameList.add(hitDoc.get("fileName"));
					hitFilePathList.add(hitDoc.get("path"));
				}
			}
			System.out.println("命中文档数："+(hits.length+hits2.length));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date endDate=new Date();
		System.out.println("searchIndex time: "+(endDate.getTime()-startDate.getTime())/1000.0+" s");
		System.out.println("____________________________");
		for (String path : hitFilePathList) {
			System.out.println(path);
		}
		System.out.println("____________________________");
		return hitFileNameList;
		
	}


	public static void main(String[] args) {
		createIndex("d:\\dir");
		Scanner scanner=new Scanner(new BufferedInputStream(System.in));
		String input="";
		while(!"end".equals(input=scanner.nextLine())){
			List<String> flieList=searchIndex(input);
			for (String fileName : flieList) {
				System.out.println(fileName);
			}
			System.out.println("请输入搜索条件：");
		}
	}
	
}
