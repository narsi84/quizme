package quizme.model;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

/**
 * Class to store user subscriptions to groups
 * 
 * @author narsir
 * 
 */

@EntitySubclass
public class UserAffiliation extends BaseEntity {

	private Key<AppUser> user;

	private Key<UserGroup> group;

	private Date userJoiningDate;

	private UserAffiliation() {
	}

	public Key<UserGroup> getGroup() {
		return group;
	}

	public void setGroup(Key<UserGroup> group) {
		this.group = group;
	}

	public Key<AppUser> getUser() {
		return user;
	}

	public void setUser(Key<AppUser> user) {
		this.user = user;
	}

	public Date getUserJoiningDate() {
		return userJoiningDate;
	}

	public void setUserJoiningDate(Date userJoiningDate) {
		this.userJoiningDate = userJoiningDate;
	}
}
