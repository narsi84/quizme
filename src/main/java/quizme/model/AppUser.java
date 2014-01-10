package quizme.model;

import java.util.Date;

import com.googlecode.objectify.annotation.EntitySubclass;

/**
 * Class to store user details
 * 
 * @author narsir
 * 
 */

@EntitySubclass
public class AppUser extends BaseEntity {

	private String email;

	private String userId;

	private Date joiningDate;

	private AppUser() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
}
