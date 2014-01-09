package quizme.utils;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;

public class ObjectifyUtils <T> {

	public static <T> List<Ref<T>> getRefList(List<T> objects){
		if (objects == null)
			return null;
		
		List<Ref<T>> refObjs = new ArrayList<>();
		for(T obj : objects)
			refObjs.add(Ref.create(obj));
		return refObjs;
	}
	
	public static <T> List<T> getDomainList(List<Ref<T>> refObjects){
		if (refObjects == null)
			return null;
		
		List<T> domainObjs = new ArrayList<>();
		for(Ref<T> refObj : refObjects)
			domainObjs.add(refObj.get());
		return domainObjs;
	}
}
