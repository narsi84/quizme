package quizme.model;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class Content extends BaseEntity {

	// TODO: Externalize this so it can be loaded from file or DB
	private static final long MAX_MEDIA_SIZE = 1048576;

	private String text;

	private Blob media;

	private ContentType type;

	private int index;

	public Content() {
		index = -1;
	}

	public Content(String text, File file, ContentType type)
			throws QuizException {
		if (text == null || text.length() == 0)
			throw new QuizException("Invalid text for content");

		this.text = text;
		this.media = getBlobFromFile(file);
		this.type = type;
	}

	public Content(String text) throws QuizException {
		if (text == null || text.length() == 0)
			throw new QuizException("Invalid text for content");
		this.text = text;
		this.type = ContentType.TEXT;
	}

	public Content(File file) throws QuizException {
		this.media = getBlobFromFile(file);
		this.type = ContentType.IMAGE;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Blob getMedia() {
		return media;
	}

	public void setMedia(Blob media) {
		this.media = media;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private Blob getBlobFromFile(File file) throws QuizException {
		Blob b = null;
		try {
			FileInputStream fstr = new FileInputStream(file);
			b = new Blob(IOUtils.toByteArray(fstr));
		} catch (Exception e) {
			throw new QuizException(
					"Error when creating content from file. Message: "
							+ e.getMessage());
		}
		if (b != null && b.getBytes().length > MAX_MEDIA_SIZE)
			throw new QuizException("Image from " + file.getName()
					+ " exceeds size limit");

		return b;
	}
}
