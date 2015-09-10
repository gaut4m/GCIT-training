package com.gcit.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;

public class AuthorDAO extends BaseDAO {

	public void createAuthor(Author author) throws ClassNotFoundException,
			SQLException {
		save("insert into tbl_author (authorName) values(?)",
				new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author) throws ClassNotFoundException,
			SQLException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public int deleteAuthor(Author author) throws ClassNotFoundException,
			SQLException {
		
		if(checkAuthor(author) == 0)
			return save("delete from tbl_author where authorId=?",
				new Object[] { author.getAuthorId() });
		return 0;
	}
	
	private int checkAuthor(Author author) throws ClassNotFoundException,
	SQLException {
		
		return check("select count(*) from tbl_book_authors where authorId=?",
				new Object[] { author.getAuthorId() });

	
}

	public List<Author> getAllAuthors() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_author", null);
	}

	public Author getAuthor(Author author) throws ClassNotFoundException,
			SQLException {
		List<Author> authors = new ArrayList<Author>();
		authors = readAll("select * from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });

		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();

		try {
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));

				authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return authors;
	}
}
