package com.jww.rereapp.enums

enum class NavigationType(val value: String) {
    RERE("reRE"), MOVIE("영화"), MUSIC("음악"), INFO("정보");

    companion object {
        fun getType(position: Int) = values().first { it.ordinal == position }
    }
}