/**
 * 
 */
package com.gcit.lms.domain;

/**
 * @author Gautham
 *
 */
public class BCopies {
	private int bookId;
	private String title;
	private int noofCopies;
	private int addCopies;
	private static int BranchId;
	/**
	 * @return the branchId
	 */
	public static int getBranchId() {
		return BranchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public static void setBranchId(int branchId) {
		BranchId = branchId;
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
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
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

}
