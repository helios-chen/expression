package com.helios.expression

val opPriorityMap = mapOf(
        '+' to 1,
        '-' to 1,
        '*' to 2,
        '/' to 2,
        '(' to 0,
        ')' to 0
)

fun isOperator(char: Char): Boolean {
    return listOf('+', '-', '*', '/', '(', ')').any { it == char }
}

fun Char.loe(ch: Char): Boolean {
    val left = opPriorityMap[this] ?: 0
    val right = opPriorityMap[ch] ?: 0
    return left <= right
}