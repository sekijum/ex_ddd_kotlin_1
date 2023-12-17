package bondagehub.presentation.controller.admin.postcategory

import bondagehub.application.service.admin.postcategory.PostCategoryService
import bondagehub.application.service.admin.postcategory.dto.PostCategoryDTO
import bondagehub.presentation.controller.admin.postcategory.resource.PostCategoryResponse
import io.swagger.annotations.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

@Api("管理者を管理するAPI", tags = ["post-categories"])
@RestController
@RequestMapping("/admin/post-categories")
class PostCategoryController(private val postCategoryService: PostCategoryService) {

    @ApiOperation("ページネーション付きのすべての投稿タグを取得する")
    @GetMapping("/paginate")
    fun findPageByQuery(pageable: Pageable): Mono<Page<PostCategoryResponse>> {

        return postCategoryService.findPageByQuery(pageable)
            .map { (dtoList, count) -> Pair(dtoList.map { it.toResponse() }, count) }
            .map { PageableExecutionUtils.getPage(
                it.first,
                pageable,
                LongSupplier { it.second.toLong() })
            }
    }

    private fun PostCategoryDTO.toResponse() = PostCategoryResponse.from(this)
}
