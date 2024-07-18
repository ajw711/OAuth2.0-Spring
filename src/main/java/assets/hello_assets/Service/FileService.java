package assets.hello_assets.Service;

import assets.hello_assets.DTO.AnalysisDTO;
import assets.hello_assets.DTO.CustomOAuth2User;
import assets.hello_assets.Repository.AnalysisRepository;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.Repository.FileRepository;
import assets.hello_assets.domain.Analysis;
import assets.hello_assets.domain.Customer;
import assets.hello_assets.domain.File;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private String filePath;

    public void setFilePath(String filePath){
        this.filePath=filePath;
    }





    public ResponseEntity<Resource> download() {
        try {
            System.out.println("파일다운로드 확인:"+filePath);
            Path pathObj = Paths.get(filePath);
            Resource resource = new InputStreamResource(Files.newInputStream(pathObj)); // 파일 resource 얻기

            java.io.File file = new java.io.File(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }






}
