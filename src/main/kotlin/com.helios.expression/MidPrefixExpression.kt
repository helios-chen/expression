package com.helios.expression

import java.util.*


class MidPrefixExpression {

    fun mid2Prefix(mid: String): String {
        val stack = Stack<Char>()
        val reverse = mid.reversed().toCharArray()
        val sb = StringBuffer()
        reverse.forEach { ch ->
            //数字
            if (!isOperator(ch)) {
                sb.append(ch)
            }
            //如果是操作符，栈顶无元素直接入栈
            else if (stack.isEmpty() || ch == ')') {
                stack.push(ch)
            }
            //操作符，如果是(，出栈，直到遇到)
            else if (ch == '(' || ch.loe(stack.peek())) {
                while (stack.isNotEmpty() && stack.peek() != ')') {
                    val op = stack.pop()
                    sb.append(op)
                }
                if (stack.isNotEmpty() && stack.peek() == ')') stack.pop()
                if (ch != '(') stack.push(ch)
            } else {
                stack.push(ch)
            }
        }
        while (stack.isNotEmpty()) {
            sb.append(stack.pop())
        }

        return sb.reverse().toString()
    }

    fun prefix2Mid(str: String): String {
        val stack: Stack<String> = Stack()
        val reverse = str.reversed()
        var preOp: Char? = null
        reverse.forEach { ch ->
            //数字，入栈
            if (!isOperator(ch)) {
                stack.push(ch.toString())
            } else {
                val left = stack.pop()
                val right = stack.pop()
                val preop = preOp
                if(preop != null && !ch.loe(preop)) {
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
    val convert = MidPrefixExpression()
    val mid = "1-(2+3)*4+5"
    val prefix = "-1+*+2345"
//    println(convert.mid2Prefix(mid))
    println(convert.prefix2Mid(prefix))
}