#Office 365 Android SDK

*Readme in progress - After samples section is not up to date*

**Table of Contents**

- [Overview](#overview)
- [Build instructions](#build-instructions)
- [Starting your app from scratch](#starting-your-app-from-scratch)
- [Office 365 Samples](#office-365-samples)
    - [Simple Office 365 Sample](#simple-office-365-sample)
        - [Using Maven archetypes](#using-maven-archetypes)
        - [Using Eclipse](#using-eclipse)
- [Features](#features)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Overview ##

With the Office 365 SDK for Android Preview, it’s now possible to use data stored in Microsoft Office 365 from your Android Apps. This means, you can access SharePoint lists, folders or Exchange calendar, contacts and emails from within your Android-based applications. 

[Microsoft Open Technologies, Inc. (MS Open Tech)](http://msopentech.com) has built the **Office 365 SDK for Android Preview**, an open source project that strives to help Android developers access Office 365 data from their apps.

This SDK provides access to: Microsoft SharePoint Lists, Microsoft SharePoint Files, Microsoft Exchange Calendar, Microsoft Exchange Contacts, Microsoft Exchange Mail.

##Build instructions

Office 365 Android SDK uses [Gradle](http://www.gradle.org/) to manage dependencies.
In order to open the sdk you just need to **import** the code with Android Studio and build the code to start downloading all the dependencies that are needed.


```
git clone https://github.com/MSOpenTech/O365-Android.git
```

>Note: Make sure you **import** the code into Android Studio.


##Starting your app from scratch
This section will guide you through the process of creating a very simple application that retrieves messages using the Office 365 Android SDK and Azure Active Directory for Android SDK.

1 - Create a new Android application using Android Studio. In the build.gradle file add the following dependencies.

>**IMPORTANT**: Currently the skd is not available as gradle dependency. In order to use it, just import the modules within your sample.

```
repositories {
        mavenCentral()
    }
    

dependencies {
    compile('[INSERT-SDK-MAVEN-ID-HERE]')
    compile('com.microsoft.aad:adal:1.0.0')
}
```

>Note: If you get an error with android support library, you might need to exclude it from ADAL. To do that, replace ADAL dependency with the following:
>
>```
compile('com.microsoft.aad:adal:1.0.0') {
        exclude group: 'com.android.support'
    }
>```

2. Add the necesary code to get the authentication token using ADAL library. To do that, follow the steps described in the ADAL documentation (How to use this library section).

```
https://github.com/AzureAD/azure-activedirectory-library-for-android
```

3. Once you get the token, you will need to create an implementation of Credentials interface to handle authentication in the Office 365 SDK.

```
public class OAuthCredentials implements Credentials {

    String mToken;

    public OAuthCredentials(String token){
        mToken = token;
    }

    @Override
    public void prepareRequest(Request request) {
        request.addHeader("Authorization","Bearer " + mToken);
    }
}
```

4. Now, add the following code to retrieve messages. Make sure you're using the right endpoint and you have replaced the authentication token.

```
String endpoint = "https://sdfpilot.outlook.com/ews/odata";
String token = "[YOUR-TOKEN-HERE]";
                DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver();

                CredentialsFactoryImpl credentialsFactory = new CredentialsFactoryImpl();

                OAuthCredentials oauthCredentials = new OAuthCredentials(token);
                credentialsFactory.setCredentials(oauthCredentials);

                dependencyResolver.setCredentialsFactory(credentialsFactory);
                EntityContainerClient client = new EntityContainerClient(endpoint, dependencyResolver);
                ListenableFuture<List<Message>> messagesFuture = client.getMe().getFolders().getById("Inbox").getMessages().execute();

                Futures.addCallback(messagesFuture, new FutureCallback<List<Message>>() {
                    @Override
                    public void onSuccess(final List<Message> result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Items: " + Integer.valueOf(result.size()).toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StringWriter writer = new StringWriter();
                                t.printStackTrace(new PrintWriter(writer));

                                String trace = writer.toString();

                                Toast.makeText(MainActivity.this,trace,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
```


##Office 365 Samples

We've created a sample to use as starting point to develop android application using all the new set of SDKs Office 365 provides. In order to make it even simpler, we propose two different approaches depending on your needs and preferences.

The first (and simpler) one, uses Android Studio and Maven archetypes to generate the sample and download its dependencies. The other approach, uses eclipse and download the code and dependencies manually.

##Simple Office 365 Sample

The application is a really simple android sample, with all the dependencies and code needed to obtain an authentication token using ADAL libraries.

It is composed by an activity where to display the obtained token and a view to set some information needed to authenticate (client id, return url, and resource url)

###Using Maven archetypes

We've created an archetype that creates a new android application with ADAL as dependency. In order to use it, you will need to have installed and configured Maven and Android Studio.

####Prerequisites

- [Git](http://git-scm.com/)
- [Android Studio](https://developer.android.com/sdk/installing/studio.html) 
- [Maven](http://maven.apache.org/) - This sample was tested with Maven v3.2.3 

1- Install the archetype in your local repository. To do this, clone the archetype's git repository: 

```
git clone https://github.com/MSOpenTech/O365-Android.git
```

2- Open a console in the samples/android-quickstart folder within the downloaded repository and run the maven command to install the archetype
	
```
mvn clean install
```

>Note: If asked for archetype version, use 0.1.0 as this is the current version.

3- Now that the archetype is installed, generate the sample by running the following command:

```
mvn archetype:generate -DarchetypeArtifactId=office365-quickstart -DarchetypeGroupId=com.microsoft.office365 ^
-DarchetypeVersion=0.1.0 -DgroupId=[YOUR-GROUP-ID] ^
-DartifactId=[YOUR-ARTIFACT-ID]
```

4- In Android Studio, open the generated code and start the application.

5- Open preferences view and set your client id, the redirect url and the resource url (for more information about this, go to Azure Active Directory Library for Android [site](https://github.com/AzureAD/azure-activedirectory-library-for-android)). 

6- Click get token. A login view will appear, enter your credentials. 

7- Your token will appear in the screen.

Now you are able to start using any of the Office 365 SDKs to create your own projects.

###Using Eclipse

####Prerequisites

- [Git](http://git-scm.com/)
- [Eclipse](https://www.eclipse.org/home/index.php)
- [ADT Plugin](http://developer.android.com/tools/sdk/eclipse-adt.html)

In order to start the sample an application using eclipse, you will first need to clone Office 365 and ADAL repositories.

1- Go to Microsoft Azure Active Directory Authentication Library (ADAL) [site](https://github.com/AzureAD/azure-activedirectory-library-for-android) and follow the instructions to download and configure the library.

```
git clone https://github.com/AzureAD/azure-activedirectory-library-for-android.git
```

2- Clone Office 365 SDK repository

```
git clone https://github.com/MSOpenTech/O365-Android.git
```

3- In Samples folder, open	 hello-android sample using eclipse.

4- Import the ADAL project you downloaded in the previous steps.

5- Update the sample to use ADAL by adding a reference to ADAL project from Properties | Android | Library.

>Note: You might have compiling errors due to the android support library's version shipped with ADAL. Please make sure you're using the same version in both projects.

## Features ##
For the entire list of methods available in the SDK, please refer to the java docs under each SDK in the SDK folder.

## Tests ##

Apart from the sample apps, we also have end to end tests that demonstrate the use of the SDK. Please look at the tests folder under the root of the SDK.

##Contributing##

You will need to sign a [Contributor License Agreement](https://cla.msopentech.com/) before submitting your pull request. To complete the Contributor License Agreement (CLA), you will need to submit a request via the form and then electronically sign the Contributor License Agreement when you receive the email containing the link to the document. This needs to only be done once for any Microsoft Open Technologies OSS project.

## License ##
Copyright (c) Microsoft Open Technologies, Inc. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
