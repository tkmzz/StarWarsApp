package br.com.luiz.starwarsapp.data.local.converter

import android.arch.persistence.room.TypeConverter
import java.sql.Date


object DateConverter{

    @TypeConverter @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

}