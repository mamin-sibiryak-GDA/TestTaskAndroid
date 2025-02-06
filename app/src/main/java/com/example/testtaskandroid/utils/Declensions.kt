package com.example.testtaskandroid.utils

fun vacancyDeclension(num: Int): String {
        return if (num % 10 == 1 && num % 100 != 11)
            ("вакансия")
        else if ((num % 10 == 2 && num % 100 != 12) || (num % 10 == 3 && num % 100 != 13) || (num % 10 == 4 && num % 100 != 14))
            ("вакансии")
        else
            ("вакансий")
}

fun peopleDeclension(num: Int): String {
    return if ((num % 10 == 2 && num % 100 != 12) || (num % 10 == 3 && num % 100 != 13) || (num % 10 == 4 && num % 100 != 14))
        ("человека")
    else
        ("человек")
}

fun dateDeclension(str: String): String {
    var date = ""
    when (str.substring(5, 7)) {
        "01" -> date = " января"
        "02" -> date = " февраля"
        "03" -> date = " марта"
        "04" -> date = " апреля"
        "05" -> date = " майя"
        "06" -> date = " июня"
        "07" -> date = " июля"
        "08" -> date = " августа"
        "09" -> date = " сентября"
        "10" -> date = " октября"
        "11" -> date = " ноября"
        "12" -> date = " декабря"
    }
    date = if (str[8] == '0')
        str[9] + date
    else
        str.substring(8, 10) + date
    return date
}
