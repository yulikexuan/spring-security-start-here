//: ssia.web.controller.MainController.java


package ssia.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
class MainController {

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("itemAdded", "");
        return "main.html";
    }

}///:~