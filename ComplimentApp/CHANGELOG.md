# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.2] - 2022-10-28

### Added
* UseCases for `ComplimentScreen`


## [0.2.1] - 2022-10-20

### Added
* BaseDataStore abstract class
* NotificationSettingsDataStore class to handle retrieving and saving settings values into dataStore

### Changed
* SharedPreferences to DataStore implementation of internal storage of user preferences

### Deleted
* NotificationPreferences classes


## [0.2.0] - 2022-10-17

### Added
* HiltBroadcastReceiver abstract class for creating implementations of BroadcastReceiver class that can have injected dependencies
* NotificationAlarmReceiver class that handles broadcast sent by AlarmManager - displays notifications with daily compliments

### Changed
* Code cleanup

### Deleted
* WorkManager classes and implementation of periodic task execution since it is nor reliable and does not work periodically when device is in Doze mode and Power Safe mode

## [0.1.1] - 2022-10-17

### Added
* ComplimentEvent sealed class for handling different types of events in ComplimentScreen

### Changed
* Bug fixed double change of Compliment background while executing onRefresh function in SwipeRefresh composable
* Code cleanup


## [0.1.0] - 2022-10-17

### Added
* Project initialization
* Set up Dagger Hilt DI
* Set up Retrofit Network Connection
* Set up WorkManager and NotificationService classes for handling daily fetching API and displaying its response in notifications
* Special implementation of Factory classes for DI of ComplimentNotificationWorker objects
* Presentation layer of app (ComplimentScreen, ComplimentViewModel, ComplimentState)
* SwipeToRefresh functionality for ComplimentScreen (re-fetching new compliment)
