package coba.daily.you.controller.restapi;


import coba.daily.you.repository.InputRepository;
import coba.daily.you.model.dto.InputDTO;
import coba.daily.you.model.entity.Input;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/upload")
public class ImageAPI {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InputRepository inputRepository;

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public InputDTO saveData(@RequestPart(value= "data", required=true) InputDTO inputDTO, @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
//        Input input = modelMapper.map(inputDTO, Input.class);
//        String userFolderPath = "C:/Users/Lenovo/IMAGE/";
//        Path path = Paths.get(userFolderPath);
//        Path filePath = path.resolve(file.getOriginalFilename());
//        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        input.setFile(file.getOriginalFilename());
//        input = inputRepository.save(input);
//        InputDTO inputDTO1 = mapToDTO(input);
//        return inputDTO1;
//    }
    private InputDTO mapToDTO(Input input) {
        InputDTO inputDTO = modelMapper.map(input, InputDTO.class);
        return inputDTO;
    }
}
