package com.example.retrofitencyclopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitencyclopedia.Model.Character
import com.example.retrofitencyclopedia.Retrofit.retrofit
import com.example.retrofitencyclopedia.Services.CharacterServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory

class CharactersSwitchPages : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_switch_pages)

        val goToMenu = findViewById<Button>(R.id.buttonGoBackToMenu2)
        goToMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val characterName = findViewById<TextView>(R.id.textViewResultName)
        val characterStatus = findViewById<TextView>(R.id.textViewResultStatus)
        val characterSpecies = findViewById<TextView>(R.id.textViewResultSpecies)
        val characterGender = findViewById<TextView>(R.id.textViewResultGender)
        val characterAvatar = findViewById<ImageView>(R.id.imageViewSwitchPagesAvatar)

        val BASE_URL = "https://rickandmortyapi.com/api/"
        val httpClient = OkHttpClient()

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: retrofit2.Retrofit = retrofit2.Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service: CharacterServices = retrofit.create(CharacterServices::class.java)

        var wantedId = 1

            service.getCharacterById(wantedId).enqueue(object :
                Callback<Character> {
                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(this@CharactersSwitchPages, "Błąd połączenia", Toast.LENGTH_LONG).show()
                        return
                    }


                    val foundCharacter = response.body()!!
                    val name = foundCharacter.name
                    val status = foundCharacter.status
                    val gender = foundCharacter.gender
                    val avatar = foundCharacter.image
                    val species = foundCharacter.species



                    //Ustawienie danych
                    characterName.text = name
                    characterStatus.text = status
                    characterSpecies.text = species
                    characterGender.text = gender
                    Picasso.with(this@CharactersSwitchPages).load(avatar).into(characterAvatar);

                }

                override fun onFailure(call: Call<Character>, t: Throwable) {

                    Toast.makeText(this@CharactersSwitchPages, t.message, Toast.LENGTH_LONG).show()

                }


            })


        //Przejście do następnej postaci
        findViewById<Button>(R.id.buttonNext).setOnClickListener {
            wantedId = wantedId + 1

            service.getCharacterById(wantedId).enqueue(object :
                Callback<Character> {
                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(this@CharactersSwitchPages, "Błąd połączenia", Toast.LENGTH_LONG).show()
                        return
                    }


                    val foundCharacter = response.body()!!
                    val name = foundCharacter.name
                    val status = foundCharacter.status
                    val gender = foundCharacter.gender
                    val avatar = foundCharacter.image
                    val species = foundCharacter.species



                    //Ustawienie danych
                    characterName.text = name
                    characterStatus.text = status
                    characterSpecies.text = species
                    characterGender.text = gender
                    Picasso.with(this@CharactersSwitchPages).load(avatar).into(characterAvatar);

                }

                override fun onFailure(call: Call<Character>, t: Throwable) {

                    Toast.makeText(this@CharactersSwitchPages, t.message, Toast.LENGTH_LONG).show()

                }


            })
            }

        //Przejście do poprzedniej postaci
        findViewById<Button>(R.id.buttonPrevious).setOnClickListener {
            if(wantedId == 1)
            {
                Toast.makeText(this@CharactersSwitchPages, "To pierwsza strona encyklopedii!", Toast.LENGTH_SHORT).show()
            }
            wantedId = wantedId - 1

            service.getCharacterById(wantedId).enqueue(object :
                Callback<Character> {
                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(this@CharactersSwitchPages, "Błąd połączenia", Toast.LENGTH_LONG).show()
                        return
                    }


                    val foundCharacter = response.body()!!
                    val name = foundCharacter.name
                    val status = foundCharacter.status
                    val gender = foundCharacter.gender
                    val avatar = foundCharacter.image
                    val species = foundCharacter.species



                    //Ustawienie danych
                    characterName.text = name
                    characterStatus.text = status
                    characterSpecies.text = species
                    characterGender.text = gender
                    Picasso.with(this@CharactersSwitchPages).load(avatar).into(characterAvatar);

                }

                override fun onFailure(call: Call<Character>, t: Throwable) {

                    Toast.makeText(this@CharactersSwitchPages, t.message, Toast.LENGTH_LONG).show()

                }


            })
        }
        }


    fun getService()
    {
        var wantedId = 1
        val characterName = findViewById<TextView>(R.id.textViewResultName)
        val characterStatus = findViewById<TextView>(R.id.textViewResultStatus)
        val characterSpecies = findViewById<TextView>(R.id.textViewResultSpecies)
        val characterGender = findViewById<TextView>(R.id.textViewResultGender)
        val characterAvatar = findViewById<ImageView>(R.id.imageViewSwitchPagesAvatar)
        val service: CharacterServices = retrofit.create(CharacterServices::class.java)

        service.getCharacterById(wantedId).enqueue(object :
            Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@CharactersSwitchPages, "Błąd połączenia", Toast.LENGTH_LONG).show()
                    return
                }


                val foundCharacter = response.body()!!
                val name = foundCharacter.name
                val status = foundCharacter.status
                val gender = foundCharacter.gender
                val avatar = foundCharacter.image
                val species = foundCharacter.species



                //Ustawienie danych
                characterName.text = name
                characterStatus.text = status
                characterSpecies.text = species
                characterGender.text = gender
                Picasso.with(this@CharactersSwitchPages).load(avatar).into(characterAvatar);

            }

            override fun onFailure(call: Call<Character>, t: Throwable) {

                Toast.makeText(this@CharactersSwitchPages, t.message, Toast.LENGTH_LONG).show()

            }


        })
    }
    }