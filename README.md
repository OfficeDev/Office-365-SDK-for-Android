#Office 365 SDK for Android

**Table of Contents**

- [Overview](#overview)
- [Quick start](#quick-start)
- [Samples](#samples)
- [FAQs](#faqs)
- [Contributing](#contributing)
- [License](#license)

## Overview
With this SDK from [MS Open Tech](https://msopentech.com) you can access all of Office 365's services and stored data from Android apps.

This SDK provides access to:
  * SharePoint Online Files and Lists
  * Exchange Online Mail, Calendars, and Contacts
  * Azure AD Directory (Graph)

The Office 365 SDK for Android takes advantage of [Android Studio](https://developer.android.com/sdk/installing/studio.html)'s support for [Gradle](http://www.gradle.org/) to manage dependencies. To use the SDK in your projects, just add the libraries to your list of dependencies in gradle.build. See the [wiki page on Gradle and dependencies](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Include-Dependencies-using-Gradle) for more info.

> **IMPORTANT** Before opening issues, please make sure you're on the latest version of Android Studio in the beta channel. Currently that is v0.8.14.

## Quick start
Now we'll create a simple application that retrieves messages using this SDK and the [Azure AD Authentication Library (ADAL)](https://github.com/AzureAD/azure-activedirectory-library-for-android).

1. Create a new Android application in Android Studio.
2. Find the app/build.gradle file and add the following dependencies. Although for this app you'll only need the "outlook-services" artifact, we listed them all for reference.

```Groovy
dependencies {
    // base OData stuff:
    compile group: 'com.microsoft.services', name: 'odata-engine-core', version: '0.10.1'
    compile group: 'com.microsoft.services', name: 'odata-engine-android-impl', version: '0.10.1', ext:'aar'

    // choose the services/SDKs you need:
    compile group: 'com.microsoft.services', name: 'outlook-services', version: '0.10.1'
    compile group: 'com.microsoft.services', name: 'discovery-services', version: '0.10.1'
    compile group: 'com.microsoft.services', name: 'directory-services', version: '0.10.1'
    compile group: 'com.microsoft.services', name: 'file-services', version: '0.10.1'
    compile group: 'com.microsoft.services', name: 'list-services', version: '0.10.1', ext:'aar'
    
    // ADAL
    compile (group: 'com.microsoft.aad', name: 'adal', version: '(,2.0)') {
       // exclude group: 'com.android.support'   // this may not be necessary
    }
}

```

  > NOTE: To avoid an error related to the Android support library, you might need to ensure it isn't being added redundantly by ADAL. That's why we've added the `exclude` call above.
  
2. Follow the instructions on the [README for ADAL](https://github.com/AzureAD/azure-activedirectory-library-for-android) to handle authentication, or use the [Authentication.java](https://github.com/OfficeDev/Office-365-SDK-for-Android/blob/master/samples/simple-exchange-sample/app/src/main/java/com/microsoft/simple_exchange_sample/Authentication.java) implementation in our samples.

3. Once you get the token, add it to the Dependency Resolver so it's added to all future calls.
  > The dependency resolver provides a dependency injection mechanism for working with various HTTP clients, JSON serializers, credential types, and loggers. You'll need to add your OAuth access token to the dependency resolver so that it's used on all API requests.

```Java
String accessToken = "result-from-ADAL";
DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver(accessToken);
```

4. With your dependencies set up, use the following code to retrieve all messages. Here we're talking to Outlook Services, so we use that URL.

```Java
String baseUrl = "https://outlook.office365.com/api/v1.0";
OutlookClient client = new OutlookClient(baseUrl, dependencyResolver);
ListenableFuture<List<Message>> messagesFuture = client
						   .getMe()
						   .getFolders()
						   .getById("Inbox")
						   .getMessages()
						   .read();
Futures.addCallback(messagesFuture, new FutureCallback<List<Message>>() {
	@Override
	public void onSuccess(final List<Message> result) {
	runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
	        Toast.makeText(MainActivity.this,
					"Items: " + Integer.valueOf(result.size()).toString(),
					Toast.LENGTH_LONG).show();
		// do more with the 'result' messages
	    }
	});
	}

	@Override
	public void onFailure(final Throwable t) {
		// handle error
	});
}
});
```

## Samples
Currently we have a simple sample in the Samples folder using Outlook Services to retrieve mail and calendar events. Look for more samples soon.

## FAQs

* [How to use CalendarView?](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Using-Calendar-View)
* [How to build SDK using Eclipse?](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Eclipse-build-instructions)
* [How to handle ETags and Optimistic Concurrency?](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/ETags-and-Optimistic-Concurrency)
* [Known Issues](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Known-Issues)

## Contributing
You will need to sign a [Contributor License Agreement](https://cla.msopentech.com/) before submitting your pull request. To complete the Contributor License Agreement (CLA), you will need to submit a request via the form and then electronically sign the Contributor License Agreement when you receive the email containing the link to the document. This needs to only be done once for any Microsoft Open Technologies OSS project.

## License
Copyright (c) Microsoft Open Technologies, Inc. All rights reserved. Licensed under the Apache License, Version 2.0.
