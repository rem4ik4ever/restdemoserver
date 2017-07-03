package kg.ksucta.dkfai.repositories;


import kg.ksucta.dkfai.Models.LockerSession;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LockerSessionRepository extends Repository<LockerSession, String> {

    void delete(LockerSession delete);

    List<LockerSession> findAll();

    LockerSession save(LockerSession saved);

    Optional<LockerSession> findOne(String id);

}