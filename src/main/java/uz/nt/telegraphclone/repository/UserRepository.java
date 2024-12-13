package uz.nt.telegraphclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nt.telegraphclone.domain.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

//    @Transactional
//    @Modifying
//    @Query("update users u set u.isBlock=:block where u.id=:id")
//    void blockUser(@Param("block") boolean block, @Param("id") UUID id);

}
