package bondagehub.domain.exception

/**
 * 無効なリクエストを受けてドメインモデルへの変換に失敗した場合に発生する例外。
 */
class InvalidRequestException(
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "invalid_request_error"
}
