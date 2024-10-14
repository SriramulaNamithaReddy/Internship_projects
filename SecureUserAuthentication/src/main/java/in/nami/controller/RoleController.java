package in.nami.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @GetMapping("/check-roles")
    public String checkUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            StringBuilder roles = new StringBuilder("User roles: ");
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.append(authority.getAuthority()).append(" ");
            }
            return roles.toString().trim();
        }
        return "User is not logged in.";
    }
}
