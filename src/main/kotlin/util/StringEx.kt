package util


fun String.firstToUpperCase():String= substring(0, 1).toUpperCase() + substring(1)
fun String.firstToLowerCase():String= substring(0, 1).toLowerCase() + substring(1)