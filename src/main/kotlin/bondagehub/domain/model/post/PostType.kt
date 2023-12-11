package bondagehub.domain.model.post

import bondagehub.domain.exception.*

enum class PostType(val type: Int, val label: String) {

    Album(1, "アルバム"),
    Video(2, "動画"),
    Image(3, "画像");

    companion object {
//        fun from(status: String): PostType = PostType.values().first { it.name == status }
//        fun from(status: Int): PostType = PostType.values().first { it.status == status }

        operator fun invoke(type: Int) = PostType.values().find { it.type == type }
            ?: throw InvalidRequestException("無効なステータスです。")

        operator fun invoke(type: String) = PostType.values().find { it.name == type }
            ?: throw InvalidRequestException("無効なステータスです。")
    }
}