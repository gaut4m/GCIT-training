/**
 * 
 */
package com.gcit.lms.dao;

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

	public int createGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		return save("insert into tbl_genre (genreName) values(?)",
				new Object[] { genre.getGenreName() });
	}

	public int updateGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		return save("update tbl_genre set genreName = ? where genreId = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public int deleteGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		if(checkGenre(genre)==0)
		return save("delete from tbl_genre where genreId=?",
				new Object[] { genre.getGenreId() });
		return 0;
	}
	
	public int checkGenre(Genre genre) throws ClassNotFoundException,
	SQLException {
	return check("select Count(*) from tbl_book_genre where genreId=?",
		new Object[] {genre.getGenreId()});
	}

	public List<Genre> getAllGenres() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_genre", null);
	}

	public Genre getGenre(Genre genre) throws ClassNotFoundException,
			SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		genres = readAll("select * from tbl_genre where genreId = ?",
				new Object[] { genre.getGenreId() });

		if (genres != null && genres.size() > 0) {
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
		List<Genre> genres = new ArrayList<Genre>();

		try {
			while (rs.next()) {
				Genre a = new Genre();
				a.setGenreId(rs.getInt("genreId"));
				a.setGenreName(rs.getString("genreName"));
			
				genres.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genres;
	}
}
