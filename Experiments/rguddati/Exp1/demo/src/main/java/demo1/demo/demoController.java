package demo1.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class demoController {

    @GetMapping("/")
    public String welcome() {

        return "Hello and welcome to ip_107";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {

        return "Hello and welcome to Team ip_107: " + name;
    }
}


