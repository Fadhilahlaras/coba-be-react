package coba.daily.you.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InputDTO {
    private Integer id;
    private String name;
    private String pbirth;
    private String bdate;
    private String address;
    private String religion;
    private String email;
    private String note;
    private String pictureUrl;

}
