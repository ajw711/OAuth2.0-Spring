package assets.hello_assets.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class File extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    private String fileName;

    private String filePath;


}
