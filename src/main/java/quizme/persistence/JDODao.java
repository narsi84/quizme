package quizme.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.PersistenceManager;

import quizme.model.QuizException;

public class JDODao implements GenericDao {

	public Object storeObjects(Object... _obj) throws QuizException {
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Map<Object, Object> returns = new HashMap<>();
			for (Object obj : _obj)
				returns.put(_obj, pm.makePersistent(obj));
			pm.close();
			return returns;
		} catch (Exception e) {
			throw new QuizException(e.getLocalizedMessage());
		}
	}
}
