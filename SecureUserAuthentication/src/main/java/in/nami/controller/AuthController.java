package in.nami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nami.model.User;
import in.nami.service.UserService;

@Controller
public class AuthController {
	
	private final AuthenticationManager authenticationManager ;

	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            // Create an authentication token
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            
            Authentication auth = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            // Redirect to the home page after successful login
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // return to login page with error
        }
    }

    
}
