package com.programmer2704.movapp.data.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    @NonNull
    // NAME MUST BE SAME WITH THAT IN API JSON, IF NOT, FOR EXAMPLE IN THIS KEYWORD, THE POSTER WON'T SHOW
    val poster_path: String?
)

data class MovieEntityResponse(
    val results: List<MovieEntity>
)