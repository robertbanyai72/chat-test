package hu.robert.banyai.lmitest.presentation.common.extension

import java.util.regex.Pattern

fun String.findUrls(): List<String> {
    val urls = ArrayList<String>()
    val regex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?+-=\\\\.&]*)"
    val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)

    while (matcher.find()) {
        urls.add(substring(matcher.start(0),
                matcher.end(0)))
    }

    return urls
}