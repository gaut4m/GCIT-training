package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoansDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Loans;
import com.gcit.lms.domain.Publisher;

public class AdministratorService {

	ConnectionUtil connUtil = new ConnectionUtil();
	
	public int createService(Object obj) throws Exception
	{
		
		if( obj instanceof Author)
			return createAuthor((Author)obj);
		else if(obj instanceof Book)
			return createBook((Book)obj);
		else if	(obj instanceof Genre)	
			return createGenre((Genre)obj);
		else if(obj instanceof Publisher)
			return createPublisher((Publisher)obj);
		else if(obj instanceof Borrower)
			return createBorrower((Borrower)obj);
		else if(obj instanceof Branch)
			return createBranch((Branch)obj);
		else
			return 0;
			
	}
	
	public int updateService(Object obj) throws Exception
	{
		
		if( obj instanceof Author)
			return updateAuthor((Author)obj);
		else if(obj instanceof Book)
			return createBook((Book)obj);
		else if	(obj instanceof Genre)	
			return updateGenre((Genre)obj);
		else if(obj instanceof Publisher)
			return updatePublisher((Publisher)obj);
		else if(obj instanceof Borrower)
			return updateBorrower((Borrower)obj);
		else if(obj instanceof Branch)
			return updateBranch((Branch)obj);
		else
			return 0;
			
	}
	
	

	

	

	private int updateGenre(Genre obj) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		GenreDAO bdao = new GenreDAO(conn);
		
