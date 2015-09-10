package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.BCopies;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Loans;
import com.gcit.lms.domain.Publisher;

public class JDBC {
	
	private Connection conn;
	
	public JDBC(){
		conn=null;
	}
	
	public int addBook(Book book,List<Author> authors,Publisher pub)
	{
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		try {
			
			getConnection();
			
			if(pub.getPublisherId() != 0){
				pstmt = conn.prepareStatement("insert into tbl_book (title,pubId) values (?,?)",Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, book.getTitle());
				pstmt.setInt(2, pub.getPublisherId());
			}
			else
			{
				pstmt = conn.prepareStatement("insert into tbl_book (title) values (?)",Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, book.getTitle());
				
			}
			
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
			  for (Author a:authors)
			  {
			pstmt = conn.prepareStatement("insert into tbl_book_authors (bookId,authorId) values (?,?)");
			pstmt.setInt(1,  rs.getInt(1));
			pstmt.setInt(2,a.getAuthorId());
			return pstmt.executeUpdate();
			  }
			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
			rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		
		return 0;
	}

	public int addPublisher(Publisher pub) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_Publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pub.getPublisherName());
			pstmt.setString(2, pub.getPublisherAddress());
			pstmt.setString(3, pub.getPublisherPhone());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
			    return  rs.getInt(1);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return 0;
		
		
		
	}

	public int addAuthor(Author author){
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_author (authorName) values (?)",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, author.getAuthorName());
			pstmt.executeUpdate();
			
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
			    return  rs.getInt(1);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		return 0;
		
	}
	
	public List<Branch> getBranch(Branch branch){
		List<Branch> branches = new ArrayList<Branch>();
		try{
			PreparedStatement pstmt=null;
			if(branch == null)
			pstmt = getConnection().prepareStatement("select * from tbl_library_branch");
			else
			{
				pstmt = getConnection().prepareStatement("select * from tbl_library_branch where branchId =?");
				pstmt.setInt(1, branch.getBranchId());
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Branch a = new Branch();
				a.setBranchId(rs.getInt("branchId"));
				a.setBranchName(rs.getString("branchName"));
				a.setBranchAddress(rs.getString("branchAddress"));
				branches.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		return branches;
		
	}
	
	public List<Author> getAuthors(){
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_author");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				
				authors.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return authors;
	}
	
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select bookId,title from tbl_book");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				
				books.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return books;
	}
	
	
	
	public List<Publisher> getPubs(){
		List<Publisher> pubs = new ArrayList<Publisher>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_Publisher");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherPhone(rs.getString("publisherPhone"));
				pubs.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return pubs;
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	private void closeConnection()
	{
		if(conn !=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int deleteAuthor(Author author) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("select bookId from tbl_book_authors where tbl_book_authors.authorId=? ");
			pstmt.setInt(1, author.getAuthorId());
			ResultSet rs = pstmt.executeQuery();
			PreparedStatement delauthor =null;
			if((rs == null) || (!rs.next()))
			{
			
			 delauthor = conn.prepareStatement("delete from tbl_author where authorId = ?");
			delauthor.setInt(1, author.getAuthorId());
			
			return delauthor.executeUpdate();
			}
			else
				return -1;
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		
		
		return 0;
	}

	public int updateAuthor(Author author) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update  tbl_author set authorName=? where authorId=? ");
			pstmt.setString(1, author.getAuthorName());
			pstmt.setInt(2, author.getAuthorId());
			
			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		
		
		
		
		return 0;
	}

	public int updateBook(Book book) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update  tbl_book set title=? where bookId=? ");
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getBookId());
			
			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		
		
		
		
		return 0;
	}

	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book_authors where bookId = ? ");
			pstmt.setInt(1, book.getBookId());
			
			PreparedStatement delauthor =null;
			int i = pstmt.executeUpdate();
			
			if(i>0){
			 delauthor = conn.prepareStatement("delete from tbl_book where authorId = ?");
			delauthor.setInt(1, book.getBookId());
			
			return delauthor.executeUpdate();
			}
			else
				return -1;
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		
		
		return 0;
	}

	public List<BCopies> getCopies(Branch branch)
	{
		List<BCopies> bcopies = new ArrayList<BCopies>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select tbl_book.bookId,title,noOfCopies from tbl_book,tbl_book_copies where branchId = ? and tbl_book_copies.bookId=tbl_book.bookId");
			pstmt.setInt(1, branch.getBranchId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				BCopies b = new BCopies();
				Book book = new Book();
				
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				b.setNoofCopies(rs.getInt("noOfCopies"));
				b.setBook(book);
				b.setBranch(branch);
				
				bcopies.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		return bcopies;
		
	}
	
	
	public int updateBranch(Branch branch) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update  tbl_library_branch set branchName=? , branchAddress=? where branchId=? ");
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());
			pstmt.setInt(3, branch.getBranchId());
			
			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		
		
		
		
		
		return 0;
	}

	public int updateCopies(BCopies b) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_copies set noOfCopies = noOfCopies+? where branchId=? and bookId=?");
			pstmt.setInt(1, b.getAddCopies());
			pstmt.setInt(2, b.getBranch().getBranchId());
			pstmt.setInt(3, b.getBook().getBookId());
			
			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return 0;
	}
	
	public List<Borrower> getBorrowers()
	{
		List<Borrower> borrower =new ArrayList<Borrower>();
		try {
					
					PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower ");
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						Borrower br =new Borrower();
						br.setCardNo(rs.getInt("cardNo"));
						br.setName(rs.getString("name"));
						br.setAddress(rs.getString("address"));
						br.setPhone(rs.getString("phone"));
						borrower.add(br);
						
					}
							
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					closeConnection();
				}
				return borrower;
		
		
		
		
	}

	public List<Loans> getLoans(int cardNo)
	{
		
		List<Loans> loans =new ArrayList<Loans>();
		try {
					
					PreparedStatement pstmt = getConnection().prepareStatement("select tbl_book.bookId,title,tbl_book_loans.branchId,tbl_library_branch.branchName,dateOut,dueDate,dateIn from tbl_book,tbl_book_loans,tbl_library_branch where tbl_book_loans.bookId = tbl_book.bookId and tbl_book_loans.branchId= tbl_library_branch.branchId and cardNo = ? and dateIn is null;");
					pstmt.setInt(1, cardNo);
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						Loans loan =new Loans();
						Book book = new Book();
						Branch branch =new Branch();
						
						book.setBookId(rs.getInt("bookId"));
						branch.setBranchId(rs.getInt("branchId"));
						branch.setBranchName(rs.getString("branchName"));
						book.setTitle(rs.getString("title"));
						loan.setDateOut(rs.getDate("dateOut"));
						loan.setDueDate(rs.getDate("dueDate"));
						loan.setBook(book);
						loan.setBranch(branch);
						
						loans.add(loan);
						
					}
							
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					closeConnection();
				}
				return loans;
	
	}
	public Borrower verifyCard(int cardNo) {
		// TODO Auto-generated method stub
		Borrower br = null;
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower where cardNo=?");
			pstmt.setInt(1, cardNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				br = new Borrower();
				br.setCardNo(cardNo);
				br.setName(rs.getString("name"));
				br.setAddress(rs.getString("address"));
				br.setPhone(rs.getString("phone"));
				
			}
					
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return br;
	}

	public int extendDate(int bookId, int branchId, int cardNo) {
		// TODO Auto-generated method stub
		
try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_loans set dueDate= dueDate + INTERVAL 7 DAY where branchId=? and bookId=? and cardNo = ?");
			pstmt.setInt(1, branchId);
			pstmt.setInt(2,bookId);
			pstmt.setInt(3, cardNo);
			
			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return 0;
	}

	public int returnBook(int bookId, int branchId, int cardNo) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_loans set dateIn= CURDATE() where branchId=? and bookId=? and cardNo = ?");
			pstmt.setInt(1, branchId);
			pstmt.setInt(2,bookId);
			pstmt.setInt(3, cardNo);
			
			if(pstmt.executeUpdate()>0)
			{
				BCopies bc = new BCopies();
				Book book = new Book();
				Branch branch = new Branch();
				book.setBookId(bookId);
				branch.setBranchId(branchId);
				bc.setAddCopies(1);
				bc.setBook(book);
				bc.setBranch(branch);
				
				return updateCopies(bc);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
		return 0;
	}

	
	
	
}
