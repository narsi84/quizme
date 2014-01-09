package quizme.persistence;

import quizme.model.QuizException;

public interface GenericDao {

	public Object storeObjects(Object... objects) throws QuizException;
}
