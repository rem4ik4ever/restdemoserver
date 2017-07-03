package kg.ksucta.dkfai.interfaces;

import kg.ksucta.dkfai.Models.LockerSession;
import kg.ksucta.dkfai.Models.PhoneLocker;

import java.util.List;

/**
 * Created by kimrem on 2017-07-03.
 */
public interface PhoneLockerInterface {

    PhoneLocker create(PhoneLocker todo);

    PhoneLocker delete(String id);

    List<PhoneLocker> findAll();

    PhoneLocker findById(String id);

    PhoneLocker update(String id, PhoneLocker locker);

    PhoneLocker addSession(String id, LockerSession session);

    public PhoneLocker unsetCurrentSession(String id);
}
