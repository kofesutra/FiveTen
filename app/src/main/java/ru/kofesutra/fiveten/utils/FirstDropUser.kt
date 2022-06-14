package ru.kofesutra.fiveten.utils

import ru.kofesutra.fiveten.myResultNow
import ru.kofesutra.fiveten.myValuesList
import ru.kofesutra.fiveten.valuesListDraw

// Бросок пяти костей
// В valuesListDraw вносятся значения от 1 до 6 для отображения изображений костей
// в myValuesList вносятся только 5, 10 и 0 для подсчёта итогов

fun firstDropUser() {
    for (i in 0..4) {
        val random = (1..6).shuffled()
        // если значение 5, сохраняем, если 1, то меняем на 10, остальные делаем нулями
        var randomValue: Int = random[0]
        valuesListDraw[i] = randomValue // Копируем значения в лист для отображения картинок
        when (randomValue) {
            1 -> randomValue = 10
            in 2..4 -> randomValue = 0
            6 -> randomValue = 0
        }
        myValuesList[i] = randomValue // Записываем результат в основной список valuesList
    } // for (i in 0..4)
    myResultNow = myValuesList.sum()
}

