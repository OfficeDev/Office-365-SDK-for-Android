package com.microsoft.office365.test.integration.framework;

public class TestLog {
    String mName;
    Exception mException;
    TestStatus mTestStatus;

    public TestLog( String name, TestStatus status){
        mName = name;
        mTestStatus = status;
    }

    public void setName(String name){
        mName = name;
    }

    public String getName(){
        return mName;
    }

    public void setException(Exception ex){
        mException = ex;
    }

    public Exception getException(){
        return mException;
    }

    public void setTestStatus(TestStatus testStatus){
        mTestStatus = testStatus;
    }

    public TestStatus getTestStatus(){
        return mTestStatus;
    }
}
