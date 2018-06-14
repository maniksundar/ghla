# Android app for Ghla (Ghana Library Authority) - Initial Implementation

This app contains the implementation of the Login, Home Page, Profile Page and the Reports Page(Membership Metric alone completed as of June 14, 2018). There is no server component as part of this codebase.

## Getting Started

Before looking into the app, it's important to understand the KPI metrics, different Users (Manager, Regional Manager and Head of Libraries), different question types (Title, Subtitle and Question). The KPI metrics and question types are defined in the Excel Sheet (not shared here) and the different User roles are defined in the initial ramp up document. Contact the project manager for the most recent information and for the Excel sheet.

### Prerequisites

* Development can be done in Windows, Linux or Mac OS.
* You need Android Studio 3.0 or greater to develop the app. 
* Git needs to be installed beforehand. 

### Installing

Clone the code on your local machine using the code below and open with Android Studio. The app can be launched using an Emulator

```
git clone https://github.com/maniksundar/ghla .
```

## Workflow (Non Technical Overview)

### User 1: Manager
When a Manager logs into the app, they see a list of different metrics that need reporting (Membership, Resources for instance.). The user chooses one of the metrics and starts filling in the values for the different fields in that metric.

For instance Membership metric requires the following questions to be answered

* Total number of Male users this week
* Total number of Female users this week

Once the user fills in that information and submits the report, it is passed on to the Regional Manager over the network.

### User 2: Regional Manager

When a Regional Manager logs into the app, they see a list of reports submitted by the Managers. They can then go over the reports and Approve them or Request for change.

### User 3: Head of libraries

The Head of the libraries gets an birds eye view of all the regions and libraries within the regions. They get a cumulative status of all the reports in all the regions, and they can filter by region or libraries.

Reports can be in the following states - NotStarted, InProgress, Submitted, InReview, Approved.

## Data architecture and Components

### Hybrid Approach

The key part of the data architecture is the KPI metrics. The KPI metrics which were originally in the Excel sheet has been translated to JSON format. JSON is chosen as the data format because it is human readable  + machine readable and light weight. The app uses a hybrid approach for the KPI metrics. It keeps a copy of the metric locally and also in the server. Since the app needs to be fast and light weight, and the KPI metrics don't change frequently, the KPI metrics are cached in the app. The cached version is used when there is no internet. The app pings the server and makes sure that the locally cached KPI metric is up to date with the server copy whenever it has access to the internet. Inorder to edit the metrics easily, the server keeps a copy of the KPI metrics as well. For instance if a typo is found in a single metric in all the apps, then the server KPI metric can be edited to force a refresh on all the clients. 

The code is flexible enough such that when a metric is edited in the server/client, the Android UI components dynamically add EditText views accordingly based on the JSON file. The process is explained in the *Editing the JSON* section.

### Editing the JSON

* Before editing

```
       { "text": "JUVENILE",
          "questions":[
            {"text": "Male","answerable":true},
            {"text": "Female","answerable":true}
          ]
       }
```
The column above with the text JUVENILE is not editable. Let's assume that we want to make the users enter value next to the JUVENILE column. All we have to do is add "answerable":"true" right next to it, as shown below.

* After editing

```
      { "text": "JUVENILE", "answerable":"true"
          "questions":[
            {"text": "Male","answerable":true},
            {"text": "Female","answerable":true}
          ]
      }
```

No other code changes are needed within the app. The app is flexible enough to render without any compilation errors. If you encounter any errors here, make sure that there are no errors in the JSON and also make sure that JSON keys comply the data model types defined in the app. Gson is used to serialize and deserialize the JSON 

### Classes and Interfaces

Important classes and their functions are listed here.

All the important data models are implemented in a single java file [Report.java]((../../blob/master/app/src/main/java/com/ghla/library/authority/Report.java)) to prevent file bloat in the application. If seen fit, these classes can be moved into a separately file.

[Title, Subtitle and Question](../../blob/master/app/src/main/java/com/ghla/library/authority/Report.java). These are the main objects that are deserialized from the JSON. All these classes implement the Answerable interface to wire the data up with the UI components.

[JSONHelper](../../blob/master/app/src/main/java/com/ghla/library/authority/JSONHelper.java) provides the necessary abstraction for  taking care of serializing and deserializing with the Gson library. If Gson were to be replaced by another library, then code changes can be limited only to this file.

[Membership.java](../../blob/master/app/src/main/java/com/ghla/library/authority/Membership.java) is the class that Gson uses to deserialize the JSON.

[Interfaces - Answerable](../../blob/master/app/src/main/java/com/ghla/library/authority/Report.java) (Important for the UI components to work seamlessly)

The different question types (Title, subtitle and Question) implement the Answerable interface. If a new question type were to be added in the future, it's important that the new question type implements the Answerable interface for the UI components to work seamlessly.

Fragments - The app primarily uses one activity many fragments philosophy for reusability. Interaction between fragments can be done efficiently using the EventBus library. 

## Communication within the app

EventBus is used to communicate between different models / activities and fragments that are not connected with each other. Events can be sent by creating an instance of the Events class.

The following code sends an event that the Membership report can be submitted.

```
EventBus.getDefault()
.post(new Events(mReport, App.getContext().getString(R.string.submitMembershipReport)));
```

## Data Model

Singleton instance responsible for saving and retrieving data to the disk. This class is mainly responsible for code separation between the app and the Network + Disk components. The Network and Disk components are not accessed directly from the app. This class provides the necessary abstraction between the activities, fragments and the Network and Disk layer. 

```
DataModel.getInstance().submitMembershipReport(event.report);

```

### Network

Singleton instance responsible for saving and retrieving data to and from the disk. Currently, [OkHttpClient library](https://github.com/square/okhttp) is used under the hood to make the REST calls to the server.

Sample code to post JSON to the server.

```
Network.getInstance().postJSON(String json);

```

### Disk

Singleton instance responsible for saving and retrieving data to the disk. It uses SharedPreferences under the hood to store the data to the memory.

Sample code to save a JSON string to a filename <file>
  
```
Disk disk = Disk.getInstance().saveReportAs(String json, String file);
```

## Interaction with the Server 

The app can communicate with the server by making REST calls and consuming and producing JSON as the data format. Most of the interaction is inside the Network abstraction class. The server component is not part of this codebase.

## Built With

The following third party libraries are used in the app.

* [okHttpClient](https://github.com/square/okhttp)
* [EventBus](https://github.com/greenrobot/EventBus)
* [Gson](https://github.com/google/gson)

## Versioning

Git is used as the version control system

## Authors

* *Initial work* - [ **Sundar Venkatesh**](https://github.com/maniksundar)

## License

This project is licensed under the MIT License.

## Acknowledgments

* This project is part of the online volunteer project to United Nations @ https://www.onlinevolunteering.org/en
