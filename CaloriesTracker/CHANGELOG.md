# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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