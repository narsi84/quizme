package quizme.model;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;

/**
 * User group class
 * 
 * @author narsir
 * 
 */
@EntitySubclass
public class UserGroup extends BaseEntity {

	private String name;

	private Key<AppUser> ownerId;

	private Date createdDate;

	private Ref<MultiPartContent> description;

	private boolean privateGroup;

	/**
	 * Store member count in this class so that we dont have to do a query every
	 * time on the {@link UserAffiliation}. This count will be updated during
	 * user subscription
	 */

	private long numMembers;

	private UserGroup() {
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
