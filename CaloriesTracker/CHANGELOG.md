# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.1] - 2022-11-12

### Changed
* Changed arrangement of `GoalScreen` and `ActivityScreen` `SelectableButtons` to be inside `FlowRow` (overflowing items that do not fit in rows width will be placed below in a new row)
* Moved all Ui Theme specific classes from `app` module to `core-ui` and adjusted imports according to that change
* Changed some Composable components to have their text/content color dependant on SystemColor setting (made them to get color from `MaterialTheme.colors`) 
* Code clean up (not used imports, etc.)


## [1.0.0] - 2022-11-11
### Added
* Implementation of `TrackerOverviewScreen` with all respective classes (`TrackerOverviewState`, `TrackerOverviewEvent`, `TrackerOverviewViewModel`)
* All of TrackerOverview necessary components (`AddMealButton`, `DaySelector`, `ExpandableMeal`, `NutrientBarInfo`, `NutrientInfo`, `NutrientsBar`, `NutrientsHeader`, `TrackedFoodItem`)
* Helper UiState class `Meal`
* Implementation of `SearchScreen` with all respective classes (`SearchState`, `SearchEvent`, `SearchViewModel`)
* All of Search necessary components (`SearchTextField`, `SearchTopBar`, `TrackableFoodItem`)
* Mutual components (`DisplayAlertDialog`)
* Goal Info dialog in `TrackerOverviewScreen` to show user info how his calories goal is calculated and provide an option to change it (go through OnBoarding process again)
* Screenshots of app in `README.md`

### Changed
* Bugfixes for some components and their behaviour (in `TrackerOverviewViewModel` changed the way how `refreshFoods()` function works - it now collapses all meals by default)
* Redesigned Screens object - changed it to Sealed class with `route`, `navigationRoute` and `navArgs` values so navigation in `MainActivity` is more clear
* Moved `Dimensions.kt` to `core-ui` module and added const values in it to provide all sp, dp, shape, size, etc values for different components across the app

## [0.2.0] - 2022-11-08

### Added
* Implementation of the rest of the onBoarding module screens: `NutrientsGoalScreen`, `SummaryScreen` with their respective `State`, `Event` classes and `UseCases`
* Compose components for `SummaryScreen`
* Scaffold with transparent `TopAppBar` in each of the onBoarding Screens with implementation of `OnNavigateBackClick` in `Event` classes
* Propagating `UserInfo` data in each OnBoarding Screen (to remember user changes while navigating back and forth)

### Changed
* Moved DI module regarding onBoarding module to `onboarding_domain`
* Fixed bug in `AgeScreen` TextField
* Code clean-up (regarding DI modules)

## [0.1.1] - 2022-11-03

### Added
* Implementation of most of the onBoarding module screens: `GenderScreen`, `AgeScreen`, `HeightScreen`, `WeightScreen`, `GoalScreen`, `ActivityScreen` with all their UseCases and `Event`, `State`, `ViewModel` classes
* `UseCaseModule` di module that provides all necessary use-case classes for ViewModels
* `UiText` sealed utility class to have a wrapper around text resources (raw/dynamic and string resource values)
* `Preferences` interface in domain package inside core module and its `SharedPreferences` implementation in data layer (also their providers in hilt modules)
* `ShowSnackBar UiEvent` to handle one-time events related to showing users validation error messages

### Changed
* Adjusted some theme colors in app
* Resolved bugs and issues related to multi-modular architecture

## [0.1.0] - 2022-11-02

### Added
* Project initialization
* Setup multi-modular project architecture and structure (dependencies, modules, module inheritance, buildSrc)
* `WelcomeScreen` in `onboarding_presentation` module
* `Dimensions` class in `core-ui` module that provides re-usable cross-project values for spacing (padding)
* `strings.xml` in `core` module that provides all cross-project needed string resources 