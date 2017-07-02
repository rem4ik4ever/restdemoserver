package kg.ksucta.dkfai.repositories;

import kg.ksucta.dkfai.Models.LockerSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by kimrem on 2017-07-02.
 */
public interface LockerSessionRepository extends MongoRepository<LockerSession, String> {
    public LockerSession findById(String Id);
//    public List<LockerSession> findUserSessions(String userId);
}