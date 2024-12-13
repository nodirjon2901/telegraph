package uz.nt.telegraphclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.nt.telegraphclone.domain.dto.request.PostCreateDTO;
import uz.nt.telegraphclone.domain.dto.response.PostResponseDTO;
import uz.nt.telegraphclone.domain.dto.response.UserResponseDTO;
import uz.nt.telegraphclone.domain.entity.enums.UserRole;
import uz.nt.telegraphclone.service.PostService;
import uz.nt.telegraphclone.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class MvcPostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping("/{id}")
    public String findAll(
            @PathVariable UUID id,
            Model model) {
        model.addAttribute("postOwnerId", id);
        model.addAttribute("postList", postService.findAll());
        return "post/post";
    }

    @GetMapping("/my/{id}")
    public String findMyPosts(
            @PathVariable UUID id,
            Model model) {
        model.addAttribute("postOwnerId", id);
        model.addAttribute("postList", postService.findUserPostList(id));
        return "post/user-posts";
    }

    @GetMapping("/create/{id}")
    public String CreatePostPage(
            @PathVariable UUID id,
            Model model
    ) {
        model.addAttribute("ownerId", id);
        return "post/create-post";
    }

    @PostMapping("/create/{id}")
    public String add(
            @ModelAttribute PostCreateDTO postCreateDTO,
            @PathVariable UUID id,
            Model model
    ) {
        postService.save(postCreateDTO);
        model.addAttribute("postList", postService.findAll());
        model.addAttribute("postOwnerId", id);
        return "post/post";
    }

    @GetMapping("/find/{id}/{postOwnerId}")
    public String findById(
            @PathVariable UUID id,
            @PathVariable UUID postOwnerId,
            Model model
    ) {
        PostResponseDTO responseDTO = postService.findById(id);
        model.addAttribute("currentPost", responseDTO);
        model.addAttribute("postOwnerId", postOwnerId);
        return "post/current-post";
    }

    @GetMapping("/update/{id}")
    public String updatePage(
            @PathVariable UUID id,
            Model model
    ) {
        PostResponseDTO oldPost = postService.findById(id);
        model.addAttribute("postId", id);
        model.addAttribute("oldPost", oldPost);
        return "post/update-post";
    }

    @PostMapping("/update/{id}/{postOwnerId}")
    public String update(
            @PathVariable UUID id,
            @PathVariable UUID postOwnerId,
            @ModelAttribute PostCreateDTO createDTO,
            Model model
    ) {
        PostResponseDTO update = postService.update(createDTO, id);
        model.addAttribute("currentPost", update);
        model.addAttribute("postOwnerId", postOwnerId);
        return "post/current-post";
    }

    @PostMapping("/delete/{id}/{postOwnerId}")
    public String delete(
            @PathVariable UUID id,
            @PathVariable UUID postOwnerId,
            Model model
    ) {
        postService.delete(id);
        UserResponseDTO user = userService.findById(postOwnerId);
        if (user.getRole().equals(UserRole.USER)) {
            model.addAttribute("postList", postService.findUserPostList(postOwnerId));
            model.addAttribute("postOwnerId", postOwnerId);
            return "post/user-posts";
        }
        model.addAttribute("postList", postService.findAll());
        model.addAttribute("postOwnerId", postOwnerId);
        return "post/post";
    }

}
