package uz.nt.telegraphclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.nt.telegraphclone.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class MvcUserController {

    private final UserService userService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("userList", userService.findOnlyUserList());
        return "user/user";
    }

    @PostMapping("/block/{id}")
    public String userBlock(Model model, @PathVariable UUID id) {
        userService.blockUnblockUser(id);
        model.addAttribute("userList", userService.findOnlyUserList());
        return "user/user";
    }


}
