package com.itheima.test;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SearchLuceneTest {
	
	@Test
	public void test() throws Exception{
		//1.创建分词器对象
		/*StandardAnalyzer analyzer = new StandardAnalyzer();*/
		IKAnalyzer analyzer = new IKAnalyzer();
		//2.创建查询解析器对象 (2/指定查询的分词器)
		//参数说明 1.默认搜索域 2.指定的搜索分词器
		QueryParser queryParser = new QueryParser("name",analyzer);
		//3.根据这个查询的解析器对象创建查询条件的对象 java
		//参数说明 当parse()参数中指定了查询的域 那么不会根据默认搜索域来查询 如果没有制定搜索域 就根据默认搜索域来查询
		Query parse = queryParser.parse("编程");
		//4.指定索引库地址刘对象Dirctory
		Directory directory = FSDirectory.open(new File("C:\\Users\\Administrator\\Desktop\\Java"));
		//5.指定索引库IndexReador(文档和休息)
		IndexReader indexReader = DirectoryReader.open(directory);
		//6.指定这个读取的对象创建indexsearch搜索对象 (文档和索引)
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//7.根据这个搜索对象查询 查询条件 查询的是索引 返回topDocs对象 (索引和坐标)
		//参数说明	1.指定查询的条件	2.查询显示的记录数
		TopDocs topDocs = indexSearcher.search(parse, 2);
		//8.根据TopDocs对象获取坐标数组
		ScoreDoc[] docs = topDocs.scoreDocs;
		//9.遍历坐标数组 有文档的ID
		for (ScoreDoc scoreDoc : docs) {
			//10.获取文档ID
			int docId = scoreDoc.doc;
			//11.IndexSearch对象来查询文档
			Document doc = indexSearcher.doc(docId);
			//12.打印文档
			System.out.println(doc.get("id"));
		}
		//13.释放资源
		indexReader.close();
	}
}
