# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


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
