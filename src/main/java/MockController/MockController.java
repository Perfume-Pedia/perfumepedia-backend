package MockController;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MockController {
    private final ObjectMapper objectMapper;

    public MockController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); // Enable Pretty Printer
    }

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }


    @GetMapping("/api/search")
    public String search(
            @RequestParam(name = "lastid") Long lastId,
            @RequestParam(name = "size") Integer size){
        return "json";
    }
}
