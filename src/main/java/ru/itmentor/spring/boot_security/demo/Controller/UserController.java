
package ru.itmentor.spring.boot_security.demo.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.DAO.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;


@Controller
@RequestMapping("/users")
public class UserController {


    private final UserDao userDAO;

    public UserController(UserDao userDao){
        this.userDAO = userDao;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("users", userDAO.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String indexCount(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDAO.indexCount(id));
        return "users/indexCount";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "users/NewUser";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@ModelAttribute("user") User user){
        userDAO.save(user);
        return "redirect:/users/index";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userDAO.indexCount(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userDAO.update(id, user);
        return "redirect:/users/index";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") long id){
        userDAO.delete(id);
        return "redirect:/users/index";
    }

    //Методы для Rest
//    @ResponseBody
//    @GetMapping("/sayHello")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String sayHello (){
//        return "Hello world!";
//    }



}
