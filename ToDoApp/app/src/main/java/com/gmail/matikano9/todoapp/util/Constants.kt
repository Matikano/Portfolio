package com.gmail.matikano9.todoapp.util

object Constants {

    const val DATE_FORMAT = "dd-MM-yyyy"
    const val TIME_FORMAT = "HH:mm"

    object Database{
        const val DATABASE_NAME = "todo.db"
        const val DATABASE_VERSION = 1
    }

    object Navigation {
        const val NAV_ARG_TODO_TASK = "toDoTask"
    }

    object Validation {
        const val TITLE_ERROR = "Title cannot be empty"
        const val DESCRIPTION_ERROR = "Description cannot be empty"
        const val DUE_DATE_EMPTY = "Due date cannot be empty"
        const val DUE_DATE_INVALID = "Please enter valid date"
        const val DUE_TIME_EMPTY = "Due time cannot be empty"
    }

    object Splash {
        const val SPLASH_DURATION = 2500L //in millis
    }

    object Test {
        const val TODO_TITLE = "title_test"
        const val TODO_DESCRIPTION = "description_test"
        const val UPDATED_TODO_TITLE = "updated_title_test"

        const val TODO_TITLE_SEARCH_QUERY = "SSS"

        const val TEST_DATABASE_NAME = "test_db"
    }


}