package uz.nt.telegraphclone.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.nt.telegraphclone.domain.dto.request.PostCreateDTO;
import uz.nt.telegraphclone.domain.dto.response.PostResponseDTO;
import uz.nt.telegraphclone.domain.entity.PostEntity;
import uz.nt.telegraphclone.exception.AlreadyExistsException;
import uz.nt.telegraphclone.exception.DataNotFoundException;
import uz.nt.telegraphclone.repository.PostRepository;
import uz.nt.telegraphclone.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements BaseService<PostResponseDTO, PostCreateDTO> {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(PostCreateDTO postCreateDTO) {
        if (isExistPost(postCreateDTO.getTitle(), postCreateDTO.getOwnerId())) {
            throw new AlreadyExistsException("Post already exists with this title in your post lis");
        }
        PostEntity postEntity = modelMapper.map(postCreateDTO, PostEntity.class);
        postEntity.setOwner(userRepository.findById(postCreateDTO.getOwnerId()).get());
        postRepository.save(postEntity);
    }

    @Override
    public void delete(UUID id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponseDTO update(PostCreateDTO postCreateDTO, UUID id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Post does not found with this id"));
        postEntity.setAuthor(postCreateDTO.getAuthor());
        postEntity.setTitle(postCreateDTO.getTitle());
        postEntity.setContent(postCreateDTO.getContent());
        PostResponseDTO responseDTO = modelMapper.map(postRepository.save(postEntity), PostResponseDTO.class);
        responseDTO.setOwnerId(postEntity.getOwner().getId());
        return responseDTO;
    }

    @Override
    public PostResponseDTO findById(UUID id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Post does not found with this id"));
        PostResponseDTO responseDTO = modelMapper.map(postEntity, PostResponseDTO.class);
        responseDTO.setOwnerId(postEntity.getOwner().getId());
        return responseDTO;
    }

    @Override
    public List<PostResponseDTO> findAll() {
        return postRepository.findAll().stream()
                .map(postEntity -> {
                    PostResponseDTO responseDTO = modelMapper.map(postEntity, PostResponseDTO.class);
                    responseDTO.setOwnerId(postEntity.getOwner().getId());
                    return responseDTO;
                }).collect(Collectors.toList());
    }

    public List<PostResponseDTO> findUserPostList(UUID id) {
        return postRepository.findByOwnerId(id).stream()
                .map(postEntity -> {
                    PostResponseDTO responseDTO = modelMapper.map(postEntity, PostResponseDTO.class);
                    responseDTO.setOwnerId(postEntity.getOwner().getId());
                    return responseDTO;
                }).collect(Collectors.toList());
    }

    private boolean isExistPost(String title, UUID ownerId) {
        return findUserPostList(ownerId).stream()
                .anyMatch(postResponseDTO -> postResponseDTO.getTitle().equals(title));
    }
}
