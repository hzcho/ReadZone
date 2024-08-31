package com.example.domain.parameters

import java.io.InputStream

data class ReadParam(
    var page:Int,
    var charCount:Int,
    var res:InputStream
)
