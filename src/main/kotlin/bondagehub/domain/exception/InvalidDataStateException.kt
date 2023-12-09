package bondagehub.domain.exception

/**
 * ドメインモデルが無効なデータ状態にある場合に発生する例外。
 */
class InvalidDataStateException(
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "invalid_data_state"
}
