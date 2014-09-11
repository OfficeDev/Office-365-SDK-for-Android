#Office 365 Samples

We've created a sample to use as starting point to develop android application using all the new set of SDKs Office 365 provides. In order to make it even simpler, we propose 2 different approaches depending on your needs and preferences.

The first (and simpler) one, uses Android Studio and Maven archetypes to generate the sample and download its dependencies. The other approach, uses eclipse and download the code and dependencies manually.

##The Sample Application

The application is a really simple android sample, with all the dependencies and code needed to obtain an authentication token using ADAL libraries.

It is composed by an activity where to display the obtained token and a view to set some information needed to authenticate (client id, return url, and resource url)

##Using Maven + Android Studio

We've created an archetype that creates a new android application with ADAL as dependency. In order to use it, you will need to have installed and configured Maven and Android Studio.

###Prerequisites

- [Git](http://git-scm.com/)
- [Android Studio](https://developer.android.com/sdk/installing/studio.html) 
- [Maven](http://maven.apache.org/) - This sample was tested with Maven v3.2.3 

1- Install the archetype in your local repository. To do this, clone the archetype's git repository: 

```
git clone https://github.com/LagashSystemsArgentina/MSOpenTech-Office365-SDK-prv.git
```

3- Open a console in the root of the downloaded repository and run the maven command to install the archetype
	
```
mvn clean install
```

>Note: If asked for archetype version, use 0.1.0 as this is the current version.

4- Now that the archetype is installed, generate the sample by running the following command:

```
mvn archetype:generate -DarchetypeArtifactId=office365-quickstart -DarchetypeGroupId=com.microsoft.office365 ^
-DarchetypeVersion=0.1.0 -DgroupId=[YOUR-GROUP-ID] ^
-DartifactId=[YOUR-ARTIFACT-ID]  -B
```

5- In Android Studio, open the generated code and start the application.

6- Open preferences view and set your client id, the redirect url and the resource url (for more information about this, go to Azure Active Directory Library for Android [site](https://github.com/AzureAD/azure-activedirectory-library-for-android)). 

7- Click get token. A login view will appear, enter your credentials. 

8- Your token will appear in the screen.

Now you are able to start using any of the Office 365 SDKs to create your own projects.

##Using Eclipse

###Prerequisites

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
git clone https://github.com/LagashSystemsArgentina/MSOpenTech-Office365-SDK-prv.git
```

3- In Samples folder, open hello-android sample using eclipse.

4- Import the ADAL project you downloaded in the previous steps.

4- Update the sample to use ADAL by adding a reference to ADAL project from Properties | Android | Library.

>Note: You might have compiling errors due to the android support library's version shipped with ADAL. Please make sure you're using the same version in both projects.
