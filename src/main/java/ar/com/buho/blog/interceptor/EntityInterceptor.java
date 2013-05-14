package ar.com.buho.blog.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import ar.com.buho.blog.model.Post;

public class EntityInterceptor extends EmptyInterceptor {

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (entity instanceof Post) {
			
		}
		return false;
	}

	
}
