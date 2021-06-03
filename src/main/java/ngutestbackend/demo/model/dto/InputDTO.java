package ngutestbackend.demo.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InputDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String date;
    private String address;
    private String file;

}
