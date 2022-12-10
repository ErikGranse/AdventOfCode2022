package net.loonmagnet.kotlin

import net.loonmagnet.util.Utils
import java.lang.Integer.signum
import java.lang.RuntimeException
import kotlin.math.abs

fun main() {
    println(run(2))
    println(run(10))
}

fun run(len: Int): Int {
    var knots = MutableList(len) { Knot(0, 0) }
    val locations = HashSet<Knot>()

    val moves = Utils.readFile("data/day9.txt").map { line -> line.split(" ") }
    for (move in moves) {
        repeat(move[1].toInt()) {
            knots[0] = knots[0].move(move[0])
            knots = knots.mapIndexed { idx, knot -> if (idx == 0) knot else knot.follow(knots[idx - 1]) }.toMutableList()
            locations.add(knots[len - 1])
        }
    }
    return locations.size
}

data class Knot constructor(val x: Int, val y: Int) {
    fun move(dir: String): Knot {
        return when (dir) {
            "U" -> Knot(this.x, this.y + 1)
            "D" -> Knot(this.x, this.y - 1)
            "L" -> Knot(this.x - 1, this.y)
            "R" -> Knot(this.x + 1, this.y)
            else -> {
                throw RuntimeException("Unexpected direction")
            }
        }
    }

    fun follow(leader: Knot): Knot {
        val xDist = leader.x - this.x
        val yDist = leader.y - this.y
        return when (abs(xDist) == 2 || abs(yDist) == 2) {
            true -> Knot(this.x + signum(xDist), this.y + signum(yDist))
            false -> this
        }
    }
}