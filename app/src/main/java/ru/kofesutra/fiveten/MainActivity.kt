package ru.kofesutra.fiveten

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.kofesutra.fiveten.databinding.ActivityMainBinding
import ru.kofesutra.fiveten.databinding.BottomSheetBinding
import ru.kofesutra.fiveten.utils.*

var attemptNumber = 0
var valuesList = mutableListOf(0, 0, 0, 0, 0)
var valuesListDraw = mutableListOf(0, 0, 0, 0, 0)
var myValuesList = mutableListOf(0, 0, 0, 0, 0)
var mySummaryList = mutableListOf(0)
var myResultNow = 0
var myResultTotal = 0
var valuesListAndr = mutableListOf(0, 0, 0, 0, 0)
var summaryListAndr = mutableListOf(0)
var andrResultNow = 0
var andrResultTotal = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    val splashScreen = installSplashScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myScoresNow.text = myResultNow.toString()
        binding.myScoresTotal.text = "0"
        binding.andrScoresNow.text = "0"
        binding.andrScoresTotal.text = "0"
        binding.message1.text = "Сделайте бросок"
//        binding.message2.text = "У Вас три попытки"

        Glide.with(this).load(R.drawable.dd1).override(180, 180).into(binding.dice1Draw)
        Glide.with(this).load(R.drawable.dd2).override(180, 180).into(binding.dice2Draw)
        Glide.with(this).load(R.drawable.dd3).override(180, 180).into(binding.dice3Draw)
        Glide.with(this).load(R.drawable.dd4).override(180, 180).into(binding.dice4Draw)
        Glide.with(this).load(R.drawable.dd5).override(180, 180).into(binding.dice5Draw)

        bindDicesResults()

            binding.button.setOnClickListener {
                when (attemptNumber) {
                    0 -> {
                        valuesList = myValuesList
                        binding.button.text = "Ещё бросок!"
                        firstDropUser()
                        bindDicesImages()
                        bindDicesResults()
                        attemptNumber++
                    }
                    1 -> {
                        binding.button.text = "И ещё один!"
                        secondThirdDropsUser()
                        bindDicesImages()
                        bindDicesResults()
                        attemptNumber++
                    }
                    2 -> {
                        binding.button.text = "Дать поиграть Андрюше"
                        attemptNumber++
                        secondThirdDropsUser()
                        bindDicesImages()
                        bindDicesResults()
                        binding.message1.text = "Все броски сделаны"
                    }
                    3 -> {
                        binding.button.text = "Бросок!"
                        // ----- Играет Андрюша -----
                        valuesList = valuesListAndr
                        showPopupBottomSheet()
                        firstDropAndroid()
                        secondThirdDropsAndroid()
                        secondThirdDropsAndroid()
                        bindDicesImages()
                        andrCountTotal()
                        bindDicesResults()
                        // End of ----- Играет Андрюша -----
                        binding.message1.text = "Сделайте бросок"
                        attemptNumber = 0
                        gameResult()
                    }
                }// - else if (buttonCount == 3)
            } // - binding.button.setOnClickListener

        supportActionBar?.apply {
            title = "FiveTen" // Меняет название активити
            setDisplayShowHomeEnabled(true) // -||-
            setLogo(R.drawable.dd5) // Показывать лого на баре
            setDisplayUseLogoEnabled(true) // -||-
        }
} // OnCreate

    private fun showPopupBottomSheet(){
        val dialog = BottomSheetDialog(this)
        val view = BottomSheetBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        dialog.setContentView(view.root)
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 2000)
    }

    private fun bindDicesResults(){
        binding.myScoresNow.text = myResultNow.toString()
        binding.andrScoresNow.text = andrResultNow.toString()
        binding.myScoresTotal.text = myResultTotal.toString()
        binding.andrScoresTotal.text = andrResultTotal.toString()
    }

    private fun bindDicesImages(){
        // ----- Заполнение картинками -----
        var diceDrawTemp = 0
        for (i in 0..4) {
            when (valuesListDraw[i]) {
                1 -> diceDrawTemp = R.drawable.dd1
                2 -> diceDrawTemp = R.drawable.dd2
                3 -> diceDrawTemp = R.drawable.dd3
                4 -> diceDrawTemp = R.drawable.dd4
                5 -> diceDrawTemp = R.drawable.dd5
                6 -> diceDrawTemp = R.drawable.dd6
            }
            when (i) {
                0 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding.dice1Draw)
                1 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding.dice2Draw)
                2 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding.dice3Draw)
                3 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding.dice4Draw)
                4 -> Glide.with(this).load(diceDrawTemp).override(180, 180).into(binding.dice5Draw)
            }
        } // End of ----- Заполнение картинками -----
    }

    private fun gameResult(){
        // ------ Итоги -----
        if (myResultTotal >= 100 || andrResultTotal >= 100){
            if (myResultTotal > andrResultTotal) {
                val dialogFragmentHere = YouWin()
                val manager = supportFragmentManager
                dialogFragmentHere.show(manager, "youwin")
                resetAll()
            } else if (myResultTotal < andrResultTotal)
            {
                val dialogFragmentHere = YouLoose()
                val manager = supportFragmentManager
                dialogFragmentHere.show(manager, "youloose")
                resetAll()
            } else if (myResultTotal == andrResultTotal)
            {
                val dialogFragmentHere = WinWin()
                val manager = supportFragmentManager
                dialogFragmentHere.show(manager, "winwin")
                resetAll()
            }
        }
    }

    private fun resetAll(){
        attemptNumber = 0
        valuesList = mutableListOf(0, 0, 0, 0, 0)
        myValuesList = mutableListOf(0, 0, 0, 0, 0)
        valuesListDraw = mutableListOf(0, 0, 0, 0, 0)
        myResultNow = 0
        myResultTotal = 0
        valuesListAndr = mutableListOf(0, 0, 0, 0, 0)
        andrResultNow = 0
        andrResultTotal = 0
        mySummaryList = mutableListOf(0)
        summaryListAndr = mutableListOf(0)
        bindDicesResults()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.howto -> {
                Toast.makeText(applicationContext, "Обо!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityHowToPlay::class.java)
                startActivity(intent)
                return true
            }
            R.id.exit -> {
                Toast.makeText(applicationContext, "Пока всем!", Toast.LENGTH_SHORT).show()
                quitApp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun quitApp() {
        this@MainActivity.finish()
        finish()
    }
} //
