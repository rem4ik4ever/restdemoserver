package kg.ksucta.dkfai.repositories;

import kg.ksucta.dkfai.Models.PhoneLocker;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by kimrem on 2017-07-03.
 */
public interface PhoneLockerRepository extends Repository<PhoneLocker, String>{

    void delete(PhoneLocker delete);

    List<PhoneLocker> findAll();

    PhoneLocker save(PhoneLocker saved);

    Optional<PhoneLocker> findOne(String id);
}
