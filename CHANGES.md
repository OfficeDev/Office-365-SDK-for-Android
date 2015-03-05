# Office 365 SDK for Android  #

 ### Version 0.12.0 ### 

* Fixing classpath for javadoc generation
* SDK and test app updated with vipr
* Merge PR #67
* Support for Streamed media entities

 ### Version 0.11.0 ###

*  Cleaned imports
*  Improved tests for sample service comparing request's payloads too
*  Updated tests for Sample Service
*  adding travis script into integration branch
*  adding travis scrip into dev
*  Added new tests for sample service
*  adding verbose log level to sample app
*  Added tests for sample service
*  adding DocLibClient
*  Added integration tests for mocked service
*  updating test app to gradle gradle 2.1
*  upgrading test project
*  adding source compatibility 1.6 issue #55
*  updating gradle files to android 1.0 release


### Version 0.10.0 ###

*	Support for deserialization of entity subclasses	a650fb1
*	Initial test structure for outlook services	d51911b
* Added tests for deserializing lists and single items in the correct d… …	f445dea
*	Updated CalendarView tests, added getCalendarView for particular cale… …	6de06e3
* Adding new ADAL based dependency resolver	1530d5e
* Updating sample with new ADAL based dependency resolver	54ad1f0
* OrderBy support	bb34fc7
* Added tests for skip and order by	08ed48e
* Version bump	d9637b6
*	Excluding android support from resulting .jar from .aar


### Version 0.9.4###

* Library consolidation into a new package : odata-engine-core
* projection and expansion support for single entities ($expand and $select)
* Fixed issue with custom headers that were not being added properly
* Updated ADAL version to use (,2.0)
* Removed CredentialsFactory interface
* Added tests for CalendarView functions

### Version 0.9.3###

* Excluding third party dependencies when creating .jar artifact. issue #45
* Updates in End2End authentication. Using refreshtoken
* moving fetcher logic to ctor as stated per issue #18. Changes applied to templates
* Fixing several Code Inspection warnings for issue #13
* Adding better sintaxis to retrieve items from collection without using getByIdXXX
* Added select and filter tests for Lists
* Removed unsupported filter tests for files
* Added filter, select and top tests in Discovery, Exchange and Files
* Adding suppress warnings to odatacollectionfetcher
* Adding constraints to generic parameters. issue #21
* Adding constraint to generic parameters
* Updated End2End App
* Updating outloook services with latest changes in code generator
* Adding bintray plugin into e2e test app
* Updated End2End. Added general_settings.xml Started adding Tests for Directory & Discovery
* Rolling back to gradle wrapper version 1.12 for sample app
* Add version headers for issue #36
* Add task to create .jar from .aar
* Minor changes and clean-ups to gradle scripts

### Version 0.9.2 ###
* Small fixes and minor improvements
