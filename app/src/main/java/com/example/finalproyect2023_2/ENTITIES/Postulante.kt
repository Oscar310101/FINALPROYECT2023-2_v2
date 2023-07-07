package com.example.finalproyect2023_2.ENTITIES

import java.util.*

data class Postulante(
    val id: String,
    val nombre: String,
    val email: String,

){
    constructor() : this("", "", "")
}