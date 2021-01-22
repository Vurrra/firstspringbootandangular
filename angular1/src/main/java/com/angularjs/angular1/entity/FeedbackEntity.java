package com.angularjs.angular1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "feedbackid", initialValue = 501, allocationSize = 1)
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedbackid")
	private int feedbackid;
	private int userid;
	private int rating;
	private String feedback;
	private long vehicleid;

	public int getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(int feedbackid) {
		this.feedbackid = feedbackid;
	}

	
	
	public long getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(long vehicleid) {
		this.vehicleid = vehicleid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "FeedbackEntity [feedbackid=" + feedbackid + ", userid=" + userid + ", rating=" + rating + ", feedback="
				+ feedback + "]";
	}

}
