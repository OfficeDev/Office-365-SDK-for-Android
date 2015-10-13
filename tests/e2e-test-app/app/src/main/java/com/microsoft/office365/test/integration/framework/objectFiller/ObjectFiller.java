package com.microsoft.office365.test.integration.framework.objectFiller;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectFiller {

    FilterPipeline filterPipeline;
    Map<SubElementDefinition, Class> subElementClasses = new HashMap<>();

    public ObjectFiller() {
        filterPipeline = new FilterPipeline();
    }

    public void addPropertyFilter(PropertyFilter propertyFilter) {
        filterPipeline.addPropertyFilter(propertyFilter);
    }

    public void setSubElementClass(Class objectClass, String propertyName, Class subElementClass) {
        subElementClasses.put(new SubElementDefinition(objectClass, propertyName), subElementClass);
    }

    public Class getSubElementClass(Object obj, String propertyName) {
        SubElementDefinition subElementDefinition = new SubElementDefinition(obj.getClass(), propertyName);
        if (subElementClasses.containsKey(subElementDefinition)) {
            return subElementClasses.get(subElementDefinition);
        } else {
            return null;
        }
    }

    class SubElementDefinition {
        private Class objectClass;
        private String propertyName;

        SubElementDefinition(Class objectClass, String propertyName) {
            this.objectClass = objectClass;
            this.propertyName = propertyName;
        }

        public Class getObjectClass() {
            return objectClass;
        }

        public String getPropertyName() {
            return propertyName;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof SubElementDefinition)) {
                return false;
            }

            SubElementDefinition sub = (SubElementDefinition)obj;
            return sub.objectClass.equals(objectClass) && sub.propertyName.equals(propertyName);
        }

        @Override
        public int hashCode() {
            return objectClass.hashCode() ^ propertyName.hashCode();
        }
    }

    public void fill(Object obj) throws IllegalAccessException, InstantiationException {
        Class objectClass = obj.getClass();

        //First try to handle entire class
        PropertyFilterContext context1 = new PropertyFilterContext(objectClass.getName(), objectClass, obj);
        Object value = filterPipeline.handleClass(context1, this);

        if(value== null) {
            throw new IllegalArgumentException("Couldn't fill sample " + objectClass.getName() + " with default values");
        }
        /*
        if(value== null) {
            List<Field> fieldList = new ArrayList<Field>();
            Class parentClass = obj.getClass();
            while (parentClass != null) {
                fieldList.addAll(Arrays.asList(parentClass.getDeclaredFields()));
                parentClass = parentClass.getSuperclass();
            }

            for (Field field : fieldList) {
                PropertyFilterContext context = new PropertyFilterContext(field.getName(), field.getType(), obj);
                Object val = filterPipeline.handle(context, this);

                if (val != null) {
                    field.setAccessible(true);
                    field.set(obj, val);
                }
            }
        }
        */
    }
}
