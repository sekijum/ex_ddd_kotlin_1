package bondagehub.presentation.controller.admin.member

import bondagehub.application.service.admin.member.MemberService
import bondagehub.application.service.admin.member.dto.MemberDTO
import bondagehub.presentation.controller.admin.member.resource.MemberResponse
import io.swagger.annotations.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

@Api("管理者を管理するAPI", tags = ["members"])
@RestController
@RequestMapping("/admin/members")
class MemberController(private val memberService: MemberService) {

    @ApiOperation("ページネーション付きのすべての会員を取得する")
    @GetMapping("/paginate")
    fun findPageByQuery(pageable: Pageable): Mono<Page<MemberResponse>> {

        return memberService.findPageByQuery(pageable)
            .map { (dtoList, count) -> Pair(dtoList.map { it.toResponse() }, count) }
            .map { PageableExecutionUtils.getPage(
                it.first,
                pageable,
                LongSupplier { it.second.toLong() })
            }
    }

    private fun MemberDTO.toResponse() = MemberResponse.from(this)
}
