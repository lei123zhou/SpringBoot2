package com.example.boot21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BMIController {
    @RequestMapping("/bmi")
    @ResponseBody
    public String bmi(float h, float w) {
        float bmi = w / (h * h);
        if (bmi < 18.5) {
            return bmi + "太瘦了";
        }else if(bmi<24){
            return bmi + "正好";
        }else if (bmi<28 ){
            return bmi + "胖了";
        }
        return bmi + "太胖了";
    }
}
