package uz.nt.telegraphclone.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.nt.telegraphclone.exception.AlreadyExistsException;
import uz.nt.telegraphclone.exception.DataNotFoundException;
import uz.nt.telegraphclone.exception.MyValidationException;
import uz.nt.telegraphclone.exception.WrongPasswordException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<String> dataNotFoundException(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<String> alreadyExistsException(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
    }

    @ExceptionHandler(value = WrongPasswordException.class)
    public ResponseEntity<String> wrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = MyValidationException.class)
    public ResponseEntity<String> myValidationException(MyValidationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

//    For MVC ExceptionHandler

//    @ExceptionHandler(value = AlreadyExistsException.class)
//    public String mvcAlreadyExistsException(AlreadyExistsException e, Model model) {
//        model.addAttribute("message", e.getMessage());
//        return "auth/sign-up";
//    }

//    @ExceptionHandler(value = WrongPasswordException.class)
//    public String mvcWrongPasswordException(WrongPasswordException e, Model model) {
//        model.addAttribute("message", e.getMessage());
//        return "auth/sign-in";
//    }

}
