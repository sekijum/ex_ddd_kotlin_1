package bondagehub.domain.exception

/**
 * ドメインモデルの更新に失敗した場合に発生する例外。
 */
class UpdateFailedException(
    value: String,
    override val message: String = "$value update failure.",
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "update_failure_error"
}
