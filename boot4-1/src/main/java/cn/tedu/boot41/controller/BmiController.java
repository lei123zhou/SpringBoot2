package cn.tedu.boot41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BmiController {
    @RequestMapping("/bmi")
    public String bmi(float h, float w) {
        float bmi = w / (h * h);
        return "bmi" + bmi;
    }
}
