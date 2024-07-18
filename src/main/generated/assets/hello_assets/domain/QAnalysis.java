package assets.hello_assets.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnalysis is a Querydsl query type for Analysis
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnalysis extends EntityPathBase<Analysis> {

    private static final long serialVersionUID = 1310917907L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnalysis analysis = new QAnalysis("analysis");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Double> avgDeposit = createNumber("avgDeposit", Double.class);

    public final NumberPath<Double> avgTransfer = createNumber("avgTransfer", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final QCustomer customer;

    public final NumberPath<Long> depositCount = createNumber("depositCount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxDeposit = createNumber("maxDeposit", Integer.class);

    public final NumberPath<Integer> maxTransfer = createNumber("maxTransfer", Integer.class);

    public final NumberPath<Integer> minDeposit = createNumber("minDeposit", Integer.class);

    public final NumberPath<Integer> minTransfer = createNumber("minTransfer", Integer.class);

    public final NumberPath<Integer> totalDeposit = createNumber("totalDeposit", Integer.class);

    public final NumberPath<Integer> totalTransfer = createNumber("totalTransfer", Integer.class);

    public final NumberPath<Long> transferCount = createNumber("transferCount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QAnalysis(String variable) {
        this(Analysis.class, forVariable(variable), INITS);
    }

    public QAnalysis(Path<? extends Analysis> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnalysis(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnalysis(PathMetadata metadata, PathInits inits) {
        this(Analysis.class, metadata, inits);
    }

    public QAnalysis(Class<? extends Analysis> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

