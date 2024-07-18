package assets.hello_assets.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisDTO {

    private long depositCount;

    private long transferCount;

    private int totalDeposit;

    private int totalTransfer;

    private int maxDeposit;

    private int minDeposit;

    private int maxTransfer;

    private int minTransfer;

    private double avgDeposit;

    private double avgTransfer;

    private String filePath;


}
