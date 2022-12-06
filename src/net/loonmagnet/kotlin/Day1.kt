package net.loonmagnet.kotlin

import net.loonmagnet.util.Utils

fun main() {

    val inventory = Utils.readFile("data/day1.txt")
    var totals:MutableList<Int> = mutableListOf()

    var subtotal = 0
    for (item in inventory) {
        if(item.isNotBlank()) {
            subtotal += item.toInt()
        } else {
            totals.add(subtotal)
            subtotal = 0;
        }
    }

    totals.sortDescending();
    println(totals[0])
    println(totals.stream().limit(3).mapToInt(Int::toInt).sum())
}

//67633
//199628