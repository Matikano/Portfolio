package com.gmail.matikano9.todoapp.data.database

import androidx.room.*
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query(
        """
            SELECT * 
            FROM todo_task_tab 
            ORDER BY id ASC
        """
    )
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query(
        """
            SELECT * 
            FROM todo_task_tab 
            WHERE id = :taskId
        """
    )
    fun getTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE FROM todo_task_tab")
    suspend fun deleteAllTasks()

    @Query(
        """
            SELECT * 
            FROM todo_task_tab 
            WHERE title LIKE '%' || :searchQuery || '%' 
            OR description LIKE '%' || :searchQuery || '%' || :searchQuery
        """
    )
    fun searchTasks(searchQuery: String): Flow<List<ToDoTask>>

    @Query(
        """
            SELECT * 
            FROM todo_task_tab ORDER BY 
            CASE 
                WHEN priority LIKE 'L%' THEN 1
                WHEN priority LIKE 'M%' THEN 2
                WHEN priority LIKE 'H%' THEN 3
            END
        """
    )
    fun sortByPriorityLow(): Flow<List<ToDoTask>>

    @Query(
        """
            SELECT * 
            FROM todo_task_tab 
            ORDER BY 
            CASE 
                WHEN priority LIKE 'H%' THEN 1
                WHEN priority LIKE 'M%' THEN 2
                WHEN priority LIKE 'L%' THEN 3
            END
        """
    )
    fun sortByPriorityHigh(): Flow<List<ToDoTask>>

}