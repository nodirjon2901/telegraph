//package uz.nt.telegraphclone.restController;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import uz.nt.telegraphclone.domain.dto.request.UserCreateDTO;
//import uz.nt.telegraphclone.domain.dto.response.UserResponseDTO;
//import uz.nt.telegraphclone.service.UserService;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/user")
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/findAll")
//    public ResponseEntity<List<UserResponseDTO>> findAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
//        return ResponseEntity.ok(userService.findById(id));
//    }
//
//    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<UserResponseDTO> update(
//            @RequestBody UserCreateDTO userCreateDTO,
//            @PathVariable UUID id
//    ) {
//        return ResponseEntity.ok(userService.update(userCreateDTO, id));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable UUID id) {
//        userService.delete(id);
//        return ResponseEntity.status(HttpStatus.valueOf(204)).build();
//    }
//
//    @PatchMapping("/block/{id}")
//    public ResponseEntity<String> block(@PathVariable UUID id) {
//        userService.blockUser(id);
//        return ResponseEntity.status(HttpStatus.valueOf(205)).build();
//    }
//
//}
