package com.example.testemvvm.utilities

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object MaskUtil{
    const val CPF = "###.###.###-##"
    const val CNPJ = "##.###.###/####-##"
    const val CELLPHONE = "(##) # ####-####"
//    const val PLACA = "###-####"
//    const val CEP = "#####-###"

    fun insert(aText: String, maskTypeProps: String): String {
        val text = unmask(aText)
        var i = 0
        val mascara = StringBuilder()
        for (m in maskTypeProps.toCharArray()) {
            if (m != '#') {
                mascara.append(m)
                continue
            }
            try {
                mascara.append(text[i])
            } catch (e: Exception) {
                break
            }
            i++
        }
        return mascara.toString()
    }

    fun insert(editText: EditText, maskTypeProps: String): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var oldValue = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val value = unmask(s.toString())
                var maskAux = ""
                if (isUpdating) {
                    oldValue = value
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in maskTypeProps.toCharArray()) {
                    if (m != '#' && value.length > oldValue.length || m != '#' && value.length < oldValue.length && value.length != i) {
                        maskAux += m
                        continue
                    }
                    maskAux += try {
                        value[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(maskAux)
                editText.setSelection(maskAux.length)
            }

            override fun afterTextChanged(pEditable: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        }
    }

    fun unmask(s: String): String {
        return s.replace("[^a-zA-Z0-9-]*".toRegex(), "")
    }

    fun unmaskNew(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("-".toRegex(), "")
            .replace("/".toRegex(), "").replace("[(]".toRegex(), "")
            .replace("[)]".toRegex(), "").replace(" ".toRegex(), "")
            .replace(",".toRegex(), "")
    }

    fun insert(mask: String, ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = unmaskNew(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var index = 0
                for (element in mask) {
                    if (element != '#') {
                        if (index == str.length && str.length < old.length) {
                            continue
                        }
                        mascara += element
                        continue
                    }
                    mascara += try {
                        str[index]
                    } catch (e: Exception) {
                        break
                    }
                    index++
                }
                if (mascara.isNotEmpty()) {
                    var lastChar = mascara[mascara.length - 1]
                    var hadSign = false
                    while (isASign(lastChar) && str.length == old.length) {
                        mascara = mascara.substring(0, mascara.length - 1)
                        lastChar = mascara[mascara.length - 1]
                        hadSign = true
                    }
                    if (mascara.isNotEmpty() && hadSign) {
                        mascara = mascara.substring(0, mascara.length - 1)
                    }
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        }
    }

    fun insert(mask: String, ediTxt: EditText, listener: EditTextListener): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                listener.onTextChanged(s, start, before, count)
                val str = unmaskNew(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var index = 0
                for (element in mask) {
                    if (element != '#') {
                        if (index == str.length && str.length < old.length) {
                            continue
                        }
                        mascara += element
                        continue
                    }
                    mascara += try {
                        str[index]
                    } catch (e: Exception) {
                        break
                    }
                    index++
                }
                if (mascara.isNotEmpty()) {
                    var lastChar = mascara[mascara.length - 1]
                    var hadSign = false
                    while (isASign(lastChar) && str.length == old.length) {
                        mascara = mascara.substring(0, mascara.length - 1)
                        lastChar = mascara[mascara.length - 1]
                        hadSign = true
                    }
                    if (mascara.isNotEmpty() && hadSign) {
                        mascara = mascara.substring(0, mascara.length - 1)
                    }
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                listener.beforeTextChanged(s, start, count, after)
            }
            override fun afterTextChanged(s: Editable) {
                listener.afterTextChanged(s)
            }
        }
    }

    fun createMaskDocument(cpfCnpj: String): String {
        val documentUnmask = unmaskNew(cpfCnpj)
        if (documentUnmask.count() == 11) {
            val prefix = documentUnmask.substring(0, 3)
            val sufix = documentUnmask.substring(9, 11)
            return "${prefix}.***.***-${sufix}"
        }

        val prefix = documentUnmask.substring(0, 2)
        val sufix = documentUnmask.substring(12, 14)
        return "${prefix}.***.***/****-${sufix}"
    }

    fun createMaskDotsDocument(cpfCnpj: String): String {
        val documentUnmask = unmaskNew(cpfCnpj)
        if (documentUnmask.count() == 11) {
            val groupOne = documentUnmask.substring(0, 3)
            val groupTwo = documentUnmask.substring(3, 6)
            val groupTree = documentUnmask.substring(6, 9)
            val groupFour = documentUnmask.substring(9, 11)
            return "$groupOne.$groupTwo.$groupTree-$groupFour"
        }

        val groupOne = documentUnmask.substring(0, 2)
        val groupTwo = documentUnmask.substring(2, 5)
        val groupTree = documentUnmask.substring(5, 8)
        val groupFour = documentUnmask.substring(8, 12)
        val groupFive = documentUnmask.substring(12, 14)
        return "$groupOne.$groupTwo.$groupTree/$groupFour-$groupFive"
    }

    fun maskPhoneAsterisk(phone: String): String {
        var numberString = phone
        for (index in 4 until numberString.length - 2) {
            numberString = numberString.replaceRange(index, index + 1, "*")
        }
        return numberString
    }

    fun maskEmailAsterisk(email: String): String{
        return email.replace(Regex("(?<=.{3}).(?=.*@)"), "*")
    }
}

interface EditTextListener {
    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
    fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
    fun afterTextChanged(s: Editable)
}

fun isASign(c: Char): Boolean {
    return c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' '
}