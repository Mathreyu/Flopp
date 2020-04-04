package com.rraya.flopp.data.models

data class VideoGameResponse(val count: Long,
                             val next: String,
                             val results: List<VideoGameLight>)