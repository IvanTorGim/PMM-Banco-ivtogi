package com.ivtogi.banco_ivtogi.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivtogi.banco_ivtogi.dao.CajeroDAO
import com.ivtogi.banco_ivtogi.entities.CajeroEntity

@Database(entities = arrayOf(CajeroEntity::class), version = 1)
abstract class BancoDatabase : RoomDatabase() {
    abstract fun cajeroDao(): CajeroDAO
}