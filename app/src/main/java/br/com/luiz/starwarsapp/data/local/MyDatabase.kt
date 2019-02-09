package br.com.luiz.starwarsapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.luiz.starwarsapp.data.local.converter.DateConverter
import br.com.luiz.starwarsapp.data.local.dao.CharacterDao
import br.com.luiz.starwarsapp.data.local.entity.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(DateConverter::class)

abstract class MyDatabase: RoomDatabase() {

    abstract fun CharacterDao(): CharacterDao

    companion object {
        private val INSTANCE : MyDatabase? = null
    }

}