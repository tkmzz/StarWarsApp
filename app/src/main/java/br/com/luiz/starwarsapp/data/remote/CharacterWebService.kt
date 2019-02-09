package br.com.luiz.starwarsapp.data.remote


import br.com.luiz.starwarsapp.data.local.entity.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterWebService{

    @GET("/people/{character}/")
    fun getCharacter(@Path("character") characterId: Int): Call<Character>

}