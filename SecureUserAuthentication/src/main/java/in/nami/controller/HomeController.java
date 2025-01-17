package in.nami.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/session-check")
    public String sessionCheck() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            return "User is logged in: " + auth.getName();
        } else {
            return "No user is logged in.";
        }
    }
    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            model.addAttribute("username", auth.getName());
        }
        return "home"; 
    }
    @GetMapping("/profile")
    public String profilePage() {
    	return "profile";
    }
    @GetMapping("/settings")
    public String settingsPage() {
    	return "settings";
    }
}

