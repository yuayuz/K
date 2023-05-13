package com.k.data.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime


class DateConverter {

    @TypeConverter
    fun from(s: String?): LocalDateTime? = if (s == null) null else LocalDateTime.parse(s)

    @TypeConverter
    fun into(date: LocalDateTime?) :String?= date?.toString()
}



