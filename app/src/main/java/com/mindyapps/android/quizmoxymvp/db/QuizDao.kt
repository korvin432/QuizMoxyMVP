package com.mindyapps.android.quizmoxymvp.db

import androidx.room.*

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    fun getAll(): List<QuizEntity>

    @Query("SELECT * FROM quiz WHERE category LIKE :category")
    fun findByCategory(category: String): QuizEntity

    @Insert
    fun insertAll(vararg quiz: QuizEntity)

    @Delete
    fun delete(quiz: QuizEntity)

    @Update
    fun updateTodo(vararg quizes: QuizEntity)
}