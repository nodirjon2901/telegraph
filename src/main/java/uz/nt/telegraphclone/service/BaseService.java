package uz.nt.telegraphclone.service;

import java.util.List;
import java.util.UUID;

public interface BaseService <RD, CD>{

    void save(CD cd);

    void delete(UUID id);

    RD update(CD cd, UUID id);

    RD findById(UUID id);

    List<RD> findAll();

}
