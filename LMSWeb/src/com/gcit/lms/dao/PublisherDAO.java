/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

/**
 * @publisher Gautham
 *
 */
public class PublisherDAO extends BaseDAO {

	public int createPublisher(Publisher publisher) throws ClassNotFoundException,
			SQLException {
		return save("insert into tbl_publisherlisher (publisherName,publisherAddress,publisherPhone) values(?,?,?)",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}

	public int updatePublisher(Publisher publisher) throws ClassNotFoundException,
			SQLException {
		return save("update tbl_publisherlisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public int deletePublisher(Publisher publisher) throws ClassNotFoundException,
			SQLException {
		if(checkPublisher(publisher)==0)
		return save("delete from tbl_publisher where publisherId=?",
				new Object[] { publisher.getPublisherId() });
		return 0;
	}
	
	

	private int checkPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return check("select Count(*) from tbl_book where pubId=?",
				new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> getAllPublishers() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_publisher", null);
	}

	public Publisher getPublisher(Publisher publisher) throws ClassNotFoundException,
			SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers = readAll("select * from tbl_publisher where publisherId = ?",
				new Object[] { publisher.getPublisherId() });

		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();

		try {
			while (rs.next()) {
				Publisher a = new Publisher();
				a.setPublisherId(rs.getInt("publisherId"));
				a.setPublisherName(rs.getString("publisherName"));
				a.setPublisherAddress(rs.getString("publisherAddress"));
				a.setPublisherPhone(rs.getString("publisherPhone"));
				publishers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publishers;
	}
}


