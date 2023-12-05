package com.ivtogi.banco_ivtogi.fragments

import com.ivtogi.banco_ivtogi.pojo.Cuenta

interface AccountListener {
    fun onAccountSelected(account: Cuenta)
}