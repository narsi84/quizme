package quizme.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Class to store user score and contributions by category and subject
 * @author narsir
 *
 */

@PersistenceCapable
@Entity
public class UserScore {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Id
	private Long id;
	
	@Persistent
	@Unowned	
	@Load
	private Ref<AppUser> user;
	
	@Persistent
	@Unowned
	@Load
	private Ref<Classification> subject;
	
	@Persistent 
	@Unowned
	@Load
	private Ref<Classification> category;
	
	@Persistent
	@Unowned
	@Load
	private Ref<Classification> subCategory;
	
	@Persistent
	private long numAnswered;
	
	@Persistent
	private long numCorrect;
	
	@Persistent
	private long numContributed;

	private UserScore() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumAnswered() {
		return numAnswered;
	}

	public void setNumAnswered(long numAnswered) {
		this.numAnswered = numAnswered;
	}

	public long getNumCorrect() {
		return numCorrect;
	}

	public void setNumCorrect(long numCorrect) {
		this.numCorrect = numCorrect;
	}

	public long getNumContributed() {
		return numContributed;
	}

	public void setNumContributed(long numContributed) {
		this.numContributed = numContributed;
	}

	public AppUser getUser() {
		return user.get();
	}

	public void setUser(AppUser user) {
		this.user = Ref.create(user);
	}

	public Classification getSubject() {
		return subject.get();
	}

	public void setSubject(Classification subject) {
		this.subject = Ref.create(subject);
	}

	public Classification getCategory() {
		return category.get();
	}

	public void setCategory(Classification category) {
		this.category = Ref.create(category);
	}

	public Classification getSubCategory() {
		return subCategory.get();
	}

	public void setSubCategory(Classification subCategory) {
		this.subCategory = Ref.create(subCategory);
	}
}
