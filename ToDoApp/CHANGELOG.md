# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


## [0.2.1] - 2022-06-21

### Added
* Live update validation for Title, Description and Date related forms (inside onTextChanged lambdas)


## [0.2.0] - 2022-06-20

### Added
* Added validation functionality in ToDoTaskViewModel
* Added new color value for validation errors
* Added some string constants for validation error messages
* Added ValidationErrorText composable for reusability in any form validation case

### Changed
* Adjusted ToDoTaskState to store validation errorMessages

## [0.1.3] - 2022-06-20
### Changed
* Bugfix for adding new task functionality

## [0.1.2] - 2022-06-16 - 2022-06-20
### Added
* ToDoTaskScreen with all necessary classes - ToDoTaskEvent, ToDoTaskState, ToDoTaskViewModel
  Only TextInputfields validation is missing
* Component composable functions for ToDoTaskScreen: TypeItem, PriorityDropDown, TypeDropDown, ToDoTaskAppBars, ToDoTaskTextField, 
* Extension functions for LocalDate and LocalTime types which returns their String representation in given format (gathered from Constants object)
* String resources for constant values in Composables


### Changed
* Adjusted some constant values for Colors and Diemnsions
* Updated compose version from 1.1.1 to 1.2.0-rc01
* Updated kotlin version from 1.6.10 to 1.6.21
* Updated ksp plugin version from 1.6.10-1.0.2 to 1.6.21-1.0.6
* Updated sdk version from 31 to 32
* Updated Compose Navigatin Destinations library version from 1.5.9-beta to 1.6.12-beta

## [0.1.1] - 2022-06-15
### Added
* UiEvent seald class that holds all relevenat and widely used UiEvents for all screens across the app (events like PopBackStack, ShowSnackBar, Navigate)
* ToDoListViewModel now handles sending UiEvents
* ToDoListViewModel handles new ToDoTaskListEvents


### Changed
* Adjusted ListAppBar function arguments, now instead of taking whole viewModel it takes ToDoListState and onEvent lambda (ToDoTaskListEvent) -> Unit
* Adjusted apperance of Cancel button in DisplayAlertDialog component

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
