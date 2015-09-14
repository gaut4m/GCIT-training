/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

/**
 * @book Gautham
 *
 */
@SuppressWarnings("unchecked")
public class BookDAO extends BaseDAO {

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void createBook(Book book) throws ClassNotFoundException,
			SQLException {
		int bookId = save(
				"insert into tbl_book (title, pubId) values(?, ?)",
				new Object[] { book.getTitle(),
						book.getPublisher().getPublisherId() });
		if(bookId>0)
		{
		for (Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for (Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genreId) values (?,?)",
					new Object[] { bookId, g.getGenreId() });
		}
		}

	}

	public List<Book> getAllBooks() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_books", null);
	}

	@Override
	public List<Book> extractData(ResultSet rs) {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(conn);
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		try {
			while (rs.next()) {
				Book b = new Book();

				b.setBookId(rs.getInt("bookId"));

				b.setTitle(rs.getString("title"));
				b.setPublisher(pdao.getPublisherById(rs.getInt("pubId")));

				List<Author> authors = (List<Author>)adao.readFirstLevel(
						"select * from tbl_author where authorId in (select authorId from tbl_book_authors where bookId = ?)",
						new Object[] { b.getBookId() });
				b.setAuthors(authors);
				
				List<Genre> genres = (List<Genre>) gdao.readAll(
						"select * from tbl_genre where genre_id in (select genre_id from tbl_book_genres where bookId = ?)",
						new Object[] { b.getBookId() });
				b.setGenres(genres);
				
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(conn);
		try {
			while (rs.next()) {
				Book b = new Book();

				b.setBookId(rs.getInt("bookId"));

				b.setTitle(rs.getString("title"));
				
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}


public int updateBook(Book book) throws ClassNotFoundException,
	SQLException {
return save("update tbl_book set bookName = ? where bookId = ?",
		new Object[] { book.getTitle(), book.getBookId() });
}

public int deleteBook(Book book) throws ClassNotFoundException,
	SQLException {
	int i=0;
	if(checkBook(book) == 0)
		i=save("delete from tbl_book where bookId=?",
		new Object[] { book.getBookId() });
	if(i>0)
		return deleteBookAuthors(book);
	else
		return 0;
}

public int checkBook(Book book) throws ClassNotFoundException,
SQLException {
return check("select Count(*) from tbl_book_loans where bookId=? and dateIn IS NOT NULL",
	new Object[] { book.getBookId() });
}


public int deleteBookAuthors(Book book) throws ClassNotFoundException,
SQLException {
return save("delete from tbl_book_authors where bookId=?",
	new Object[] { book.getBookId() });
}



public Book getBook(int bookId) throws ClassNotFoundException,
	SQLException {
List<Book> books = new ArrayList<Book>();
books = readAll("select * from tbl_book where bookId = ?",
		new Object[] { bookId });

if (books != null && books.size() > 0) {
	return books.get(0);
}
return null;
}



}
