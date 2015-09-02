package com.microsoft.office365.test.integration.framework.objectFiller;

public class PropertyFilterContext {
    private String propertyName;

    private Class propertyClass;

    private Object propertyObject;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Class getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(Class propertyClass) {
        this.propertyClass = propertyClass;
    }

    public Object getPropertyObject() {
        return propertyObject;
    }

    public void setPropertyObject(Object propertyObject) {
        this.propertyObject = propertyObject;
    }

    public PropertyFilterContext(String propertyName, Class propertyClass, Object propertyObject) {
        this.propertyName = propertyName;
        this.propertyClass = propertyClass;
        this.propertyObject = propertyObject;
    }

    public PropertyFilterContext(PropertyFilterContext source) {
        propertyName = source.propertyName;
        propertyClass = source.propertyClass;
        propertyObject = source.propertyObject;
    }
}
