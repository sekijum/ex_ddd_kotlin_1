package bondagehub.presentation.controller.admin.post

import bondagehub.application.service.admin.post.PostService
import bondagehub.application.service.admin.post.dto.PostDTO
import bondagehub.presentation.controller.admin.post.resource.PostResponse
import io.swagger.annotations.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

@Api("管理者を管理するAPI", tags = ["posts"])
@RestController
@RequestMapping("/admin/posts")
class PostController(private val postService: PostService) {

    @ApiOperation("ページネーション付きのすべての投稿を取得する")
    @GetMapping("/paginate")
    fun findPageByQuery(pageable: Pageable): Mono<Page<PostResponse>> {

        return postService.findPageByQuery(pageable)
            .map { (dtoList, count) -> Pair(dtoList.map { it.toResponse() }, count) }
            .map { PageableExecutionUtils.getPage(
                    it.first,
                    pageable,
                    LongSupplier { it.second.toLong() })
            }
    }

    private fun PostDTO.toResponse() = PostResponse.from(this)
}
