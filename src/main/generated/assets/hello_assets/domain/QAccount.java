package assets.hello_assets.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 1114772758L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> accountNumber = createNumber("accountNumber", Integer.class);

    public final EnumPath<AccountType> accountType = createEnum("accountType", AccountType.class);

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final QCustomer customer;

    public final ListPath<Transaction, QTransaction> fromAccountList = this.<Transaction, QTransaction>createList("fromAccountList", Transaction.class, QTransaction.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Float> interestRate = createNumber("interestRate", Float.class);

    public final NumberPath<Integer> password = createNumber("password", Integer.class);

    public final NumberPath<Integer> termMonth = createNumber("termMonth", Integer.class);

    public final ListPath<Transaction, QTransaction> toAccountList = this.<Transaction, QTransaction>createList("toAccountList", Transaction.class, QTransaction.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

