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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalysisService {

        private final AnalysisRepository analysisRepository;
        private final CustomerRepository customerRepository;
        private final FileRepository fileRepository;
        private final FileService fileService;

        public ResponseEntity<AnalysisDTO> analysis(CustomOAuth2User customOAuth2User){

                Customer customer=customerRepository.findByCustomerName(customOAuth2User.getUsername());

                Long depositCount = analysisRepository.getDepositCount(customOAuth2User.getUsername());
                Long transferCount = analysisRepository.getTransferCount(customOAuth2User.getUsername());
                Integer totalDeposit = analysisRepository.getTotalDeposit(customOAuth2User.getUsername());
                Integer totalTransfer = analysisRepository.getTotalTransfer(customOAuth2User.getUsername());
                Integer maxDeposit = analysisRepository.getMaxDeposit(customOAuth2User.getUsername());
                Integer minDeposit = analysisRepository.getMinDeposit(customOAuth2User.getUsername());
                Integer maxTransfer = analysisRepository.getMaxTransfer(customOAuth2User.getUsername());
                Integer minTransfer = analysisRepository.getMinTransfer(customOAuth2User.getUsername());
                Double avgDeposit = analysisRepository.getAvgDeposit(customOAuth2User.getUsername());
                Double avgTransfer = analysisRepository.getAvgTransfer(customOAuth2User.getUsername());

                AnalysisDTO analysisDTO = createAnalysisDTO(depositCount, transferCount, totalDeposit, totalTransfer, maxDeposit, minDeposit, maxTransfer, minTransfer, avgDeposit, avgTransfer);


                Analysis analysis=new Analysis();

                analysis.setDepositCount(depositCount);
                analysis.setTransferCount(transferCount);
                analysis.setTotalDeposit(totalDeposit);
                analysis.setTotalTransfer(totalTransfer);
                analysis.setMaxDeposit(maxDeposit);
                analysis.setMinDeposit(minDeposit);
                analysis.setMaxTransfer(maxTransfer);
                analysis.setMinTransfer(minTransfer);
                analysis.setAvgDeposit(avgDeposit);
                analysis.setAvgTransfer(avgTransfer);
                analysis.setCustomer(customer);

                analysisRepository.save(analysis);

                System.out.println("getId 입니다"+customer.getId());

                String filePath = createFileLoad(customer);

                System.out.println("filePath 입니다: "+filePath);

                analysisDTO.setFilePath(filePath);

                fileService.setFilePath(filePath);

                return ResponseEntity.ok(analysisDTO);

        }


        private AnalysisDTO createAnalysisDTO(Long depositCount, Long transferCount, Integer totalDeposit, Integer totalTransfer, Integer maxDeposit, Integer minDeposit, Integer maxTransfer, Integer minTransfer, Double avgDeposit, Double avgTransfer) {

                AnalysisDTO analysisDTO = new AnalysisDTO();

                analysisDTO.setDepositCount(depositCount);
                analysisDTO.setTransferCount(transferCount);
                analysisDTO.setTotalDeposit(totalDeposit);
                analysisDTO.setTotalTransfer(totalTransfer);
                analysisDTO.setMaxDeposit(maxDeposit);
                analysisDTO.setMinDeposit(minDeposit);
                analysisDTO.setMaxTransfer(maxTransfer);
                analysisDTO.setMinTransfer(minTransfer);
                analysisDTO.setAvgDeposit(avgDeposit);
                analysisDTO.setAvgTransfer(avgTransfer);



                return analysisDTO;
        }

        public String createFileLoad(Customer customer){

                String uploadPath = "C://";
                String savedFileName = "analysis_"+System.currentTimeMillis()+".json";
                String filePath = uploadPath + savedFileName;

                Optional<Analysis> analysis=analysisRepository.findAnalysisByCustomerId(customer.getId());

                JSONObject obj=new JSONObject();



                System.out.println("analysis.get입니다: "+analysis.get());

                obj.put("입금 횟수",analysis.get().getDepositCount());
                obj.put("계좌이체 횟수",analysis.get().getTransferCount());
                obj.put("총입금액",analysis.get().getTotalDeposit());
                obj.put("총이체금액",analysis.get().getTotalTransfer());
                obj.put("최대 입금액",analysis.get().getMaxDeposit());
                obj.put("최소 입금액",analysis.get().getMinDeposit());
                obj.put("최대 이체금액",analysis.get().getMaxTransfer());
                obj.put("최소 이체금액",analysis.get().getMinTransfer());
                obj.put("평균 입금액",analysis.get().getAvgDeposit());
                obj.put("평균 이체금액",analysis.get().getAvgTransfer());

                try{
                        FileWriter fileWriter=new FileWriter(filePath);
                        fileWriter.write(obj.toJSONString());
                        fileWriter.flush();
                        fileWriter.close();

                }catch (IOException e){
                        e.printStackTrace();
                        throw new RuntimeException("Failed to write the file", e);
                }
                System.out.println(obj);

                File file=new File();
                file.setFilePath(filePath);
                file.setFileName(savedFileName);
                file.setAnalysis(analysis.get());
                fileRepository.save(file);



                return filePath;



        }




}







