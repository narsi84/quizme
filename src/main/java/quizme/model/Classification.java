package quizme.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@PersistenceCapable
@Entity
public class Classification {

	@Id
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private ClassificationType type;
	
	public Classification() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ClassificationType getType() {
		return type;
	}
	
	public void setType(ClassificationType type) {
		this.type = type;
	}
}
