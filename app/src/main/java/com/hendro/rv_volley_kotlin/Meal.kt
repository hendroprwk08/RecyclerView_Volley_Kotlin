package com.hendro.rv_volley_kotlin

class Meal (id: String?, meal: String?, image: String?) {
    private var id: String
    private var meal: String
    private var image: String

    init {
        this.id = id!!
        this.meal = meal!!
        this.image = image!!
    }

    fun getId(): String? {
        return id
    }

    fun getMeal(): String? {
        return meal
    }

    fun getImage(): String? {
        return image
    }

}