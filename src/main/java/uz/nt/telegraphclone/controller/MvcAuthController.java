package uz.nt.telegraphclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.nt.telegraphclone.domain.dto.request.UserCreateDTO;
import uz.nt.telegraphclone.domain.dto.response.UserResponseDTO;
import uz.nt.telegraphclone.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MvcAuthController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute UserCreateDTO createDTO
    ) {
        userService.save(createDTO);
        return "auth/sign-in";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "auth/sign-in";
    }

    @GetMapping("/go_menu")
    public String menu(Model model, Principal principal) {
        UserResponseDTO responseDTO = userService.findByUserName(principal.getName());
        if (responseDTO.getIsBlock()){
            model.addAttribute("loginUser", responseDTO);
            return "user/block";
        }
        model.addAttribute("loginUser", responseDTO);
        return "menu";
    }

    @GetMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/sign-in";
    }


}
