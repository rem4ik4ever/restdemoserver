package kg.ksucta.dkfai.services;

import kg.ksucta.dkfai.Models.LockerSession;
import kg.ksucta.dkfai.Models.PhoneLocker;
import kg.ksucta.dkfai.errors.LockerSessionNotFoundException;
import kg.ksucta.dkfai.interfaces.PhoneLockerInterface;
import kg.ksucta.dkfai.repositories.PhoneLockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by kimrem on 2017-07-03.
 */
@Service
public class PhoneLockerService implements PhoneLockerInterface{
    private final PhoneLockerRepository repository;

    @Autowired
    PhoneLockerService(PhoneLockerRepository repository){
        this.repository = repository;
    }

    @Override
    public PhoneLocker create(PhoneLocker locker) {
        PhoneLocker savedLocker = repository.save(locker);
        return savedLocker;
    }

    @Override
    public PhoneLocker delete(String id) {
        PhoneLocker deleted = findLockerById(id);
        repository.delete(deleted);
        return deleted;
    }

    @Override
    public List<PhoneLocker> findAll() {
        List<PhoneLocker> locker = repository.findAll();
        return locker;
    }

    @Override
    public PhoneLocker findById(String id) {
        PhoneLocker found = findLockerById(id);
        return found;
    }

    @Override
    public PhoneLocker update(String id, PhoneLocker locker) {
        PhoneLocker updated = findLockerById(id);

        updated.isOpen = locker.isOpen;
        if(locker.currentSession != null){
            updated.setCurrentSession(locker.currentSession);
        }
        updated = repository.save(updated);
        return updated;
    }

    @Override
    public PhoneLocker unsetCurrentSession(String id){
        PhoneLocker locker = findLockerById(id);
        locker.unsetCurrentSession();
        return repository.save(locker);
    }

    @Override
    public PhoneLocker addSession(String id, LockerSession session){
        PhoneLocker locker = findLockerById(id);
        locker.addSession(session);
        return repository.save(locker);
    }



    private PhoneLocker findLockerById(String id) {
        Optional<PhoneLocker> result = repository.findOne(id);
        return result.orElseThrow(() -> new LockerSessionNotFoundException(id));
    }
}
