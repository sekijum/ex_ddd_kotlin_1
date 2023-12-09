package bondagehub.domain.exception

/**
 * ドメインモデルが存在しない場合に発生する例外。
 */
class NotFoundException(
    value: String,
    override val message: String = "$value not found.",
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "not_found_error"
}
