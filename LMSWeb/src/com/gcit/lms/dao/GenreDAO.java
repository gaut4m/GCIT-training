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
import com.gcit.lms.domain.Genre;

/**
 * @author Gautham
 *
 */
public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int createGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		return save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenreName() });
	}

	public int updateGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		return save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public int deleteGenre(int genreId) throws ClassNotFoundException,
			SQLException {
		if(checkGenre(genreId)==0)
		return save("delete from tbl_genre where genre_id=?",
				new Object[] { genreId });
		return 0;
	}
	
	public int checkGenre(int genreId) throws ClassNotFoundException,
	SQLException {
	return check("select Count(*) from tbl_book_genres where genre_id=?",
		new Object[] {genreId});
	}

	public List<Genre> getAllGenres() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_genre", null);
	}

	public Genre getGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		genres = readAll("select * from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId() });

		if (genres != null && genres.size() > 0) {
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Genre> genres = new ArrayList<Genre>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while (rs.next()) {
				Genre a = new Genre();
				a.setGenreId(rs.getInt("genre_id"));
				a.setGenreName(rs.getString("genre_name"));
				List<Book> books = (List<Book>)bdao.readFirstLevel(
						"select * from tbl_book where bookId in (select bookId from tbl_book_genres where genre_id = ?)",
						new Object[] { a.getGenreId() });
				genres.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return genres;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		
		try {
			while (rs.next()) {
				Genre a = new Genre();
				a.setGenreId(rs.getInt("genre_id"));
				a.setGenreName(rs.getString("genre_name"));
				
				genres.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genres;
	}
}
