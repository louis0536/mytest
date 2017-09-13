package com.itheima.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itheima.pojo.Book;


public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> findBookList() {
		// TODO Auto-generated method stub
		//���ݿ�����
		Connection conn = null;
		//Ԥ����statment
		PreparedStatement prep = null;
		//�����
		ResultSet resultSet = null;
		//ͼ���б�
		List<Book> list = new ArrayList<Book>();
		
		try {
			// �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// �������ݿ�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123");
			// SQL���
			String sql = "SELECT * FROM book";
			// ����preparedStatement
			prep = conn.prepareStatement(sql);
			// ��ȡ�����
			resultSet = prep.executeQuery();
			// ���������
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name"));
				book.setPrice(resultSet.getFloat("price"));
				book.setPic(resultSet.getString("pic"));
				book.setDesc(resultSet.getString("desc"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
