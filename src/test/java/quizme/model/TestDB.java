package quizme.model;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizme.persistence.JDODao;
import quizme.persistence.ObjectifyDao;
import quizme.persistence.PMF;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;

public class TestDB {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private List<Object> toStore;

	@Before
	public void setUp() throws QuizException {
		toStore = new ArrayList<>();

		helper.setUp();
		setUpObjectify();
		prepareQuestion();
	}

	private void setUpObjectify(){
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(Classification.class);
		ObjectifyService.register(Content.class);
		ObjectifyService.register(MultiPartContent.class);
		ObjectifyService.register(Option.class);
		ObjectifyService.register(Question.class);
		ObjectifyService.register(UserAffiliation.class);
		ObjectifyService.register(UserGroup.class);
		ObjectifyService.register(UserScore.class);
	}
	
	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testJDO() throws QuizException {
		persistQuestionJDO();
		queryJDO();
	}

	@Test
	public void testObjectify() throws QuizException {
		persistQuestionObjectify();
		queryObjectify();
	}
	
	private void persistQuestionJDO() throws QuizException {
		System.out.println("****** JDO Store ********");
		new JDODao().storeObjects(toStore.toArray());
	}

	private void persistQuestionObjectify() throws QuizException {
		System.out.println("****** Objectify Store ********");
		new ObjectifyDao().storeObjects(toStore.toArray());
	}

	private void prepareQuestion() throws QuizException {
		Question q = new Question();
		q.setType(QuestionType.SINGLE);

		toStore.add(q);
	}
	
	private void prepareQuestion2() throws QuizException {
		Question q = new Question();
		q.setType(QuestionType.SINGLE);

		File f = new File("/Users/narsir/Desktop/test.png");
		Content qC = new Content("What is 1+1", f, ContentType.IMAGE_AFTER_TEXT);
		MultiPartContent qMpc = new MultiPartContent();
		qMpc.addContent(qC);

		q.setContent(qMpc);

		MultiPartContent oMpc1 = new MultiPartContent(new Content("2"));
		Option o1 = new Option(q, oMpc1);
		o1.setCorrect(true);

		MultiPartContent oMpc2 = new MultiPartContent(new Content("3"));
		Option o2 = new Option(q, oMpc2);

		MultiPartContent oMpc3 = new MultiPartContent(new Content("4"));
		Option o3 = new Option(q, oMpc3);

		MultiPartContent oMpc4 = new MultiPartContent(new Content("5"));
		Option o4 = new Option(q, oMpc4);

		toStore.add(q);
		toStore.add(o1);
		toStore.add(o2);
		toStore.add(o3);
		toStore.add(o4);
	}

	private void queryJDO() throws QuizException {
		System.out.println("****** JDO Query ********");
		Query q = PMF.get().getPersistenceManager().newQuery(Question.class);
		q.setFilter("type == typeParam");
		q.declareParameters("String typeParam");

		try {
			List<Question> results = (List<Question>) q.execute("SINGLE");
			if (!results.isEmpty()) {
				for (Question p : results) {
					System.out.println(p.getType());
				}
			} else {
				System.out.println("No results");
			}
		} finally {
			q.closeAll();
		}
	}

	private void queryObjectify() throws QuizException {
		System.out.println("****** Objectify Query ********");
		List<Question> questions = ofy().load().type(Question.class).filter("type", "SINGLE").list();
		if (questions.size() == 0)
			System.out.println("No results");

		for(Question q : questions){
			System.out.println(q.getType());
		}
	}
}