		try{
			if(obj.getGenreName() !=null && obj.getGenreName() !="" ){
			if(obj.getGenreName().length() > 20){
				throw new Exception("Genre Name cannot be more than 20 chars");
			}else{
				bdao.updateGenre(obj);
				i=1;
			}
			
			}
			else
				throw new Exception("Genre Name cannot be null");
				
				conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int updateBranch(Branch obj) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BranchDAO bdao = new BranchDAO(conn);
		
		try{
			if(obj.getBranchName() !=null && obj.getBranchName() !=""){
			if(obj.getBranchName().length() > 20){
				throw new Exception("Branch Name cannot be more than 20 chars");
			}else{
				bdao.updateBranch(obj);
				i=1;
			}
			
			}
			else
				throw new Exception("Branch Name and Address cannot be null");
				
				conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	public List<?> getService(String str ) throws Exception
	{
		switch(str)
		{
		case "pubs":
			return (List<?>) getPublisher();
		case "authors":
			return (List<?>) getAuthor();
		case "books":
			return (List<?>) getBook();
		case "users":
			return (List<?>)getBorrower();
		case "genres":
			return (List<?>)getGenres();
		case "branch":
			return (List<?>)getBranch();
		
		
		default:
			break;
		}
		return null;
	}

	
	

	private List<?> getBook() {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
		
		
		BookDAO bdao = new BookDAO(conn);
		
			
			return bdao.getAllBooks();
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn !=null)
			{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return null;
	}

	private List<?> getBranch() throws Exception {
		// TODO Auto-generated method stub
Connection conn = ConnectionUtil.getConnection();
		
		BranchDAO adao = new BranchDAO(conn);
		try{
			
			return adao.getAllBranchs();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	private List<?> getGenres() throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionUtil.getConnection();
		
		GenreDAO adao = new GenreDAO(conn);
		try{
			
			return adao.getAllGenres();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	private List<?> getBorrower() throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionUtil.getConnection();
		
		BorrowerDAO bdao = new BorrowerDAO(conn);
		try{
			
			return bdao.getAllBorrowers();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public Object getServiceById(String str, int id) throws Exception
	{

		switch(str)
		{
		
		case "pubById":
			return getPublisher(id);
		case "cardNo":
			return getBorrower(id);
		case "book":
			return getBook(id);
		
		default:
			break;
		}
		return null;
	}
	
	private Object getBook(int bookId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
		
		
		BookDAO bdao = new BookDAO(conn);
		
			
			return bdao.getBook(bookId);
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn !=null)
			{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return null;
	}

	public List<Loans> getLoans(int cardNo) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		LoansDAO bdao = new LoansDAO(conn);
		try{
			
			return bdao.getLoans(cardNo);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	private Object getBorrower(int cardNo) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionUtil.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		try{
			
			return bdao.getBorrower(cardNo);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	
	private int updateBorrower(Borrower obj) throws Exception{
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		
		try{
			
			if(obj.getName().length() > 20){
				throw new Exception("Borrower Name cannot be more than 20 chars");
			}else{
				bdao.updateBorrower(obj);
				i=1;
			}
			
			
			conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}
	public int deleteService(String str, int id) throws Exception {
		// TODO Auto-generated method stub
		switch(str)
		{
		
		case "pub":
			return deletePublisher(id);
			
		case "cardNo":
				return deleteBorrower(id);
		case "branch":
			return deleteBranch(id);
		case "genre":
			return deleteGenre(id);
				
		default:
			break;
		}
		
		return 0;
	}
	
	private int deleteGenre(int genreId) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		GenreDAO bdao = new GenreDAO(conn);
		try{
			i= bdao.deleteGenre(genreId);
			if(i>0)
				conn.commit();
			else
				conn.rollback();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int deleteBranch(int branchId) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BranchDAO bdao = new BranchDAO(conn);
		try{
			i= bdao.deleteBranch(branchId);
			if(i>0)
				conn.commit();
			else
				conn.rollback();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int deleteBorrower(int cardNo) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		try{
			i= bdao.deleteBorrower(cardNo);
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int updatePublisher(Publisher obj)throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		
		try{
			if(obj.getPublisherName() !=null){
			if(obj.getPublisherName().length() > 20){
				throw new Exception("Publisher Name cannot be more than 20 chars");
			}else{
				pdao.updatePublisher(obj);
				i=1;
			}
			
			}
			conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int deletePublisher(int publisherId)throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		try{
			i= pdao.deletePublisher(publisherId);
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private Object getPublisher(int id) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionUtil.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		try{
			
			return pdao.getPublisherById(id);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	private List<?> getPublisher() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		i=0;
		PublisherDAO pdao = new PublisherDAO(conn);
		try{
			
			return pdao.getAllPublishers();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	private int createBranch(Branch obj) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BranchDAO bdao = new BranchDAO(conn);
		
		try{
			if(obj.getBranchName() !=null && obj.getBranchName() !="" && obj.getBranchAddress() != null && obj.getBranchAddress() != ""){
			if(obj.getBranchName().length() > 20){
				throw new Exception("Branch Name cannot be more than 20 chars");
			}else{
				bdao.createBranch(obj);
				i=1;
			}
			
			}
			else
				throw new Exception("Branch Name and Address cannot be null");
				
				conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int createBorrower(Borrower obj) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		
		try{
			if(obj.getName() !=null){
			if(obj.getName().length() > 20){
				throw new Exception("Borrower Name cannot be more than 20 chars");
			}else{
				bdao.createBorrower(obj);
				i=1;
			}
			
			}
			conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int createPublisher(Publisher obj) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		
		try{
			if(obj.getPublisherName() !=null && obj.getPublisherName() !=""){
			if(obj.getPublisherName().length() > 20){
				throw new Exception("Publisher Name cannot be more than 20 chars");
			}else{
				pdao.createPublisher(obj);
				i=1;
			}
			
			}
			else
				throw new Exception("Publisher Name is empty");
			conn.commit();
			}
			catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}
	
	

	private int createGenre(Genre genre) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		GenreDAO gdao = new GenreDAO(conn);
		
		try{
			if(genre.getGenreName() !=null && genre.getGenreName() != ""){
			if(genre.getGenreName().length() > 20){
				throw new Exception("Genre Name cannot be more than 20 chars");
			}else{
				gdao.createGenre(genre);
				i=1;
				}
			}
			else
				throw new Exception("Genre Name cannot be null");
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	private int createBook(Book obj) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public int createAuthor(Author author) throws Exception {
		//Boolean flag = validateAuthor(author)
		int i=0;
			Connection conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			try{
				if(author.getAuthorName()!=null && author.getAuthorName() !=""){
				if(author.getAuthorName().length() > 45){
					throw new Exception("Author Name cannot be more than 45 chars");
				}else{
					adao.createAuthor(author);
					i=1;
					}
				}
				conn.commit();
				
			}catch(Exception e){
				e.printStackTrace();
				conn.rollback();
			}finally{
				conn.close();
			}
			return i;
	}
	
	private List<?> getAuthor() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = ConnectionUtil.getConnection();
		i=0;
		AuthorDAO adao = new AuthorDAO(conn);
		try{
			
			return adao.getAllAuthors();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	private int updateAuthor(Author author) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = connUtil.getConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		try{
			if(author.getAuthorName()!=null && author.getAuthorName() !=""){
			if(author.getAuthorName().length() > 45){
				throw new Exception("Author Name cannot be more than 45 chars");
			}else{
				adao.updateAuthor(author);
				i=1;
				}
			}
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	public int extendDate(int bookId, int branchId, int cardNo) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn = connUtil.getConnection();
		LoansDAO adao = new LoansDAO(conn);
		try{
			
				i= adao.extendLoan(bookId,branchId,cardNo);
				
			if(i>0)	
			conn.commit();
			else
				conn.rollback();
			
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return i;
	}

	
}
