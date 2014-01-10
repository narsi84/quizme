package quizme.model;

import java.util.ArrayList;
import java.util.List;

import quizme.utils.ObjectifyUtils;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class MultiPartContent extends BaseEntity {

	private List<Ref<Content>> contents;

	private int index;

	public MultiPartContent() {
		contents = new ArrayList<>();
	}

	public MultiPartContent(List<Content> _contents) {
		contents = new ArrayList<>();
		if (_contents == null || _contents.size() == 0)
			return;

		for (Content c : _contents)
			addContent(c);
	}

	public MultiPartContent(Content... _contents) {
		contents = new ArrayList<>();
		if (_contents == null || _contents.length == 0)
			return;

		for (Content c : _contents)
			addContent(c);
	}

	public List<Content> getContents() {
		return ObjectifyUtils.getDomainList(contents);
	}

	public void setContents(List<Content> contents) {
		this.contents = ObjectifyUtils.getRefList(contents);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void addContent(Content content) {
		if (content != null) {
			contents.add(Ref.create(content));
			if (content.getIndex() == -1)
				content.setIndex(contents.size());
		}
	}
}
