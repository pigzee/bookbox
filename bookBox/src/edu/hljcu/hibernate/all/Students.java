package edu.hljcu.hibernate.all;

/**
 * StudentsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Students implements java.io.Serializable {

	// Fields

	private Integer studId;
	private String studNo;
	private String studName;
	private String bookNo;

	// Constructors

	/** default constructor */
	public Students() {
	}

	/** full constructor */
	public Students(Integer studId, String studNo, String studName,
			String bookNo) {
		this.studId = studId;
		this.studNo = studNo;
		this.studName = studName;
		this.bookNo = bookNo;
	}

	// Property accessors

	public Integer getStudId() {
		return this.studId;
	}

	public void setStudId(Integer studId) {
		this.studId = studId;
	}

	public String getStudNo() {
		return this.studNo;
	}

	public void setStudNo(String studNo) {
		this.studNo = studNo;
	}

	public String getStudName() {
		return this.studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getBookNo() {
		return this.bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Students))
			return false;
		Students castOther = (Students) other;

		return ((this.getStudId() == castOther.getStudId()) || (this
				.getStudId() != null
				&& castOther.getStudId() != null && this.getStudId().equals(
				castOther.getStudId())))
				&& ((this.getStudNo() == castOther.getStudNo()) || (this
						.getStudNo() != null
						&& castOther.getStudNo() != null && this.getStudNo()
						.equals(castOther.getStudNo())))
				&& ((this.getStudName() == castOther.getStudName()) || (this
						.getStudName() != null
						&& castOther.getStudName() != null && this
						.getStudName().equals(castOther.getStudName())))
				&& ((this.getBookNo() == castOther.getBookNo()) || (this
						.getBookNo() != null
						&& castOther.getBookNo() != null && this.getBookNo()
						.equals(castOther.getBookNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStudId() == null ? 0 : this.getStudId().hashCode());
		result = 37 * result
				+ (getStudNo() == null ? 0 : this.getStudNo().hashCode());
		result = 37 * result
				+ (getStudName() == null ? 0 : this.getStudName().hashCode());
		result = 37 * result
				+ (getBookNo() == null ? 0 : this.getBookNo().hashCode());
		return result;
	}

}