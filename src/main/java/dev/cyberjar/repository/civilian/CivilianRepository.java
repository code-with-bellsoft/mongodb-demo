package dev.cyberjar.repository.civilian;

import dev.cyberjar.entity.Civilian;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CivilianRepository extends MongoRepository<Civilian, Long>, CivilianRepositoryCustom {

    Optional<Civilian> findById(long id);

    Optional<Civilian> findByNationalId(String nationalId);

    boolean existsById(long id);

    boolean existsByNationalId(String nationalId);

}