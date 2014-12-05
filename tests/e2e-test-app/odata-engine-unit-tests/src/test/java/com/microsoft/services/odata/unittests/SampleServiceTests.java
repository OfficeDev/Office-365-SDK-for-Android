package com.microsoft.services.odata.unittests;

import com.microsoft.sampleservice.SampleComplexType;
import com.microsoft.sampleservice.SampleContainerClient;
import com.microsoft.sampleservice.SampleEntity;
import com.microsoft.services.odata.impl.desktop.JvmDependencyResolver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SampleServiceTests extends WireMockTestBase {

    private String url = "http://localhost:8080";

    private JvmDependencyResolver resolver = new JvmDependencyResolver("faketoken");
    private SampleContainerClient client = new SampleContainerClient(url, resolver);

    @Test
    public void queryTwoParamsActionsFirstIsEntityType() throws ExecutionException, InterruptedException {

        Integer result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsEntityType(new SampleEntity(), false)
                .get();

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void queryTwoParamsActionsFirstIsComplexType() throws ExecutionException, InterruptedException {

        Integer result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsComplexType(new SampleComplexType(), false)
                .get();

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void queryTwoParamsActionsFirstIsCollectionEntityType() throws ExecutionException, InterruptedException {

        List<SampleEntity> entities = new ArrayList<SampleEntity>();
        SampleEntity entity = new SampleEntity();
        entities.add(entity);

        Integer result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsCollectionEntityType(entities, false)
                .get();

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void queryTwoParamsActionsFirstIsCollectionComplexType() throws ExecutionException, InterruptedException {

        List<SampleComplexType> complexTypes = new ArrayList<SampleComplexType>();
        SampleComplexType complexType = new SampleComplexType();
        complexTypes.add(complexType);

        Integer result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsCollectionComplexType(complexTypes, false)
                .get();

        assertThat(result, is(notNullValue()));
    }

}
