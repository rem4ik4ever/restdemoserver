package kg.ksucta.dkfai.services;

import kg.ksucta.dkfai.Models.LockerSession;
import kg.ksucta.dkfai.errors.LockerSessionNotFoundException;
import kg.ksucta.dkfai.interfaces.LockerSessionInterface;
import kg.ksucta.dkfai.repositories.LockerSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.util.Optional;

/**
 * Created by kimrem on 2017-07-03.
 */
@Service
final class LockerSessionService implements LockerSessionInterface {
    private final LockerSessionRepository repository;

    @Autowired
    LockerSessionService(LockerSessionRepository repository){
        this.repository = repository;
    }

    @Override
    public LockerSession create(LockerSession todo) {
        LockerSession session = repository.save(todo);
        return session;
    }

    @Override
    public LockerSession delete(String id) {
        LockerSession deleted = findTodoById(id);
        repository.delete(deleted);
        return deleted;
    }

    @Override
    public List<LockerSession> findAll() {
        List<LockerSession> sessions = repository.findAll();
        return sessions;
    }

    @Override
    public LockerSession findById(String id) {
        LockerSession found = findTodoById(id);
        return found;
    }

    @Override
    public LockerSession update(LockerSession todo) {
        LockerSession updated = findTodoById(todo.getId());
        updated.timeEnded = todo.timeEnded;
        updated.cost = todo.cost;
        updated = repository.save(updated);
        return updated;
    }

    private LockerSession findTodoById(String id) {
        Optional<LockerSession> result = repository.findOne(id);
        return result.orElseThrow(() -> new LockerSessionNotFoundException(id));
    }

}
