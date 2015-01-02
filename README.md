#Office 365 SDK for Android

[![Build Status](https://travis-ci.org/OfficeDev/Office-365-SDK-for-Android.svg?branch=master)](https://travis-ci.org/OfficeDev/Office-365-SDK-for-Android)

**Table of Contents**

- [Overview](#overview)
- [Quick Start](#quick-start)
- [Samples](#samples)
- [FAQ](#faq)
- [Contributing](#contributing)
- [License](#license)

## Overview
With this SDK from [MS Open Tech](https://msopentech.com) you can access all of Office 365's services and stored data from Android apps.

This SDK provides access to:
  * SharePoint Online Files and Lists
  * Exchange Online Mail, Calendars, and Contacts
  * Azure AD Directory (Graph)

The Office 365 SDK for Android takes advantage of [Android Studio](https://developer.android.com/sdk/index.html)'s support for [Gradle](http://www.gradle.org/) to manage dependencies. To use the SDK in your projects, just add the libraries to your list of dependencies in gradle.build. See the [wiki page on Gradle and dependencies](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Include-Dependencies-using-Gradle) for more info.

> **IMPORTANT** Before opening issues, please make sure you're on the latest version of Android Studio. Currently that is v1.0!

## Quick start
Now we'll create a simple application that retrieves messages using this SDK and the [Azure AD Authentication Library (ADAL)](https://github.com/AzureAD/azure-activedirectory-library-for-android).

1. Create a new Android application in Android Studio.
2. Locate app/build.gradle in your app module and add the following dependencies. In this walkthrough we'll only use "outlook-services", they're all listed here for reference.
	```Groovy
	dependencies {
	    // base OData stuff:
	    compile group: 'com.microsoft.services', name: 'odata-engine-core', version: '0.11.1'
	    compile group: 'com.microsoft.services', name: 'odata-engine-android-impl', version: '0.11.1', ext:'aar'
	
	    // choose the services/SDKs you need:
	    compile group: 'com.microsoft.services', name: 'outlook-services', version: '0.11.1'
	    compile group: 'com.microsoft.services', name: 'discovery-services', version: '0.11.1'
	    compile group: 'com.microsoft.services', name: 'directory-services', version: '0.11.1'
	    compile group: 'com.microsoft.services', name: 'file-services', version: '0.11.1'
	    compile group: 'com.microsoft.services', name: 'sharepoint-services', version: '0.11.1', ext:'aar'
	    
	    // ADAL
	    compile (group: 'com.microsoft.aad', name: 'adal', version: '1.0.5') {
	       // exclude group: 'com.android.support'   // this may not be necessary
	    }
	}
	
	```
  > NOTE: To avoid an error related to the Android support library, you might need to ensure it isn't being added redundantly by ADAL. That's why we've added the `exclude` call above.

3. Follow the instructions on the [README for ADAL](https://github.com/AzureAD/azure-activedirectory-library-for-android) to handle authentication. Alternatively, use [AuthenticationController.java](https://github.com/OfficeDev/Office-365-SDK-for-Android/blob/dev/samples/outlook/app/src/main/java/com/microsoft/services/controllers/AuthenticationController.java) from the samples. AuthenticationController.java uses ADALDependencyResolver to handle cached access and refresh tokens automatically.

4. Set up one of the two available dependency resolvers:

  - DefaultDependencyResolver - Add the access token to the resolver. You are responsible for knowing when the token expires and replacing it.

	```Java
	String accessToken = "base64 JWT from ADAL";
	DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver(accessToken);
	```

  - ADALDependencyResolver - Add the initialized ADAL AuthenticationContext to the resolver. The dependency resolver will handle caching of access and refresh tokens and use the refresh token when necessary. You are responsible for initiating an interactive logon if the refresh token also expires.

	```Java
	AuthenticationContext authContext = new AuthenticationContext(activity, authorityUrl, false /* verifyAuthority */ );
	ADALDependencyResolver dependencyResolver = new ADALDependencyResolver(authContext, resourceId, clientId);
	```

5. Now create the API client and use code like the following to retrieve all messages. Here we're talking to Outlook Services, so we use that URL.

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
A simple sample in the Samples folder uses Outlook Services to retrieve mail and events. Look for more samples soon.

## FAQ

* [How to use CalendarView](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Using-Calendar-View)
* [How to use the SDK with Eclipse](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Eclipse-build-instructions)
* [How to handle ETags and optimistic concurrency](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/ETags-and-Optimistic-Concurrency)
* [Known Issues](https://github.com/OfficeDev/Office-365-SDK-for-Android/wiki/Known-Issues)

## Contributing
You will need to sign a [Contributor License Agreement](https://cla.msopentech.com/) before submitting your pull request. To complete the Contributor License Agreement (CLA), you will need to submit a request via the form and then electronically sign the Contributor License Agreement when you receive the email containing the link to the document. This needs to only be done once for any Microsoft Open Technologies OSS project.

## License
Copyright (c) Microsoft Open Technologies, Inc. All rights reserved. Licensed under the Apache License, Version 2.0.
