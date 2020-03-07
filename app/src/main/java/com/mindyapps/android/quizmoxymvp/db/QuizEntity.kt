package com.mindyapps.android.quizmoxymvp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "difficulty") var difficulty: String,
    @ColumnInfo(name = "right_answers") var rightAnswers: Int
){
    constructor():this(null,"","", 0)
}
