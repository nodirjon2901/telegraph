//package uz.nt.telegraphclone.restController;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import uz.nt.telegraphclone.domain.dto.request.PostCreateDTO;
//import uz.nt.telegraphclone.domain.dto.response.PostResponseDTO;
//import uz.nt.telegraphclone.service.PostService;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/post")
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('USER')")
//public class PostController {
//
//    private final PostService postService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> create(@RequestBody PostCreateDTO postCreateDTO) {
//        postService.save(postCreateDTO);
//        return ResponseEntity.status(HttpStatus.valueOf(201)).build();
//    }
//
//    @GetMapping("/findAll")
//    @PreAuthorize("permitAll()")
//    public ResponseEntity<List<PostResponseDTO>> findAll() {
//        return ResponseEntity.ok(postService.findAll());
//    }
//
//    @GetMapping("/find/{id}")
//    @PreAuthorize("permitAll()")
//    public ResponseEntity<PostResponseDTO> findById(@PathVariable UUID id) {
//        return ResponseEntity.ok(postService.findById(id));
//    }
//
//    @GetMapping("/findUserPosts/{id}")
//    public ResponseEntity<List<PostResponseDTO>> findUserPosts(@PathVariable UUID id) {
//        return ResponseEntity.ok(postService.findUserPostList(id));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    public ResponseEntity<String> delete(@PathVariable UUID id) {
//        postService.delete(id);
//        return ResponseEntity.status(HttpStatus.valueOf(204)).build();
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<PostResponseDTO> update(
//            @RequestBody PostCreateDTO postCreateDTO,
//            @PathVariable UUID id) {
//        return ResponseEntity.ok(postService.update(postCreateDTO, id));
//    }
//
//}
