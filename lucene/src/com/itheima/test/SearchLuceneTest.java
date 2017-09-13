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
		//1.�����ִ�������
		/*StandardAnalyzer analyzer = new StandardAnalyzer();*/
		IKAnalyzer analyzer = new IKAnalyzer();
		//2.������ѯ���������� (2/ָ����ѯ�ķִ���)
		//����˵�� 1.Ĭ�������� 2.ָ���������ִ���
		QueryParser queryParser = new QueryParser("name",analyzer);
		//3.���������ѯ�Ľ��������󴴽���ѯ�����Ķ��� java
		//����˵�� ��parse()������ָ���˲�ѯ���� ��ô�������Ĭ������������ѯ ���û���ƶ������� �͸���Ĭ������������ѯ
		Query parse = queryParser.parse("���");
		//4.ָ���������ַ������Dirctory
		Directory directory = FSDirectory.open(new File("C:\\Users\\Administrator\\Desktop\\Java"));
		//5.ָ��������IndexReador(�ĵ�����Ϣ)
		IndexReader indexReader = DirectoryReader.open(directory);
		//6.ָ�������ȡ�Ķ��󴴽�indexsearch�������� (�ĵ�������)
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//7.����������������ѯ ��ѯ���� ��ѯ�������� ����topDocs���� (����������)
		//����˵��	1.ָ����ѯ������	2.��ѯ��ʾ�ļ�¼��
		TopDocs topDocs = indexSearcher.search(parse, 2);
		//8.����TopDocs�����ȡ��������
		ScoreDoc[] docs = topDocs.scoreDocs;
		//9.������������ ���ĵ���ID
		for (ScoreDoc scoreDoc : docs) {
			//10.��ȡ�ĵ�ID
			int docId = scoreDoc.doc;
			//11.IndexSearch��������ѯ�ĵ�
			Document doc = indexSearcher.doc(docId);
			//12.��ӡ�ĵ�
			System.out.println(doc.get("id"));
		}
		//13.�ͷ���Դ
		indexReader.close();
	}
}
