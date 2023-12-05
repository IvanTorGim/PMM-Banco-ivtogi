package com.ivtogi.banco_ivtogi.adapter

import com.ivtogi.banco_ivtogi.pojo.Cuenta

interface OnClickAccountListener {
    fun onclick(account: Cuenta)
}