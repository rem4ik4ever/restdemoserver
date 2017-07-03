package kg.ksucta.dkfai.interfaces;

import kg.ksucta.dkfai.Models.LockerSession;

import java.util.List;
import java.util.Optional;

/**
 * Created by kimrem on 2017-07-03.
 */
public interface LockerSessionInterface {

    LockerSession create(LockerSession todo);

    LockerSession delete(String id);

    List<LockerSession> findAll();

    LockerSession findById(String id);

    LockerSession update(LockerSession todo);


}
