package quizme.model;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Class to store user details
 * @author narsir
 *
 */

@PersistenceCapable
@Entity
public class AppUser {

	@Persistent
	@Id
	private Long id;
	
	@Persistent
	private String email;
	
	@Persistent
	private String userId;
	
	@Persistent
	private Date joiningDate;
	
	private AppUser(){	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
