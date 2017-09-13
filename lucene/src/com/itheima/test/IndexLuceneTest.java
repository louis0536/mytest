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
		// 1.��ȡ����
		BookDaoImpl daoImpl = new BookDaoImpl();
		List<Book> list = daoImpl.findBookList();
		//����һ���ĵ�����
		List<Document> docList = new ArrayList<>();
		
		for (Book book : list) {
			// 2.�����ĵ�
			Document doc = new Document();
			// 3.����field�� �����ݷ�������
			// ����˵�� 1.���� 2.��ֵ 3.�Ƿ�洢
			Field idField = new TextField("id", book.getId() + "", Store.YES);
			Field nameField = new TextField("name", book.getName() + "", Store.YES);
			Field priceField = new TextField("price", book.getPrice() + "", Store.YES);
			Field picField = new TextField("pic", book.getPic() + "", Store.YES);
			Field descField = new TextField("desc", book.getDesc() + "", Store.YES);
			// 4����Field������ĵ���
			doc.add(idField);
			doc.add(nameField);
			doc.add(priceField);
			doc.add(picField);
			doc.add(descField);
			
			//��ӵ��ĵ�����
			docList.add(doc);	
		}
		// 5.�ִ�
		//StandardAnalyzer analyzer = new StandardAnalyzer();
		IKAnalyzer analyzer = new IKAnalyzer();
		// 6.����ָ���������ַ����
		Directory directory = FSDirectory.open(new File("C:\\Users\\Administrator\\Desktop\\Java"));
		// 7.��������������ö��� ����˵��	1.ָ��lucene���ĸ��汾 2.ָ��luceneҪ����ʲô�ִ������ִ�
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// 8.����д��������Ķ���IndexWriter(����:ָ��������ĵ�ַ ����������ö���)
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
		// 9.��д��������Ķ��� д���ĵ�
		for(Document document : docList){
			indexWriter.addDocument(document);
		}
		// 10.�ͷ���Դ
		indexWriter.close();
	}
	
}
