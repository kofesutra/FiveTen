package ru.kofesutra.fiveten

fun one() {
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
} // fun one

