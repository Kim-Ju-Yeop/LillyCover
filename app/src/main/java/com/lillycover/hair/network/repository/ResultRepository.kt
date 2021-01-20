package com.lillycover.hair.network.repository

import com.lillycover.hair.network.model.request.ResultRequest

interface ResultRepository {
    fun getResult(resultRequest: ResultRequest)
}