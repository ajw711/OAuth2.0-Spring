package assets.hello_assets.Controller;

import assets.hello_assets.DTO.AnalysisDTO;
import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.Service.AnalysisService;
import assets.hello_assets.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/analysis")
    public ResponseEntity<AnalysisDTO> accountAnalysis(@AuthenticationPrincipal CustomOAuth2User customOAuth2User){

        return analysisService.analysis(customOAuth2User);
    }

}
