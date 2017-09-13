package com.itheima.dao;

import java.util.List;

import com.itheima.pojo.Book;

public interface BookDao {
	
	/**
	 * 查询所有书
	 * @return
	 */
	public List<Book> findBookList();
}
