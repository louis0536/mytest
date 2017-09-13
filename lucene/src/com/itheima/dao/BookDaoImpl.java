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
		//数据库连接
		Connection conn = null;
		//预编译statment
		PreparedStatement prep = null;
		//结果集
		ResultSet resultSet = null;
		//图书列表
		List<Book> list = new ArrayList<Book>();
		
		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123");
			// SQL语句
			String sql = "SELECT * FROM book";
			// 创建preparedStatement
			prep = conn.prepareStatement(sql);
			// 获取结果集
			resultSet = prep.executeQuery();
			// 结果集解析
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
