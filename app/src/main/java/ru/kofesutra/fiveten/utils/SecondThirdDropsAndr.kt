package ru.kofesutra.fiveten

import android.content.ContentValues
import android.util.Log

fun twoThreeAndr(){

    val listOfNulls = mutableListOf<Int>()

    if (valuesListAndr.contains(0)){
        for( z in 0 until valuesListAndr.size) {
            val indexOfVal = valuesListAndr[z]
            if (indexOfVal == 0) {
                listOfNulls.add(z)
            }
        }
        if (listOfNulls.size > 0) {
            for (i in 0 until listOfNulls.size){
                val random = (1..6).shuffled()
                val nullVal = listOfNulls[i]
                var randomValue: Int = random[0]
                valuesListDraw[nullVal] = randomValue

                when (randomValue) {
                    1 -> randomValue = 10
                    in 2..4 -> randomValue = 0
                    6 -> randomValue = 0
                }
                valuesListAndr[nullVal] = randomValue
            }
        }// if (valuesList.contains(0))
        andrResultNow = valuesListAndr.sum()
    }else{
        println("Нет необходимости в броске")
    }
}

fun andrCountTotal(){
        summaryListAndr.add(0, andrResultNow)
//        Log.d(ContentValues.TAG, "Андрюшин итог: $summaryListAndr")
        andrResultTotal = summaryListAndr.sum()
}