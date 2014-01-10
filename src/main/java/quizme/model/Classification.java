package quizme.model;

import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class Classification extends BaseEntity {

	private String name;

	private ClassificationType type;

	public Classification() {
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
