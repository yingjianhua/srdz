package irille.wpt.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.persistence.Entity;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class MySimplePropertyPreFilter extends SimplePropertyPreFilter {
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if (source == null) {
            return true;
        }

        if (getClazz() != null && !getClazz().isInstance(source)) {
            return true;
        }

        if (this.getExcludes().contains(name)) {
            return false;
        }
        
        if (getMaxLevel() > 0) {
            int level = 0;
            SerialContext context = serializer.getContext();
            Method getfield;
            Annotation annotation = null;
			try {
				getfield = source.getClass().getMethod("get"+Character.toUpperCase((name.charAt(0)))+name.substring(1));
				annotation = getfield.getReturnType().getAnnotation(Entity.class);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
            while (context != null) {
                level++;
                if(level == getMaxLevel() && annotation != null) {
                	serializer.out.write(","+name+":{}");
                	return false;
                }
                if (level > getMaxLevel()) {
                    return false;
                }
                context = context.parent;
            }
        }

        if (getIncludes().size() == 0 || getIncludes().contains(name)) {
            return true;
        }
        
        return false;
    }

}
