package br.com.luiz.starwarsapp.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Character(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var height: Int = 0,
    var mass: Int = 0,
    var hair_color: String = "",
    var eye_color: String = "",
    var gender: String = "",
    var lastRefresh: Date? = null

)