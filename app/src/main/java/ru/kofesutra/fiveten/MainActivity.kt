package ru.kofesutra.fiveten

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.kofesutra.fiveten.databinding.ActivityMainBinding
import ru.kofesutra.fiveten.utils.YouLoose
import ru.kofesutra.fiveten.utils.YouWin

var buttonCount = 0
var valuesList = mutableListOf(0, 0, 0, 0, 0)
val myValuesList = mutableListOf(0, 0, 0, 0, 0)
val myValuesListDraw = mutableListOf(0, 0, 0, 0, 0)
var myResultNow = 0
var myResultTotal = 0
val valuesListAndr = mutableListOf(0, 0, 0, 0, 0)
var andrResultNow = 0
var andrResultTotal = 0
val mySummaryList = mutableListOf(0)
val summaryListAndr = mutableListOf(0)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        dice1Draw.

        binding.myScoresNow.text = myResultNow.toString()
        binding.myScoresTotal.text = "0"
        binding.andrScoresNow.text = "0"
        binding.andrScoresTotal.text = "0"
        binding.message1.text = "Сделайте бросок"
//        binding.message2.text = "У Вас три попытки"

        binding.dice1.text = valuesList[0].toString()
        binding.dice2.text = valuesList[1].toString()
        binding.dice3.text = valuesList[2].toString()
        binding.dice4.text = valuesList[3].toString()
        binding.dice5.text = valuesList[4].toString()

        buttonCount = 0

            binding.button.setOnClickListener {
                if (buttonCount == 0) {
                    valuesList = myValuesList
                    binding.button.text = "Ещё бросок!"
//                    binding.message2.text = "1-й из 3"
                    one()
                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.myScoresNow.text = myResultNow.toString()
                    buttonCount++
                } else if (buttonCount == 1) {
                    binding.button.text = "И ещё один!"
//                    binding.message2.text = "2-й из 3"
                    twoThree()
                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.myScoresNow.text = myResultNow.toString()
                    buttonCount++
                } else if (buttonCount == 2) {
                    binding.button.text = "Играет Андрюша"
//                    binding.message2.text = "3-й из 3"
                    buttonCount++
                    twoThree()
                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.myScoresNow.text = myResultNow.toString()
                    binding.myScoresTotal.text = myResultTotal.toString()
                    binding.message1.text = "Все броски сделаны"
                } else if (buttonCount == 3){
                    binding.button.text = "Бросок!"

        // ----- Играет Андрюша -----
                    valuesList = valuesListAndr

                    oneAndr()
                    pauseFun()

                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.andrScoresNow.text = andrResultNow.toString()

                    twoThreeAndr()
                    pauseFun()

                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.andrScoresNow.text = andrResultNow.toString()

                    twoThreeAndr()
                    andrCountTotal()

                    binding.dice1.text = valuesList[0].toString()
                    binding.dice2.text = valuesList[1].toString()
                    binding.dice3.text = valuesList[2].toString()
                    binding.dice4.text = valuesList[3].toString()
                    binding.dice5.text = valuesList[4].toString()
                    binding.andrScoresNow.text = andrResultNow.toString()
                    binding.andrScoresTotal.text = andrResultTotal.toString()

        // End of ----- Играет Андрюша -----

                    binding.message1.text = "Сделайте бросок"
                    buttonCount = 0

                    // ------ Итоги -----
                    if (myResultTotal > 100 || andrResultTotal > 100){
                        if (myResultTotal > andrResultTotal) {
                            val dialogFragmentHere = YouWin()
                            val manager = supportFragmentManager
                            dialogFragmentHere.show(manager, "youwin")
                        } else
                        {
                            val dialogFragmentHere = YouLoose()
                            val manager = supportFragmentManager
                            dialogFragmentHere.show(manager, "youloose")
                        }
                    }
                    // End of ------ Итоги -----
                }// - else if (buttonCount == 3)

//                    pauseFun()

            } // - binding.button.setOnClickListener
        supportActionBar?.apply {
            title = "FiveTen" // Меняет название активити
//            setDisplayHomeAsUpEnabled(true) // Back button
            setDisplayShowHomeEnabled(true) // -||-
            setLogo(R.drawable.d5) // Показывать лого на баре
            setDisplayUseLogoEnabled(true) // -||-
        }
} // OnCreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    } // - override fun onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.howto -> {
                Toast.makeText(applicationContext, "Обо!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityHowToPlay::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    } // - override fun onOptionsItemSelected

} //


fun pauseFun() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(500) // non-blocking delay for 3 second (default time unit is ms)
        println("Всйо") // print after delay
    }}
