package quizme.model;

import java.util.ArrayList;
import java.util.List;

import quizme.utils.ObjectifyUtils;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Parent;

@EntitySubclass
public class Option extends BaseEntity {

	@Parent
	private Key<Question> question;

	// The list is used only for questions of type MATCH. For all other types,
	// this list will have only 1 element. MATCH questions will rely on the
	// MULTIPARTCOMPONENT key ids
	private List<Ref<MultiPartContent>> content;

	// Indicates the order in a question of type ORDER
	private int order;

	// This will indicate if an option is the correct. Used by QuestionType
	// SINGLE and MULTI.
	private boolean isCorrect;

	public Option(Question _question) throws QuizException {
		if (_question == null)
			throw new QuizException(
					"Trying to create option without associated question");

		content = new ArrayList<Ref<MultiPartContent>>();
	}

	public Option(Question _question, List<MultiPartContent> _contents)
			throws QuizException {
		this(_question);

		if (_contents == null || _contents.size() == 0)
			throw new QuizException("Trying to create option without content");

		for (MultiPartContent c : _contents)
			content.add(Ref.create(c));
	}

	public Option(Question _question, MultiPartContent... _contents)
			throws QuizException {
		this(_question);

		if (_contents == null || _contents.length == 0)
			throw new QuizException("Trying to create option without content");

		for (MultiPartContent c : _contents)
			content.add(Ref.create(c));
	}

	public Key<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Key<Question> question) {
		this.question = question;
	}

	public List<MultiPartContent> getContent() {
		return ObjectifyUtils.getDomainList(content);
	}

	public void setContent(List<MultiPartContent> content) {
		this.content = ObjectifyUtils.getRefList(content);
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
