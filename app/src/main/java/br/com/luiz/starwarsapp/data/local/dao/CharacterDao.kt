package br.com.luiz.starwarsapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.luiz.starwarsapp.data.local.entity.Character
import java.util.*

@Dao
interface CharacterDao {

    @Insert(onConflict = REPLACE)
    fun save(character: Character)

    @Query("SELECT * FROM Character WHERE id = :id")
    fun load(id: Int): LiveData<Character>

    @Query("SELECT * FROM Character WHERE id = :login AND lastRefresh = :lastRefresh LIMIT 1")
    fun hasUser(login: String, lastRefresh: Date): Character

}