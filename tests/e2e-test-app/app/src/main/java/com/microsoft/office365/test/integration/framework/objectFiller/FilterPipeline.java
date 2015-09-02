package com.microsoft.office365.test.integration.framework.objectFiller;

import java.util.ArrayList;
import java.util.List;

public class FilterPipeline {

    List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();

    public FilterPipeline() {
    }

    public void addPropertyFilter(PropertyFilter propertyFilter) {
        propertyFilters.add(0, propertyFilter);
    }

    public Object handleClass(PropertyFilterContext context, ObjectFiller objectFiller) throws InstantiationException, IllegalAccessException {
        for (PropertyFilter filter : propertyFilters) {
            if (filter.canHandle(context)) {
                return filter.handle(context, objectFiller);
            }
        }

        return null;
    }
}