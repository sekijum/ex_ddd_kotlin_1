package bondagehub.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class ApplicationController {
    @GetMapping("/")
    fun root() {
        return
    }
}
