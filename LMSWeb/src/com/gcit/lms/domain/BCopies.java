/**
 * 
 */
package com.gcit.lms.domain;

/**
 * @author Gautham
 *
 */
public class BCopies {
	private int noofCopies;
	private int addCopies;
	private Book book;
	private Branch branch;
	
	/**
	 * @return the noofCopies
	 */
	public int getNoofCopies() {
		return noofCopies;
	}
	/**
	 * @param noofCopies the noofCopies to set
	 */
	public void setNoofCopies(int noofCopies) {
		this.noofCopies = noofCopies;
	}
	/**
	 * @return the addCopies
	 */
	public int getAddCopies() {
		return addCopies;
	}
	/**
	 * @param addCopies the addCopies to set
	 */
	public void setAddCopies(int addCopies) {
		this.addCopies = addCopies;
	}
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

}
