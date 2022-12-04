package vnu.uet.prodmove.utils.querier;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is used to filter attributes to response.
 */
public class ObjectQuerier<T> {
    private T obj;
    private List<String> fields;
    private Map<String, Method> methods;

    public ObjectQuerier(T obj) {
        this.obj = obj;
        this.fields = new ArrayList<String>();
        this.methods = new HashMap<String, Method>();
        
        Field[] _fields = obj.getClass().getDeclaredFields();
        for (Field field : _fields) {
            this.fields.add(field.getName());
        }
        Method[] methods = this.obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            this.methods.put(method.getName(), method);
        }
    }

    public static <T> ObjectQuerier<T> of(T obj) {
        return new ObjectQuerier<T>(obj);
    }

    /**
     * Get object.
     * @return return Object
     */
    public T get() {
        return this.obj;
    }

    /**
     * 
     * @param attributes Attributes needed to include
     * @return Object after filtering attributes.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public ObjectQuerier<T> include(String... attributes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.filterAttributes(attributes, true);
        return this;
    }

    /**
     * 
     * @param attributes Attributes needed to exclude
     * @return Object after filtering attributes.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public ObjectQuerier<T> exclude(String... attributes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.filterAttributes(attributes, false);
        return this;
    }

    private void filterAttributes(String[] _attributes, boolean isNeeded) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String> attributes = new ArrayList<String>(Arrays.asList(_attributes));
        List<String> attrs = new ArrayList<>();
        if (isNeeded) {
            attrs = fields.stream().filter(f -> {
                return !attributes.contains(f);
            }).collect(Collectors.toList());
        } else {
            attrs.addAll(attributes);
        }
        
        // set all of attributes are null
        for (String attribute : attrs) {
            String methodName = "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            Method method = this.getMethod(methodName);
            method.invoke(this.obj, (Object) null);
        }
    }
    
    private Method getMethod(String methodName) {
        return methods.get(methodName);
    }
}
