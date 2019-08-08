package com.helios.expression

import java.util.*

class MidSufixExpression {

    fun mid2Suffix(str: String): String {
        val stack: Stack<Char> = Stack()
        val sb = StringBuffer()
        str.forEach { ch ->
            if (!isOperator(ch)) {
                sb.append(ch)
            } else if (stack.isEmpty() || ch == '(') {
                stack.push(ch)
            } else if (ch == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop())
                }
                if (stack.peek() == '(') stack.pop()
            } else if (ch.loe(stack.peek())) {
                while (stack.isNotEmpty() && ch.loe(stack.peek())) {
                    sb.append(stack.pop())
                }
                stack.push(ch)
            } else {
                stack.push(ch)
            }
        }

        while (stack.isNotEmpty()) sb.append(stack.pop())
        return sb.toString()
    }

    fun suffix2Mid(str: String): String {
        val stack: Stack<String> = Stack()
        var preOp:Char? = null
        str.forEach { ch ->
            if (!isOperator(ch)) {
                stack.push(ch.toString())
            } else {
                val right = stack.pop()
                val left = stack.pop()
                val pre = preOp
                if(pre != null && !ch.loe(pre)){
                    stack.push("($left)$ch$right")
                }
                else {
                    stack.push("$left$ch$right")
                }

                preOp = ch
            }
        }
        return stack.pop()
    }
}

fun main() {
    val mid = "1-(2+3)*4+5"
    val suffix = "123+4*-5+"
    val convert = MidSufixExpression()
    println(convert.mid2Suffix(mid))
    println(convert.suffix2Mid(suffix))

}