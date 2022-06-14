package ru.kofesutra.fiveten.utils

import ru.kofesutra.fiveten.andrResultNow
import ru.kofesutra.fiveten.valuesListAndr
import ru.kofesutra.fiveten.valuesListDraw

fun firstDropAndroid() {
    for (i in 0..4) {
        val random = (1..6).shuffled()
        var randomValue: Int = random[0]
        valuesListDraw[i] = randomValue
        when (randomValue) {
            1 -> randomValue = 10
            in 2..4 -> randomValue = 0
            6 -> randomValue = 0
        }
        valuesListAndr[i] = randomValue
    } // for (i in 0..4)
    andrResultNow = valuesListAndr.sum()
}