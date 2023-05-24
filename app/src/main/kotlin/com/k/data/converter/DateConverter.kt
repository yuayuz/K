package com.k.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun from(long: Long?): Date? = if (long == null) null else Date(long)

    @TypeConverter
    fun into(date: Date?) :Long?= date?.time
}