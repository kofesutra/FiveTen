package ru.kofesutra.fiveten

import android.content.ContentValues.TAG
import android.util.Log

fun twoThree(){

    val listOfNulls = mutableListOf<Int>()

    if (myValuesList.contains(0)){
        for( z in 0 until myValuesList.size) {
            val indexOfVal = myValuesList[z]
            if (indexOfVal == 0) {
                // Добавляем в список индексов значения
                listOfNulls.add(z)
            }
        }
        if (listOfNulls.size > 0) {
            for (i in 0 until listOfNulls.size){
                val random = (1..6).shuffled()
                val nullVal = listOfNulls[i]
                var randomValue: Int = random[0]
                valuesListDraw[nullVal] = randomValue // Копируем значения в лист для отображения картинок
                when (randomValue) {
                    1 -> randomValue = 10
                    in 2..4 -> randomValue = 0
                    6 -> randomValue = 0
//                    else -> randomValue
                }
//                println("$randomValue")

//                valuesList.set(nullVal, randomValue)
                myValuesList[nullVal] = randomValue
            }
        }// if (valuesList.contains(0))
        myResultNow = myValuesList.sum()
    }else{
        println("Нет необходимости в броске")
    }

    if (attemptNumber == 3) {
        mySummaryList.add(0, myResultNow)
        Log.d(TAG, "Мой итог: $mySummaryList")
        myResultTotal = mySummaryList.sum()
    }
}// fun twoThree

