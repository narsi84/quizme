package quizme.model;


import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * User group class
 * 
 * @author narsir
 * 
 */
@PersistenceCapable
@Entity
public class UserGroup {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Id
	private Long id;

	@Persistent
	private String name;

	@Persistent
	private Key<AppUser> ownerId;

	@Persistent
	private Date createdDate;

	@Persistent
	private Ref<MultiPartContent> description;
	
	@Persistent
	private boolean privateGroup;

	/**
	 * Store member count in this class so that we dont have to do a query every
	 * time on the {@link UserAffiliation}. This count will be updated during
	 * user subscription
	 */
	@Persistent
	private long numMembers;

	private UserGroup() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MultiPartContent getDescription() {
		return description.get();
	}

	public void setDescription(MultiPartContent description) {
		this.description = Ref.create(description);
	}
	
	public long getNumMembers() {
		return numMembers;
	}
	
	public void setNumMembers(long numMembers) {
		this.numMembers = numMembers;
	}
	
	public boolean isPrivateGroup() {
		return privateGroup;
	}
	
	public void setPrivateGroup(boolean privateGroup) {
		this.privateGroup = privateGroup;
	}
	
	public Key<AppUser> getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(Key<AppUser> ownerId) {
		this.ownerId = ownerId;
	}
}
