package beans;

public class ReviewBean {
	private String bid;
	private String email;
	private String day;
	private int rating;
	private String reviewTitle;
	private String reviewDescription;

	public ReviewBean(String bid, String email, String day, String reviewTitle, String reviewDescription, int rating){
		this.bid = bid;
		this.email = email;
		this.rating = rating;
		this.day = day ;
		this.reviewTitle = reviewTitle;
		this.reviewDescription = reviewDescription;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return day;
	}

	public void setDate(String day) {
		this.day = day;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	}
