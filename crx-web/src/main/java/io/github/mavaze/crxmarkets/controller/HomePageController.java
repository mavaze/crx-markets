package io.github.mavaze.crxmarkets.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * I won't be having my web and api in one application. I would rather split them into two applications.
 * One specifically serving web content and other providing backend api functionality. This way we can protect
 * api endpoints independent of web access with different authorization. 2 different gateways would do.
 * Plus we can keep build and deployment for both, independent of each other.
 * For simplicity I am adding both webcontroller and apicontroller in same application.
 */

@Slf4j
@Controller
public class HomePageController {

    @RequestMapping("/")
    public String homepage() {
        log.info("User accesses home page. Returning 'index.html'");
        return "index";
    }
}
