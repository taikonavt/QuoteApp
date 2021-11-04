package com.example.coreapi.network

interface NetworkProvider {

    fun getMoexApi(): MoexApi
}