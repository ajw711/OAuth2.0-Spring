package assets.hello_assets.Controller;

import assets.hello_assets.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class FileController {

    private final FileService fileService;


    @GetMapping("/analysis/download/")
    public ResponseEntity<Resource> fileDownload(){
        System.out.println("파일 다운로드 호출 확인: ");
        return fileService.download();
    }

}
