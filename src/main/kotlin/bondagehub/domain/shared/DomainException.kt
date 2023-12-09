package bondagehub.domain.shared

class DomainException(
    override val message: String,
    cause: Throwable? = null,
    type: String,
) : RuntimeException(message, cause) {

    val type: String = type
}