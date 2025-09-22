package com.example.geradormegasena

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        prefs = getSharedPreferences("db", MODE_PRIVATE)

        val btn = findViewById<Button>(R.id.btn)
        val editText = findViewById<EditText>(R.id.input)
        val txt_resultado = findViewById<TextView>(R.id.txt_resultado)

        btn.setOnClickListener {
            txt_resultado.text = ""
            val qtd_numeros = editText.text.toString()
            gerarJogo(qtd_numeros, txt_resultado)
        }
    }
    private fun gerarJogo(qtd_numeros: String, txt_resultado: TextView){
        if (qtd_numeros.isNotEmpty()){
            val qtd: Int = qtd_numeros.toInt()
            if (qtd >= 6 && qtd <= 15){
                val jogo = mutableSetOf<Int>()
                while (true){
                    val numero: Int = Random.nextInt(1,61)
                    jogo.add(numero)
                    if (jogo.size == qtd){
                        break
                    }
                }
                txt_resultado.text = jogo.joinToString (" - ")
                val editor = prefs.edit()
                editor.putString("jogo", txt_resultado.text.toString())
                editor.apply()
            }
            else{
                Toast.makeText(this, "Informe um número entre 6 e 15!", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Informe um número entre 6 e 15!", Toast.LENGTH_SHORT).show()
        }
    }
}