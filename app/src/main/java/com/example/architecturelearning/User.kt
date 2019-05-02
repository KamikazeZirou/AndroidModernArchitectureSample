package com.example.architecturelearning

data class User(val id: Int,
                val name: String,
                val email: String,
                val avatarUrl: String,
                val htmlUrl: String) {
    companion object {
        val EMPTY_USER = User(0, "", "", "", "")
    }
}