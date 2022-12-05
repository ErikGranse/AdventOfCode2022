package net.loonmagnet


fun main() {
    val lines = Utils.readFile("data/day4.txt")
    var overlaps = 0
    var intersections = 0

    for (line in lines) {
        val split = line.split("-", ",")
        val leftRange = IntRange(split[0].toInt(), split[1].toInt()).toCollection(HashSet())
        val rightRange = IntRange(split[2].toInt(), split[3].toInt()).toCollection(HashSet())

        if (leftRange.containsAll(rightRange) || rightRange.containsAll(leftRange)) overlaps++
        if (leftRange.removeAll(rightRange)) intersections++
    }
    println(overlaps)
    println(intersections)
}

