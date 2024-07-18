package assets.hello_assets.Controller;

import assets.hello_assets.JWT.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;



//    @PostMapping("/login")
//    public ResponseEntity<TokenDTO> login(@ResponseBody LoginRequset loginRequset, HttpServletResponse response){
//
//    }


}
