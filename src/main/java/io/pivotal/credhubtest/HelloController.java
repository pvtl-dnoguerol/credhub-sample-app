package io.pivotal.credhubtest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private Config config;

    public HelloController(Config config) {
        this.config = config;
    }

    @RequestMapping("/")
    public String index() {
        return "username: \"" + config.getUsername() + "\", password: \"" + config.getPassword() + "\"";
    }
}
