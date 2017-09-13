package com.itheima.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.itheima.dao.BookDaoImpl;
import com.itheima.pojo.Book;

public class IndexLuceneTest {

	@Test
	public void test() throws Exception {
		// 1.获取数据
		BookDaoImpl daoImpl = new BookDaoImpl();
		List<Book> list = daoImpl.findBookList();
		//创建一个文档集合
		List<Document> docList = new ArrayList<>();
		
		for (Book book : list) {
			// 2.创建文档
			Document doc = new Document();
			// 3.创建field域 把数据放在域中
			// 参数说明 1.域名 2.域值 3.是否存储
			Field idField = new TextField("id", book.getId() + "", Store.YES);
			Field nameField = new TextField("name", book.getName() + "", Store.YES);
			Field priceField = new TextField("price", book.getPrice() + "", Store.YES);
			Field picField = new TextField("pic", book.getPic() + "", Store.YES);
			Field descField = new TextField("desc", book.getDesc() + "", Store.YES);
			// 4、把Field域放在文档中
			doc.add(idField);
			doc.add(nameField);
			doc.add(priceField);
			doc.add(picField);
			doc.add(descField);
			
			//添加到文档集合
			docList.add(doc);	
		}
		// 5.分词
		//StandardAnalyzer analyzer = new StandardAnalyzer();
		IKAnalyzer analyzer = new IKAnalyzer();
		// 6.创建指定索引库地址的流
		Directory directory = FSDirectory.open(new File("C:\\Users\\Administrator\\Desktop\\Java"));
		// 7.创建索引库的配置对象 参数说明	1.指定lucene是哪个版本 2.指定lucene要根据什么分词器来分词
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// 8.创建写入索引库的对象IndexWriter(参数:指定索引库的地址 索引库的配置对象)
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
		// 9.用写入索引库的对象 写入文档
		for(Document document : docList){
			indexWriter.addDocument(document);
		}
		// 10.释放资源
		indexWriter.close();
	}
	
}
