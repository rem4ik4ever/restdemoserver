package kg.ksucta.dkfai.controller;

import kg.ksucta.dkfai.Models.LockerSession;
import kg.ksucta.dkfai.Models.PhoneLocker;
import kg.ksucta.dkfai.repositories.LockerSessionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/locker")
public class LockerController {
    private LockerSessionRepository repository;
    List<PhoneLocker> lockers = PhoneLocker.initialise();
    List<LockerSession> lockerSessions = new ArrayList<>();

    HashMap<Number, LockerSession> lockersSessionHashMap = new HashMap<>();
    HashMap<Number, List<LockerSession>> lockerSessionsMap = new HashMap<>();

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<PhoneLocker> getLockers() {
        return lockers;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    @RequestMapping(path = "/{idx}/occupy", method = RequestMethod.PUT)
    @ResponseBody
    public LockerSession occupy(@PathVariable Integer idx) {
        if (idx < 0 || idx >= lockers.size()) {
            throw new RecordNotFoundException(idx);
        }
        PhoneLocker locker = lockers.get(idx);
        locker.isOpen = false;
        LockerSession session = new LockerSession(new Date(), "Rem Kim");
        lockersSessionHashMap.put(locker.id, session);

        return session;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    @RequestMapping(path = "/{idx}/unlock", method = RequestMethod.PUT)
    @ResponseBody
    public LockerSession unlock(@PathVariable Integer idx) {
        if (idx < 0 || idx >= lockers.size()) {
            throw new RecordNotFoundException(idx);
        }
        PhoneLocker locker = lockers.get(idx);
        locker.isOpen = false;
        LockerSession session = lockersSessionHashMap.get(locker.id);
        session.timeEnded = new Date();
        session.cost = 15.00;
        if(lockerSessionsMap.containsKey(locker.id)){
            List<LockerSession> lSessions = lockerSessionsMap.get(locker.id);
            lSessions.add(session);
        } else {
            List<LockerSession> lSessions = new ArrayList<>();
            lSessions.add(session);
            lockerSessionsMap.put(locker.id, lSessions);
        }
        return session;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    @RequestMapping(path = "/{idx}/sessions", method = RequestMethod.PUT)
    @ResponseBody
    public List<LockerSession> getLockerSesssions(@PathVariable Integer idx) {
        if (idx < 0 || idx >= lockers.size()) {
            throw new RecordNotFoundException(idx);
        }
        PhoneLocker locker = lockers.get(idx);

        if(lockerSessionsMap.containsKey(locker.id)){
            return lockerSessionsMap.get(locker.id);
        }
        throw new RecordNotFoundException(idx);
    }

}