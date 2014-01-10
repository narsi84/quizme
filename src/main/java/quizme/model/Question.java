package quizme.model;

import java.util.Date;
import java.util.List;

import quizme.utils.ObjectifyUtils;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@EntitySubclass
@Index
public class Question extends BaseEntity {

	// Date on which this was created
	private Date createdDate;

	@Load
	// Subject to which this belongs (E.g., science, match, religion)
	private Ref<Classification> subject;

	@Load
	// Category (E.g., Zoology, Calculus, Hinduism)
	private Ref<Classification> category;

	@Load
	// Sub-category (E.g., Integral calculus, Protestant, Catholic)
	private Ref<Classification> subCategory;

	// Searchable text
	private String tags;

	@Load
	// User who created this
	private Ref<AppUser> user;

	// Type of question
	private QuestionType type;

	// Difficulty level
	private DifficultyLevel level;

	@Load
	// Group this belongs to
	private Ref<UserGroup> group;

	@Load
	// Details about this
	private Ref<MultiPartContent> details;

	@Load
	// Actual content
	private Ref<MultiPartContent> content;

	// Options
	@Load
	private List<Ref<Option>> options;

	public Question() {
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Classification getSubject() {
		return subject.get();
	}

	public void setSubject(Classification subject) {
		this.subject = Ref.create(subject);
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public AppUser getUserId() {
		return user.get();
	}

	public void setUserId(AppUser _user) {
		this.user = Ref.create(_user);
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public DifficultyLevel getLevel() {
		return level;
	}

	public void setLevel(DifficultyLevel level) {
		this.level = level;
	}

	public UserGroup getGroup() {
		return group.get();
	}

	public void setGroup(UserGroup group) {
		this.group = Ref.create(group);
	}

	public MultiPartContent getContent() {
		return content.get();
	}

	public void setContent(MultiPartContent content) {
		this.content = Ref.create(content);
	}

	public MultiPartContent getDetails() {
		return details.get();
	}

	public void setDetails(MultiPartContent details) {
		this.details = Ref.create(details);
	}

	public List<Option> getOptions() {
		return ObjectifyUtils.getDomainList(options);
	}

	public void setOptions(List<Option> options) {
		this.options = ObjectifyUtils.getRefList(options);
	}
}
