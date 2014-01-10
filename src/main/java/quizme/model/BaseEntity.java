package quizme.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class BaseEntity {

	@Id
	protected Long id;
	
	public BaseEntity() {
		id = System.currentTimeMillis();
	}
	
	public Long getId() {
		return id;
	}
}
