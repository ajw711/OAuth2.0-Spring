package assets.hello_assets.Repository;

import assets.hello_assets.domain.Analysis;

import java.util.Optional;

public interface AnalysisRepositoryCustom {


    Long getDepositCount(String customerName);

    Long getTransferCount(String customerName);

    Integer getTotalDeposit(String customerName);

    Integer getTotalTransfer(String customerName);

    Integer getMaxDeposit(String customerName);

    Integer getMinDeposit(String customerName);

    Integer getMaxTransfer(String customerName);

    Integer getMinTransfer(String customerName);

    Double  getAvgDeposit(String customerName);

    Double getAvgTransfer(String customerName);

}
