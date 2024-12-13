package uz.nt.telegraphclone.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.nt.telegraphclone.domain.dto.request.UserCreateDTO;
import uz.nt.telegraphclone.domain.dto.request.UserLoginDTO;
import uz.nt.telegraphclone.domain.dto.response.UserResponseDTO;
import uz.nt.telegraphclone.domain.entity.UserEntity;
import uz.nt.telegraphclone.domain.entity.enums.Permission;
import uz.nt.telegraphclone.domain.entity.enums.UserRole;
import uz.nt.telegraphclone.exception.AlreadyExistsException;
import uz.nt.telegraphclone.exception.DataNotFoundException;
import uz.nt.telegraphclone.exception.MyValidationException;
import uz.nt.telegraphclone.exception.WrongPasswordException;
import uz.nt.telegraphclone.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserResponseDTO, UserCreateDTO> {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserCreateDTO userCreateDTO) {
        if (userRepository.findByUsername(userCreateDTO.getUsername()).isPresent()) {
            throw new AlreadyExistsException("User already exists with this username");
        }
        UserEntity userEntity = modelMapper.map(userCreateDTO, UserEntity.class);
        userEntity.setRole(UserRole.USER);
        userEntity.setIsBlock(false);
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        userRepository.save(userEntity);
    }

    public UserResponseDTO login(UserLoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(() -> new DataNotFoundException("User does not found with this username"));
        if (passwordEncoder.matches(loginDTO.password(), userEntity.getPassword())) {
            return modelMapper.map(userEntity, UserResponseDTO.class);
        }
        throw new WrongPasswordException("Password is wrong! Please try again");
    }

    public UserEntity setPermissions(UserEntity userEntity, Set<String> permissions) {
        Set<Permission> collect = permissions.stream()
                .map(permission -> {
                    Permission permissionEnum = Permission.valueOf(permission.toUpperCase());
                    if (!permissionEnum.getRole().equals(userEntity.getRole().name())) {
                        throw new MyValidationException(String.format("wrong permission included: %s is not part of %s permissions", permission, userEntity.getRole()));
                    }
                    return permissionEnum;
                }).collect(Collectors.toSet());
        userEntity.setPermissions(collect);
        return userEntity;
    }

    public void blockUnblockUser(UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User does not found with this id"));
        if (userEntity.getIsBlock()) {
            userEntity.setIsBlock(false);
            userRepository.save(userEntity);
            return;
        }
        userEntity.setIsBlock(true);
        userRepository.save(userEntity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO update(UserCreateDTO userCreateDTO, UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User does not found with this id"));
        userEntity.setFullName(userCreateDTO.getFullName());
        userEntity.setUsername(userCreateDTO.getUsername());
        userEntity.setPassword(userCreateDTO.getPassword());
        return modelMapper.map(userRepository.save(userEntity), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        UserEntity userEntity = userRepository.findById(id).
                orElseThrow(() -> new DataNotFoundException("User does not found with this id"));
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    public UserResponseDTO findByUserName(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User does not found with this username"));
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> {
                    return modelMapper.map(userEntity, UserResponseDTO.class);
                }).collect(Collectors.toList());
    }

    public List<UserResponseDTO> findOnlyUserList() {
        return findAll().stream().filter(userResponseDTO -> {
            return userResponseDTO.getRole().equals(UserRole.USER);
        }).collect(Collectors.toList());
    }

}
