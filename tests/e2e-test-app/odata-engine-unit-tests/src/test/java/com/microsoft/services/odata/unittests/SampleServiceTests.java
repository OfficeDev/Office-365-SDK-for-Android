package com.microsoft.services.odata.unittests;

import com.microsoft.sampleservice.AnotherEntity;
import com.microsoft.sampleservice.SampleComplexType;
import com.microsoft.sampleservice.SampleContainerClient;
import com.microsoft.sampleservice.SampleEntity;
import com.microsoft.services.odata.Helpers;
import com.microsoft.services.odata.impl.desktop.JvmDependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SampleServiceTests extends WireMockTestBase {

    private String url = "http://localhost:8080";

    private JvmDependencyResolver resolver = new JvmDependencyResolver("faketoken");
    private SampleContainerClient client = new SampleContainerClient(url, resolver);

    @Test
    public void testTwoParamsActionsFirstIsEntityTypeUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsEntityTypePOST.json
        Integer result = null;
        try{
           result = client.getMe()
                    .getOperations()
                    .twoParamsActionsFirstIsEntityType(getSampleEntity(), false)
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testTwoParamsActionsFirstIsComplexTypeUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionComplexTypePOST.json
        Integer result = null;
        SampleComplexType sampleComplexEntity = new SampleComplexType();
        try{
         result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsComplexType(sampleComplexEntity, false)
                .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testTwoParamsActionsFirstIsComplexTypeRawUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionComplexTypePOST.json
        String result = null;
        SampleComplexType sampleComplexEntity = new SampleComplexType();
        String serializedEntity = new String(Helpers.serializeToJsonByteArray(sampleComplexEntity, resolver));
        try{
            result = client.getMe()
                    .getOperations()
                    .twoParamsActionsFirstIsComplexTypeRaw(serializedEntity, "false")
                    .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
    }
    @Test
    public void testTwoParamsActionsFirstIsCollectionEntityTypeUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionEntityTypePOST.json
        List<SampleEntity> entities = new ArrayList<SampleEntity>();
        SampleEntity entity = new SampleEntity();
        entities.add(entity);
        Integer result = null;
        try{
         result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsCollectionEntityType(entities, false)
                .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testTwoParamsActionsFirstIsCollectionEntityTypeRawUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionEntityTypePOST.json
        List<SampleEntity> entities = new ArrayList<SampleEntity>();
        SampleEntity entity = new SampleEntity();
        entities.add(entity);
        String result = null;

        String serializedEntity = new String(Helpers.serializeToJsonByteArray(entities, resolver));
        try{
            result = client.getMe()
                    .getOperations()
                    .twoParamsActionsFirstIsCollectionEntityTypeRaw(serializedEntity, "false")
                    .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testTwoParamsActionsFirstIsCollectionComplexTypeUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionComplexTypePOST.json
        List<SampleComplexType> complexTypes = new ArrayList<SampleComplexType>();
        SampleComplexType complexType = new SampleComplexType();
        complexTypes.add(complexType);
        Integer result = null;
        try{
         result = client.getMe()
                .getOperations()
                .twoParamsActionsFirstIsCollectionComplexType(complexTypes, false)
                .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testTwoParamsActionsFirstIsCollectionComplexTypeRawUri() throws ExecutionException, InterruptedException {
        //twoParamsActionsFirstIsCollectionComplexTypePOST.json
        List<SampleComplexType> complexTypes = new ArrayList<SampleComplexType>();
        SampleComplexType complexType = new SampleComplexType();
        complexTypes.add(complexType);

        String serializedEntity = new String(Helpers.serializeToJsonByteArray(complexTypes, resolver));
        String result = null;
        try{
            result = client.getMe()
                    .getOperations()
                    .twoParamsActionsFirstIsCollectionComplexTypeRaw(serializedEntity, "false")
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testGetNavigationList() throws ExecutionException, InterruptedException {
        //getNavigationsGET.json
        List<AnotherEntity> result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .read()
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void testGetNavigationItem() throws ExecutionException, InterruptedException {
        //getNavigationItemGET.json
        AnotherEntity result = null;
        try{
            result = client.getMe()
                    .getNavigation("SomeId")
                    .read()
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.getSomeString(), is(equalToIgnoringCase(getAnotherEntity().getSomeString())));
    }

    @Test
    public void testGetNavigationItemWithSelect() throws ExecutionException, InterruptedException {
        //getNavigationItemWithSelectGET.json
        AnotherEntity result = null;
        try{
            result = client.getMe()
                    .getNavigation("SomeId")
                    .select("SomeProp, AnotherProp")
                    .read()
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.getSomeString(), is(equalToIgnoringCase(getAnotherEntity().getSomeString())));
    }

    @Test
    public void testGetNavigationItemRawWithSelect() throws ExecutionException, InterruptedException {
        //getNavigationItemWithSelectGET.json
        String result = null;
        try{
            result = client.getMe()
                    .getNavigation("SomeId")
                    .select("SomeProp, AnotherProp")
                    .readRaw()
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        String expectedResponseString = "{\"SomeString\":\"Some String\",\"Id\":\"3281EC0B-1AEB-49A4-A345-E64D732DA6D3\",\"@odata.type\":\"#Microsoft.SampleService.AnotherEntity\"}";
        assertThat(result, is(notNullValue()));
        assertThat(result, is(equalTo(expectedResponseString)));
    }

    @Test
    public void testGetNavigationListWithSelectAndTop() throws ExecutionException, InterruptedException {
        //getNavigationsWithSelectAndTopGET.json
        List<AnotherEntity> result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .select("SomeProp, AnotherProp")
                    .top(1)
                    .read()
                    .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void testGetNavigationListRawWithSelectAndTop() throws ExecutionException, InterruptedException {
        //getNavigationsWithSelectAndTopGET.json
        String result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .select("SomeProp, AnotherProp")
                    .top(1)
                    .readRaw()
                    .get();
        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        String responseContent = "{\"value\" : [{\"SomeString\":\"Some String\",\"Id\":\"3281EC0B-1AEB-49A4-A345-E64D732DA6D3\",\"@odata.type\":\"#Microsoft.SampleService.AnotherEntity\"}]}";
        assertThat(result, is(notNullValue()));
        assertThat(result, is(equalTo(responseContent)));
    }

    @Test
    public void testGetCollectionWithFilterAndExpand() throws ExecutionException, InterruptedException {
        //getCollectionsWithExpandAndFilter.json
        List<SampleEntity> result = null;

        try {
            result = client.getservices()
                    .expand("SomeProp")
                    .filter("SomeProp eq 'SomeString'")
                    .read()
                    .get();
        } catch (Throwable t) {
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void testGetCollectionWithHeaders() throws ExecutionException, InterruptedException {
        //getCollectionsWithHeaders.json
        List<SampleEntity> result = null;

        try {
            result = client.getservices()
                    .addHeader("Header1", "Value1")
                    .addHeader("Header2", "Value2")
                    .read()
                    .get();
        } catch (Throwable t) {
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void testDefaultHeaders() throws ExecutionException, InterruptedException {
        //testDefaultHeaders.json
        List<SampleEntity> result = null;

        try {
            result = client.getservices()
                    .top(99)
                    .read()
                    .get();
        } catch (Throwable t) {
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void testDeleteNavigationItem() throws ExecutionException, InterruptedException {
        //deleteNavigationItemDELETE.json
        Object result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .getById("SomeId")
                    .delete()
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(nullValue()));
    }

    @Test
    public void testAddNavigationItem() throws ExecutionException, InterruptedException {
        //addNavigationItemPOST.json
        AnotherEntity result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .add(getAnotherEntity())
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
    }

    @Test
    public void testUpdateNavigationItem() throws ExecutionException, InterruptedException {
        //updateNavigationItemPOST.json
        AnotherEntity existingEntity = getAnotherEntity();
        AnotherEntity result = null;
        try{
            result = client.getMe()
                    .getNavigations()
                    .getById(existingEntity.getId())
                    .addHeader("IsPatch", "yes")
                    .update(existingEntity)
                    .get();

        }catch(Throwable t){
            resolver.getLogger().log(t.getLocalizedMessage(), LogLevel.ERROR);
        }

        assertThat(result, is(notNullValue()));
    }

    private SampleEntity getSampleEntity(){
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setDisplayName("Some Display Name");
        sampleEntity.setentityKey("Some Entity Key");
        sampleEntity.setId("5C338D75-CB90-4785-8667-CED25B3695BF");

        return sampleEntity;
    }

    private AnotherEntity getAnotherEntity(){
        AnotherEntity anotherEntity = new AnotherEntity();
        anotherEntity.setId("3281EC0B-1AEB-49A4-A345-E64D732DA6D3");
        anotherEntity.setSomeString("Some String");

        return anotherEntity;
    }

}
