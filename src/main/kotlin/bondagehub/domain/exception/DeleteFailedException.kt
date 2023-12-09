package bondagehub.domain.exception

/**
 * ドメインモデルの削除に失敗した場合に発生する例外。
 */
class DeleteFailedException(
    value: String,
    override val message: String = "$value delete failure.",
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "delete_failure_error"
}
