package e_commerce.controller;

import e_commerce.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHandler(){
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Hello World");
       return apiResponse;
    }
}
