package br.com.luiz.starwarsapp.data.repositories

import android.arch.lifecycle.LiveData
import br.com.luiz.starwarsapp.data.local.dao.CharacterDao
import br.com.luiz.starwarsapp.data.local.entity.Character
import br.com.luiz.starwarsapp.data.remote.CharacterWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject
constructor(private val webservice: CharacterWebService, private val characterDao: CharacterDao, private val executor: Executor){

    fun getCharacter(characterId: Int): LiveData<Character> {
        refreshCharacter(characterId)
        return characterDao.load(characterId)
    }

    private fun refreshCharacter(characterId: Int){
        executor.execute {

            val characterExists = characterDao.hasUser(characterId.toString(), getMaxRefreshTime(Date())) != null

            if(!characterExists) {
                webservice.getCharacter(characterId).enqueue(object : Callback<Character>{

                    override fun onResponse(call: Call<Character>?, response: Response<Character>) {
                        executor.execute {
                            val character = response.body()
                            character?.lastRefresh = Date()
                            if (character != null)
                                characterDao.save(character)
                        }
                    }

                    override fun onFailure(call: Call<Character>?, t: Throwable?) {

                    }
                })
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {

        private const val FRESH_TIMEOUT_IN_MINUTES = 3
    }


}