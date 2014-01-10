package quizme.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Load;

/**
 * Class to store user score and contributions by category and subject
 * 
 * @author narsir
 * 
 */

@EntitySubclass
public class UserScore extends BaseEntity {

	@Load
	private Ref<AppUser> user;

	@Load
	private Ref<Classification> subject;

	@Load
	private Ref<Classification> category;

	@Load
	private Ref<Classification> subCategory;

	private long numAnswered;

	private long numCorrect;

	private long numContributed;

	private UserScore() {
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
