# Android app for Ghla (Ghana Library Authority)

This app contains the implementation of the Login, Home Page, Profile Page and the Reports Page(Membership Metric alone completed as of June 14, 2018). There is no server component and all the screens work with Dummy data.

## Getting Started

Before looking into the app, it's important to understand the KPI metrics, different User roles, different data models (Title, Subtitle and Question)

### Prerequisites

You need Android Studio 3.0 or greater to develop the app.

### Installing

Clone the code on your local machine using the code below and open with Android Studio. The app can be launched using an Emulator

```
git clone https://github.com/maniksundar/ghla .
```

## Communication

EventBus is used to communicate between different models that are not connected with each other.

## Data Model

Singleton instance responsible for saving and retrieving data to the disk.

```
DataModel.getInstance().submitMembershipReport(event.report);

```


### Network

Singleton instance responsible for saving and retrieving data to the disk.

```
Disk disk = Disk.getInstance().saveReportAs(String file, String json);

```

### Disk

Singleton instance responsible for saving and retrieving data to the disk. It uses SharedPreferences under the hood to store the data to the memory.

```
Disk disk = Disk.getInstance().saveReportAs(String json, String file);
```

## Versioning

Git is used as the version control system

## Authors

* **Sundar Venkatesh** - *Initial work* - [maniksundar](https://github.com/maniksundar)

## License

This project is licensed under the MIT License.

## Acknowledgments

* This project is part of the online volunteer project to United Nations @ https://www.onlinevolunteering.org/en
