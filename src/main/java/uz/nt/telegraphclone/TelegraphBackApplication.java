package uz.nt.telegraphclone;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.nt.telegraphclone.domain.entity.UserEntity;
import uz.nt.telegraphclone.domain.entity.enums.Permission;
import uz.nt.telegraphclone.domain.entity.enums.UserRole;
import uz.nt.telegraphclone.repository.UserRepository;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class TelegraphBackApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(TelegraphBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("qwer").isEmpty()) {
            UserEntity admin = UserEntity.builder()
                    .fullName("qwer")
                    .username("qwer")
                    .password(passwordEncoder.encode("qwer"))
                    .role(UserRole.ADMIN)
                    .isBlock(false)
                    .permissions(Set.of(Permission.USER_BLOCK, Permission.USER_DELETE))
                    .build();
            userRepository.save(admin);
        }
    }
}
