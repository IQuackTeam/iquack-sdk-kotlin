package ru.iquack.sdk.core.caller

internal interface Caller {
    suspend fun <T> get(url: String, deserialize: (String) -> T): Result<T>
    suspend fun <T> post(url: String, body: Any? = null, deserialize: (String) -> T): Result<T>
    suspend fun <T> put(url: String, body: Any? = null, deserialize: (String) -> T): Result<T>
    suspend fun <T> patch(url: String, body: Any? = null, deserialize: (String) -> T): Result<T>
    suspend fun <T> delete(url: String, deserialize: (String) -> T): Result<T>
}