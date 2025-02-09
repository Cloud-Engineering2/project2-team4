/*
* 토큰 테스트 후 삭제할 컨트롤러
* */

package showu.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("")
    public HttpStatus test() {
        return HttpStatus.OK;
    }
}
