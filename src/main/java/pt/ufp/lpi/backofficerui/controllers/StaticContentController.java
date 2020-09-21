package pt.ufp.lpi.backofficerui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StaticContentController {

    //endpoint for getting a web page
    @GetMapping("/index")
    public String getIndexPage(Map<String,Object> model){
        //change the value of title into thymeleaf template
        model.put("title","Home");
        //return the page index.html existing into the folder /resources/templates
        return "index";
    }

    @GetMapping("/work")
    public String getWorksPage(Map<String,Object> model){
        //change the value of title into thymeleaf template
        model.put("title","Work");
        //return the page index.html existing into the folder /resources/templates
        return "work";
    }

    @GetMapping("/tag")
    public String getTagsPage(Map<String,Object> model){
        //change the value of title into thymeleaf template
        model.put("title","Tags");
        //return the page index.html existing into the folder /resources/templates
        return "tags";
    }

}
