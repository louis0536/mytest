package com.itheima.dao;

import java.util.List;

import com.itheima.pojo.Book;

public interface BookDao {
	
	/**
	 * ��ѯ������
	 * @return
	 */
	public List<Book> findBookList();
}
