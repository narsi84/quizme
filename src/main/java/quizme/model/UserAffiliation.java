package quizme.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Class to store user subscriptions to groups
 * @author narsir
 *
 */

@PersistenceCapable
@Entity
public class UserAffiliation {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Id
	private Long id;
	
	@Persistent
	@Unowned
	private Key<AppUser> user;
	
	@Persistent
	@Unowned
	private Key<UserGroup> group;
	
	@Persistent
	private Date userJoiningDate;
	
	private UserAffiliation() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
