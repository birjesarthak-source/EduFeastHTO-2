package com.EduFest.controller;
import com.EduFest.repository.UserRepository; import com.EduFest.entity.User; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*; import jakarta.servlet.http.HttpSession;
@Controller public class AuthController {
    private final UserRepository users; private final PasswordEncoder encoder;
    public AuthController(UserRepository u,PasswordEncoder e){users=u;encoder=e;}
    @GetMapping("/login") public String login(){return "login";}
    @PostMapping("/login") public String login(@RequestParam String email,@RequestParam String password,HttpSession session,Model model){
        User u=users.findByEmail(email).orElse(null);
        if(u!=null && encoder.matches(password,u.getPassword())){session.setAttribute("user",u);return "redirect:/";}
        model.addAttribute("error","Invalid email or password"); return "login";
    }
    @GetMapping("/logout") public String logout(HttpSession session){session.invalidate();return "redirect:/login";}
}
