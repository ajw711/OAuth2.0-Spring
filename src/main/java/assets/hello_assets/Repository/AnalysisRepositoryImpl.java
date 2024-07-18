package assets.hello_assets.Repository;

import static assets.hello_assets.domain.QAccount.account;
import static assets.hello_assets.domain.QTransaction.transaction;

import assets.hello_assets.domain.Analysis;
import assets.hello_assets.domain.TransactionType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AnalysisRepositoryImpl implements AnalysisRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;





    @Override
    public Long getDepositCount(String customerName) {
        return jpaQueryFactory.select(transaction.count())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.DEPOSIT)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
    }

    @Override
    public Long getTransferCount(String customerName) {

        return jpaQueryFactory.select(transaction.count())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.REMITTANCE)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
    }

    @Override
    public Integer getTotalDeposit(String customerName) {

        Integer TotalDeposit=jpaQueryFactory
                .select(transaction.amount.sum())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.DEPOSIT)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return TotalDeposit !=null ? TotalDeposit : 0;

    }

    @Override
    public Integer getTotalTransfer(String customerName) {

        Integer TotalTransfer=jpaQueryFactory
                .select(transaction.amount.sum())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.REMITTANCE)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return TotalTransfer !=null ? TotalTransfer : 0;
    }

    @Override
    public Integer getMaxDeposit(String customerName) {
        Integer MaxDeposit= jpaQueryFactory
                .select(transaction.amount.max())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.DEPOSIT)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();

        return MaxDeposit !=null ? MaxDeposit : 0;

    }

    @Override
    public Integer getMinDeposit(String customerName) {
        Integer MinDeposit=jpaQueryFactory
                .select(transaction.amount.min())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.DEPOSIT)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();

        return MinDeposit !=null ? MinDeposit : 0;
    }

    @Override
    public Integer getMaxTransfer(String customerName) {
            Integer MaxTransfer=jpaQueryFactory
                .select(transaction.amount.max())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.REMITTANCE)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return MaxTransfer !=null ? MaxTransfer : 0;
    }

    @Override
    public Integer getMinTransfer(String customerName) {
        Integer MinTransfer=jpaQueryFactory
                .select(transaction.amount.min())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.REMITTANCE)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return MinTransfer !=null ? MinTransfer : 0;
    }

    @Override
    public Double getAvgDeposit(String customerName) {
        Double AvgDeposit=jpaQueryFactory
                .select(transaction.amount.avg())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.DEPOSIT)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return AvgDeposit !=null ? AvgDeposit : 0;
    }

    @Override
    public Double getAvgTransfer(String customerName) {
        Double AvgTransfer=jpaQueryFactory
                .select(transaction.amount.avg())
                .from(transaction)
                .join(transaction.toAccount, account)
                .where(transaction.transactionType.eq(TransactionType.REMITTANCE)
                        .and(account.customer.customerName.eq(customerName)))
                .fetchOne();
        return AvgTransfer !=null ? AvgTransfer : 0;
    }
}
