package quizme.persistence;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Map;

import quizme.model.QuizException;

import com.googlecode.objectify.Key;

public class ObjectifyDao implements GenericDao {

	@Override
	public Map<Key<Object>, Object> storeObjects(Object... objects) throws QuizException{
		try {
			return ofy().save().entities(objects).now();
		} catch (Exception e) {
			throw new QuizException(e.getLocalizedMessage());
		}
	}

}
