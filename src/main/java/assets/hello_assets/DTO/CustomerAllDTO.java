package assets.hello_assets.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerAllDTO {

    private String name;

    private String customerName;

    private String email;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
