package com.ivtogi.banco_ivtogi.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cajeros")
data class CajeroEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var direccion: String,
    var latitud: Double,
    var longitud: Double,
    var zoom: String
)
