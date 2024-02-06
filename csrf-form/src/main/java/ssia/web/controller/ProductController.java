//: ssia.web.controller.ProductController.java


package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/products")
class ProductController {

    @PostMapping("/add")
    public String add(@RequestParam String name, Model model) {

        log.info(">>> Adding product : {} ", name);

        model.addAttribute("itemAdded",
                ">>> The latest added item is %s".formatted(name));

        return "main.html";
    }

}///:~