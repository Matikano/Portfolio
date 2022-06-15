# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


## [0.1.0] - 2022-06-15
### Added
* Ui component for ToDoTaskListItem
* Business Logic for ToDoAppBar actions (sorting, deleting all tasks and triggering SearchAppBar)
* ListSearchAppBar with implemented logic for searching (filtering) tasks in the list
* DisplayAlertDialog component for creating custom confirmation AlertDialogs across the App
* LazyColumn with ToDoListItems in ToDoListScreen which displays ToDoTasks
* OnClickEvent for ToDoListItem that navigates to ToDoTaskScreen (passing ToDoTask parcelable object)
* Time Format constants


### Changed
* Extended ToDoListEvent and ToDoListState classes with proper functions and values used for executing business logic (ListAppBar actions)
* Adjusted some constants in Diemnsions file
* Adjusted Priority Colors to fit better into app Theme
* Adjusted onEvent function in ToDoListViewModel to properly react on triggered events


## [0.0.2] - 2022-06-10
### Added
* Set up Room Database
* Dao Interface
* Repository Interface and Repositroy Implementation
* Configured Dependency Injection (AppModule, RepositoryModule)
* Implemented DefaultAppBar for ToDoListScreen with actions (Search, Filter, Morew with delete all) - Only UI components were impleneted (no business logic yet)
* ToDoListViewModel for ToDoListScreen (with Event and State classes)


## [0.0.1] - 2022-06-09
### Added 
* Set up dependencies:
* Room 
* DaggerHilt
* Compose Directions
