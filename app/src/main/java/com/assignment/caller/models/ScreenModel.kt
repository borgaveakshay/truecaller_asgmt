package com.assignment.caller.models

data class ScreenModel(
    var tenthChar: Char = ' ',
    var charArray: Array<Char> = emptyArray(),
    var wordCount: HashMap<String, Int> = hashMapOf()
) {

    fun getStringFromChar(): String = tenthChar.toString()

    fun getStringFromArray(): String = "" + charArray.map {
        "[$it] "
    }

    fun getStringFromMap(): String = "" + wordCount.map {
        "[${it.key}, ${it.value}]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ScreenModel

        if (tenthChar != other.tenthChar) return false
        if (!charArray.contentEquals(other.charArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tenthChar.hashCode()
        result = 31 * result + charArray.contentHashCode()
        return result
    }
}