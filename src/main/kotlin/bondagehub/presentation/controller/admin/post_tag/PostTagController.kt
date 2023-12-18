package bondagehub.presentation.controller.admin.post_tag

import bondagehub.application.service.admin.post_tag.PostTagService
import bondagehub.application.service.admin.post_tag.dto.PostTagDTO
import bondagehub.presentation.controller.admin.post_tag.resource.PostTagResponse
import io.swagger.annotations.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

@Api("管理者を管理するAPI", tags = ["post-tags"])
@RestController
@RequestMapping("/admin/post-tags")
class PostTagController(private val postTagService: PostTagService) {

    @ApiOperation("ページネーション付きのすべての投稿タグを取得する")
    @GetMapping("/paginate")
    fun findPageByQuery(pageable: Pageable): Mono<Page<PostTagResponse>> {

        return postTagService.findPageByQuery(pageable)
            .map { (dtoList, count) -> Pair(dtoList.map { it.toResponse() }, count) }
            .map { PageableExecutionUtils.getPage(
                    it.first,
                    pageable,
                    LongSupplier { it.second.toLong() })
            }
    }

    private fun PostTagDTO.toResponse() = PostTagResponse.from(this)
}
