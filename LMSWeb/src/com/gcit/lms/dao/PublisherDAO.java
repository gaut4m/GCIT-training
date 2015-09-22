/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Publisher;

import com.gcit.lms.domain.Author;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class PublisherDAO extends BaseDAO {

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int createPublisher(Publisher publisher) throws ClassNotFoundException,
	SQLException {
return save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values(?,?,?)",
		new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
}

	public List<Publisher> readAll() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_publisher", null);
	}
	

	
	public Publisher getPublisherById(int publisherId) throws ClassNotFoundException,
			SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers = readAll("select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId });

		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		}
		return null;
	}
	
	public Publisher getPublisher(int publisherId) throws ClassNotFoundException,
	SQLException {
List<Publisher> publishers = new ArrayList<Publisher>();
publishers = readFirstLevel("select * from tbl_publisher where publisherId = ?",
		new Object[] { publisherId });

if (publishers != null && publishers.size() > 0) {
	return publishers.get(0);
}
return null;
}
	

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherPhone(rs.getString("publisherPhone"));
				List<Book> books;
				try {
					books = (List<Book>)bdao.readFirstLevel(
							"select * from tbl_book where pubId =?",
							new Object[] { p.getPublisherId() });
					p.setBooks(books);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				publishers.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publishers;
	}


	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();

		try {
			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherPhone(rs.getString("publisherPhone"));
				
				publishers.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publishers;
	}

	public int updatePublisher(Publisher publisher) throws ClassNotFoundException,
			SQLException {
int i=0;
		return save("update tbl_publisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public int deletePublisher(int publisherId) throws ClassNotFoundException,
			SQLException {
		if(checkPublisher(publisherId)==0)
		return save("delete from tbl_publisher where publisherId=?",
				new Object[] { publisherId });
		return 0;
	}
	
	

	private int checkPublisher(int publisherId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return check("select Count(*) from tbl_book where pubId=?",
				new Object[] { publisherId });
	}

	public List<Publisher> getAllPublishers(String searchString, int pageNo, int pageSize) throws ClassNotFoundException,
			SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		searchString = "%"+searchString+"%";
		
		return readAll("select * from tbl_publisher where publisherName like ? or publisherAddress like ? or publisherPhone like ?", new Object[] { searchString,searchString,searchString });
	}

	public int getCount(String searchString) throws ClassNotFoundException, SQLException {
			searchString = "%"+searchString+"%";
			return check("select count(*) as count from tbl_publisher where publisherName like ? or publisherAddress like ? or publisherPhone like ?", new Object[] { searchString,searchString,searchString });
		
	}

	

	
}


