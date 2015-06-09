# Office 365 SDKs for Android

Easily integrate services and data from Office 365 into native Android apps using these Android/Java libraries.

[![Build Status](https://travis-ci.org/OfficeDev/Office-365-SDK-for-Android.svg?branch=master)](https://travis-ci.org/OfficeDev/Office-365-SDK-for-Android)
[![Download](https://api.bintray.com/packages/msopentech/Maven/Office-365-SDK-for-Android/images/download.svg)](https://bintray.com/msopentech/Maven/Office-365-SDK-for-Android/_latestVersion)

---

:exclamation:**NOTE**: You are free to use this code and library according to the terms of its included [LICENSE](/LICENSE) and to open issues in this repo for unofficial support.

Information about official Microsoft support is available [here][support-placeholder].

[support-placeholder]: https://support.microsoft.com/

---

These libraries are generated from API metadata using [Vipr] and [Vipr-T4TemplateWriter] and use a shared client stack provided by [orc-for-android].

For information on release cadence and how to access built binaries before release, see [Releases](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Releases).

Current services, service versions, and SDK versions:

|API|Service Version|SDK Version|Artifact Id|
|---|---------------|-----------|-----------|
|Mail/Calendar/Contacts|1.0|0.14.1|outlook-services|
|Files|1.0|0.14.1|file-services|
|Discovery|1.0|0.14.1|discovery-services|
|AAD Graph|1.5|0.14.1|directory-services|
|OneNote|1.0|0.14.1|onenote-services|
|Unified API|beta|0.2.0|graph-services|
|SharePoint Lists|1.0|0.14.1|sharepoint-services|

[Vipr]: https://github.com/microsoft/vipr
[Vipr-T4TemplateWriter]: https://github.com/msopentech/vipr-t4templatewriter
[orc-for-android]: https://github.com/msopentech/orc-for-android

## Quick Start

To use these libraries in your project, follow these general steps, as described further below:

1. Configure dependencies in build.gradle.
2. Set up authentication.
3. Construct an API client.
4. Call methods to make REST calls and receive results.

### Setup

1. From the Android Studio splash screen, click "Start a new Android Studio project". Name your application as you wish; we'll assume the name *O365QuickStart* here.

2. Select "Phone and Tablet" and set Minimum SDK as API 18, then click Next. Choose "Blank Activity", then click Next. The defaults are fine for the activity name, so click Finish.

3. Open the Project view in the left-hand column if it's not open. From the list of Gradle Scripts, find the one title "build.gradle (Module: app)" and double-click to open it.

4. In the `dependencies` closure, add the following dependencies to the `compile` configuration:

    ```groovy
    compile 'com.microsoft.services:outlook-services:0.14.1'
    compile 'com.microsoft.orc:orc-engine-android:0.14.1@aar'
    compile 'com.microsoft.aad:adal:1.1.3@aar'
    ```

    > **NOTE**: All three dependencies must be explicitly specified, because there are alternate ORC implementations (e.g. for JVM) and alternate authentication libraries (e.g. MSA) which can be used.

    You may want to click the "Sync Project with Gradle Files" button in the toolbar. This will download the dependencies so Android Studio can assist in coding with them.

5. Find AndroidManifest.xml and add the following line within the manifest section:

     ```xml
     <uses-permission android:name="android.permission.INTERNET" />
     ```

### Authenticate and construct client
With your project prepared, the next step is to initialize the dependency manager and an API client.

:exclamation: If you haven't yet registered your app in Azure AD, you'll need to do so before completing this step by following [these instructions][MSDN Add Common Consent].

[MSDN Add Common Consent]: https://msdn.microsoft.com/en-us/office/office365/howto/add-common-consent-manually

1. From the Project view in Android Studio, find app/src/main/res/values, right-click it, and choose *New* > *Values resource file*. Name your file adal_settings.

2. Fill in the file with values from your app registration, as in the following example. **Be sure to paste in your app registration values for the Client ID and Redirect URL.**

    ```xml
    <string name="AADAuthority">https://login.microsoftonline.com/common</string>
    <string name="AADResourceId">https://outlook.office365.com</string>
    <string name="AADClientId">Paste your Client ID HERE</string>
    <string name="AADRedirectUrl">Paste your Redirect URI HERE</string>
    ```


3. Add an id to the "Hello World" TextView. Open app/src/main/res/layout/activity_main.xml.
4. Add the following id tag to the TextView element for "Hello World".

    ```xml
	android:id="@+id/messages"
    ```

4. Set up the ADALDependencyResolver

    Open the MainActivity class and add the following imports:

    ```java
    import com.google.common.util.concurrent.FutureCallback;
    import com.google.common.util.concurrent.Futures;
    import com.google.common.util.concurrent.SettableFuture;
    import com.microsoft.aad.adal.AuthenticationCallback;
    import com.microsoft.aad.adal.AuthenticationContext;
    import com.microsoft.aad.adal.AuthenticationResult;
    import com.microsoft.aad.adal.PromptBehavior;
    import com.microsoft.outlookservices.Message;
    import com.microsoft.outlookservices.orc.OutlookClient;
    import com.microsoft.services.android.impl.ADALDependencyResolver;
    import static com.microsoft.aad.adal.AuthenticationResult.AuthenticationStatus;
    ```

    Then, add these instance fields to the MainActivity class:

    ```java
    private AuthenticationContext mAuthContext;
    private ADALDependencyResolver mResolver;
    private TextView messagesTextView;
    ```

    Add the following method to the MainActivity class. The logon() method constructs and initializes ADAL's AuthenticationContext, carries out interactive logon, and constructs the ADALDependencyResolver using the ready-to-use AuthenticationContext.

    ```java
    protected SettableFuture<Boolean> logon() {
        final SettableFuture<Boolean> result = SettableFuture.create();

        try {
            mAuthContext = new AuthenticationContext(this, getString(R.string.AADAuthority), true);
            mAuthContext.acquireToken(
                    this,
                    getString(R.string.AADResourceId),
                    getString(R.string.AADClientId),
                    getString(R.string.AADRedirectUrl),
                    PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onSuccess(final AuthenticationResult authenticationResult) {
                            if (authenticationResult != null
                                    && authenticationResult.getStatus() == AuthenticationStatus.Succeeded) {
                                mResolver = new ADALDependencyResolver(
                                        mAuthContext,
                                        getString(R.string.AADResourceId),
                                        getString(R.string.AADClientId));
                                result.set(true);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            result.setException(e);
                        }

                    });
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            result.setException(e);
        }
        return result;
    }
    ```

    You also must configure MainActivity to pass the result of authentication back to the AuthenticationContext by adding this method to its class:

    ```java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAuthContext.onActivityResult(requestCode, resultCode, data);
    }
    ```

    From MainActivity.onCreate, cache the messages TextView, then call logon() and hook up to its completion using the following code:

    ```java
       messagesTextView = (TextView) findViewById(R.id.messages);
       Futures.addCallback(logon(), new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("logon", t.getMessage());
            }
        });
    ```

4. Now fill in the onSuccess method of the FutureCallback to create an API client.

    Add a private static variable with the Outlook base URL:

    ```java
    private static final String outlookBaseUrl = "https://outlook.office365.com/api/v1.0";
    ```

    Add a private instance variable for the client:

    ```java
    private OutlookClient mClient;
    ```

    And finally complete the onSuccess method by constructing a client and using it. We'll define the getMessages() method in the next step.

    ```java
    @Override
    public void onSuccess(Boolean result) {
        mClient = new OutlookClient(outlookBaseUrl, mResolver);
        getMessages();
    }
    ```


5. Create a new method to use the client to get all messages from your inbox.

	```java
    protected void getMessages() {
        Futures.addCallback(
                mClient.getMe()
                        .getFolders()
                        .getById("Inbox")
                        .getMessages()
                        .read(),
                new FutureCallback<List<Message>>() {
                    @Override
                    public void onSuccess(final List<Message> result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messagesTextView.setText("Messages: " + result.size());
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        Log.e("getMessages", t.getMessage());
                    }
                });
    }
	```

If successful, the number of messages in your inbox will be displayed in the TextView. :)

## Samples
- [O365-Android-Connect] - Getting started and authentication <br />
- [O365-Android-Snippets] - API requests and responses

[O365-Android-Connect]: https://github.com/OfficeDev/O365-Android-Connect
[O365-Android-Snippets]: https://github.com/OfficeDev/O365-Android-Snippets

## FAQ

* [How to use CalendarView](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Using-Calendar-View)
* [How to use the SDK with Eclipse](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Eclipse-build-instructions)
* [How to handle ETags and optimistic concurrency](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/ETags-and-Optimistic-Concurrency)
* [Known Issues](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Known-Issues)

## Contributing
You will need to sign a [Contributor License Agreement](https://cla.msopentech.com/) before submitting your pull request. To complete the Contributor License Agreement (CLA), you will need to submit a request via the form and then electronically sign the Contributor License Agreement when you receive the email containing the link to the document. This needs to only be done once for any Microsoft Open Technologies OSS project.

## License
Copyright (c) Microsoft Open Technologies, Inc. All rights reserved. Licensed under the Apache License, Version 2.0.
