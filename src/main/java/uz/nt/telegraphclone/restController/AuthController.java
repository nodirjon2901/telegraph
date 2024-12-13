//package uz.nt.telegraphclone.restController;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import uz.nt.telegraphclone.domain.dto.request.UserCreateDTO;
//import uz.nt.telegraphclone.domain.dto.request.UserLoginDTO;
//import uz.nt.telegraphclone.domain.dto.response.UserResponseDTO;
//import uz.nt.telegraphclone.service.UserService;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserCreateDTO userCreateDTO) {
//        userService.save(userCreateDTO);
//        return ResponseEntity.status(HttpStatus.valueOf(201)).build();
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginDTO loginDTO) {
//        return ResponseEntity.ok(userService.login(loginDTO));
//    }
//
//}
