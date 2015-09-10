/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @book Gautham
 *
 */
public class BookDAO extends BaseDAO {
	
	public int createBook(Book book) throws ClassNotFoundException,
	SQLException {
return save("insert into tbl_book (bookName,pubId) values(?,?)",
		new Object[] { book.getTitle(),book.getPublisher().getPublisherId() });
}
	
	public int createBookAuthor(Book book) throws ClassNotFoundException,
	SQLException {
		int i=0;
		if(null != book.getAuthors() && book.getAuthors().size()>0 )
		{
			List<Author> authors = book.getAuthors();
			for(Author a:authors)
			i+=save("insert into tbl_book_author (bookId,authorId) values(?,?)",
		new Object[] { book.getBookId(),book.getAuthors() });
		}
		return i;
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
return check("select Count(*) from tbl_book_loans where bookId=?",
	new Object[] { book.getBookId() });
}


public int deleteBookAuthors(Book book) throws ClassNotFoundException,
SQLException {
return save("delete from tbl_book_authors where bookId=?",
	new Object[] { book.getBookId() });
}

public List<Book> getAllBooks() throws ClassNotFoundException,
	SQLException {
return readAll("select * from tbl_book", null);
}

public Book getBook(Book book) throws ClassNotFoundException,
	SQLException {
List<Book> books = new ArrayList<Book>();
books = readAll("select * from tbl_book where bookId = ?",
		new Object[] { book.getBookId() });

if (books != null && books.size() > 0) {
	return books.get(0);
}
return null;
}

@Override
public List<?> extractData(ResultSet rs) {
List<Book> books = new ArrayList<Book>();

try {
	while (rs.next()) {
		Book a = new Book();
		a.setBookId(rs.getInt("bookId"));
		a.setTitle(rs.getString("title"));

		books.add(a);
	}
} catch (SQLException e) {
	e.printStackTrace();
}

return books;
}

}
