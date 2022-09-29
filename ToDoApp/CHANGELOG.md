# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


## [0.4.0] - 2022-09-29

### Added
* setup package structures for Unit Test and Instrumented Test

* implemented FakeToDoRepository for Unit Tests
* test cases for all Validation Use Cases
* test cases for all Common Use Cases
* test cases for all ToDo_Task Use Cases

* setup HiltTestRunner and TestAppModule in Instrumented Test package for proper testing with Dagger Hilt library
* test cases for ToDoDao (testing special Test Instance of Room DB)

## [0.3.1] - 2022-06-24

### Added
* extension function for Modifier that enables triggering onClick lambda without ripple effect

### Changed
* PriorityDropDown and TypeDropDown have now enabled = false to allow triggering custom onClick lambda with usage of added extension function noRippleClickable
* PriorityDropDown and TypeDropDown have adjusted color scheme (to appear same in disabled mode)

## [0.3.0] - 2022-06-23

### Added
* splashScreen which is app entry point, after some delay (3s) automatically navigates to main screen (ToDoListScreen)
* use_case package with UseCase classes organized in separate packages for each screen
* Dependency Injections for new use cases and modules that provide them in ViewModelScope (UseCaseModule with ToDoListModule and ToDoTaskModule)

### Changed
* Moved repository related functionalities to separate UseCase classes to properly follow Clean Architecture principles
* Reorganized Validation classes to ../use_case/validation package to keep consistent project structure
* Some constant values changes and teaks

## [0.2.2] - 2022-06-22

### Added
* Swipe to dismiss functionality in ToDoListItem (TodoListEvent and ToDoListViewModel adjusted accordingly) which deletes ToDoTask from the list when swiped from right to left
* Validation package in domain packages with Validation use cases and Top Validations class that encapsulates all validation use case classes
* Validation module for Dependency Injects that provides all Validation use case classes 

### Changed
* Validation as in clean architecture standards (use cases, ValidationResult class)
* Adjusted DI for new validation features
* Adjsuted TextInputFields to have isError parameter set (for more clear visual representation of validation errors)

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
