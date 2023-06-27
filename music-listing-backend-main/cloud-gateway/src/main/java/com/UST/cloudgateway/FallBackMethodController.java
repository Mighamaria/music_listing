package com.UST.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/authServiceFallBackMethod")
    public String authServiceFallBackMethod(){
        return "Hi user,our service is taking a time.Please wait";
    }
    @GetMapping("/adminServiceFallBackMethod")
    public String adminServiceFallBackMethod(){
        return "Hi user,our service is taking a time.Please wait";
    }
    @GetMapping("/userServiceFallBackMethod")
    public String userServiceFallBackMethod(){

        return "Hi user,our service is taking a time.Please wait";
    }

}
