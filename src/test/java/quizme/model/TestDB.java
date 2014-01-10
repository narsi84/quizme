package quizme.model;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizme.persistence.ObjectifyDao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;

public class TestDB {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private List<Object> toStore;

	@Before
	public void setUp() throws QuizException {
		toStore = new ArrayList<>();

		helper.setUp();
//		setUpObjectify();
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
	public void testObjectify() throws QuizException {
		persistQuestionObjectify();
		queryObjectify();
	}
	
	private void persistQuestionObjectify() throws QuizException {
		System.out.println("****** Objectify Store ********");
		new ObjectifyDao().storeObjects(toStore.toArray());
	}

	private Question prepareQuestion() throws QuizException {
		Question q = new Question();
		q.setType(QuestionType.SINGLE);
		
		Classification subject = new Classification();
		subject.setName("Chemistry");
		subject.setType(ClassificationType.SUBJECT);
				
		q.setCategory(subject);
		
		
		toStore.add(q);
		return q;
	}
	
	@Test
	public void testReflection() throws IllegalArgumentException, IllegalAccessException, QuizException{
		persistObject(prepareQuestion());
	}
	
	private void persistObject(Object obj) throws IllegalArgumentException, IllegalAccessException{
		if (!isEntity(obj))
			return;

		for (Field f : obj.getClass().getDeclaredFields()){
			if (Collection.class.isAssignableFrom(f.getType())){
				for (Object elem : (Collection<?>) f.get(obj)){
					persistObject(elem);
				}
			}
			
			System.out.println(" **** Persisting " + f.getName() + " ***** ");
			persistObject(f.get(obj));
		}
		
		System.out.println(" **** Saving " + obj.getClass().getCanonicalName() + " ***** ");
		ofy().save().entities(obj).now();
	}
	
	private boolean isEntity(Object obj){
		for (Annotation annotation : obj.getClass().getDeclaredAnnotations()){
			if (Entity.class.isAssignableFrom(annotation.annotationType())){
				return true;
			}
		}
		return false;
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
