package ru.kekulta.listapp.features.list.data

import ru.kekulta.listapp.features.list.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}