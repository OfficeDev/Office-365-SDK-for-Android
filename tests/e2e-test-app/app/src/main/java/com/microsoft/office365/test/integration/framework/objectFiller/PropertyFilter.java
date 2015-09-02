package com.microsoft.office365.test.integration.framework.objectFiller;

public interface PropertyFilter {

    boolean canHandle(PropertyFilterContext context);

    Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException;
}
