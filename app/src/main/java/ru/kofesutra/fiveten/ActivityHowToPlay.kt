package ru.kofesutra.fiveten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kofesutra.fiveten.databinding.ActivityHowToPlayBinding

class ActivityHowToPlay : AppCompatActivity() {
    private lateinit var binding: ActivityHowToPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)
        binding = ActivityHowToPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rules = "Правила игры:\n" +
                "\n" +
                "На один ход даётся три броска костей\n" +
                "\n" +
                "\"5\" считается как пять очков, \"1\" считается как десять очков, другие грани не имеют значения.\n" +
                "\n" +
                "Кубики с невыпавшими \"5\" и \"1\" перебрасываются.\n" +
                "\n" +
                "Кубики бросаются игроками по очереди.\n" +
                "\n" +
                "Побеждает тот игрок, который раньше наберёт 100 очков. \n" +
                "\n" +
                "PS. Эту игру я когда-то придумал сам :)"

//        binding.editTextTextMultiLine.text = rules
        binding.howText.text = rules

        supportActionBar?.apply {
            title = "Rules" // Меняет название активити
            setDisplayHomeAsUpEnabled(true) // Back button
            setDisplayShowHomeEnabled(true) // -||-
	        setLogo(R.drawable.dd5) // Показывать лого на баре
	        setDisplayUseLogoEnabled(true) // -||-
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}