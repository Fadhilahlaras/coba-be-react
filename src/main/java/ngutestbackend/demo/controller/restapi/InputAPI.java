package ngutestbackend.demo.controller.restapi;

import ngutestbackend.demo.model.entity.*;
import ngutestbackend.demo.model.dto.InputDTO;
import ngutestbackend.demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Base64;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/input")
@CrossOrigin(origins = "http://localhost:3001")
public class InputAPI {
    @Autowired
    private InputRepository inputRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InputAPI(InputRepository inputRepository, ModelMapper modelMapper) {
        this.inputRepository = inputRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<InputDTO> getList() {
        List<Input> inputList = inputRepository.findAll();
        List<InputDTO> inputDTOList =
                inputList.stream()
                        .map(in -> mapToDTO(in))
                        .collect(Collectors.toList());
        return inputDTOList;
    }

    private InputDTO mapToDTO(Input input) {
        InputDTO inputDTO = modelMapper.map(input, InputDTO.class);
        return inputDTO;
    }

    @GetMapping("/getImage/{id}")
    public String getImage(@PathVariable Integer id) throws IOException {
        Input input = inputRepository.findById(id).get();
        String userFolderPath = "E:/";
//        String userFolderPath = "C:/Users/Lenovo/FullStackTech/ngu-test/src/assets/utils/images/";
        String pathFile = userFolderPath + input.getFile();
        Path paths = Paths.get(pathFile);
        byte[] photo = Files.readAllBytes(paths);
        String encodedString = Base64.getEncoder().encodeToString(photo);

        return encodedString;

    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/save")
    public InputDTO saveData(@RequestPart(value="data", required = true) InputDTO inputDTO, @RequestPart(value="file", required = true) MultipartFile file) throws Exception {
        Input input = modelMapper.map(inputDTO, Input.class);
        String userFolderPath = "C:/Users/lenovo/Images/";
//        String userFolderPath = "C:/Users/Lenovo/FullStackTech/ngu-test/src/assets/utils/images/";
        Path path = Paths.get(userFolderPath);
        Path filePath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        input.setFile(file.getOriginalFilename());
        input = inputRepository.save(input);
        InputDTO inputDTO1 = mapToDTO(input);
        return inputDTO1;
    }



    @DeleteMapping
    public void del(){
        inputRepository.deleteAll();
    }
}
