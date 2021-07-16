package com.personal.site.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/home")
    public String getHome() {
        return "test successful";
    }

    @GetMapping("/.well-known/pki-validation/CF787205659CE2C272C67A60D2F0EFCD.txt")
    public String getText() {
        return "83876C29A6CE13D27E360EBEEEF5DCF7EDF909C9849E5BA791DCE57D8F0D9EC4\n" +
                "comodoca.com\n" +
                "dcv20210716yuv1";
    }
}
