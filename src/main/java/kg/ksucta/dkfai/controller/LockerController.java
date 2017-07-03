package kg.ksucta.dkfai.controller;

import kg.ksucta.dkfai.Models.LockerSession;
import kg.ksucta.dkfai.Models.PhoneLocker;
import kg.ksucta.dkfai.interfaces.LockerSessionInterface;
import kg.ksucta.dkfai.interfaces.PhoneLockerInterface;
import kg.ksucta.dkfai.repositories.LockerSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/locker")
public class LockerController {
    private final LockerSessionInterface lockerSessionService;
    private final PhoneLockerInterface phoneLockerService;

    @Autowired
    LockerController(LockerSessionInterface sessionService, PhoneLockerInterface lockerService){
        this.lockerSessionService = sessionService;
        this.phoneLockerService = lockerService;
    }

    List<PhoneLocker> lockers = new ArrayList<>();
    List<LockerSession> lockerSessions = new ArrayList<>();

    HashMap<Number, LockerSession> lockersSessionHashMap = new HashMap<>();
    HashMap<Number, List<LockerSession>> lockerSessionsMap = new HashMap<>();

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<PhoneLocker> getLockers() {
        lockers = phoneLockerService.findAll();
        return lockers;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    @RequestMapping(path = "/{idx}/occupy", method = RequestMethod.PUT)
    @ResponseBody
    public LockerSession occupy(@PathVariable String idx, @RequestBody LockerSession inputSession) {
        PhoneLocker locker = phoneLockerService.findById(idx);
        locker.isOpen = false;
        LockerSession session = new LockerSession(inputSession.timeStarted, inputSession.userName);

        session.setPhoneLocker(locker);
        locker.setCurrentSession(session);

        LockerSession savedSession = lockerSessionService.create(session);
        phoneLockerService.update(locker.id, locker);

        return savedSession;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
    @RequestMapping(path = "/{idx}/unlock/{sessionId}", method = RequestMethod.PUT)
    @ResponseBody
    public LockerSession unlock(@PathVariable String idx, @PathVariable String sessionId, @RequestBody LockerSession inputSession) {
        PhoneLocker locker = phoneLockerService.findById(idx);
        locker.isOpen = true;

        locker = phoneLockerService.update(locker.id, locker);
        phoneLockerService.unsetCurrentSession(locker.id);

        LockerSession session = lockerSessionService.findById(sessionId);
        session.timeEnded = inputSession.timeEnded;
        session.cost = inputSession.cost;

        LockerSession updatedSession = lockerSessionService.update(session);
        return updatedSession;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @RequestMapping(path = "/{idx}/sessions", method = RequestMethod.GET)
    @ResponseBody
    public List<LockerSession> getLockerSesssions(@PathVariable String idx) {
        PhoneLocker locker = phoneLockerService.findById(idx);

        List<LockerSession> sessions = locker.getLockerSessions();
        System.out.println("Getting locker sessions " + sessions);
        return sessions;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @RequestMapping(path = "/create-lockers", method = RequestMethod.GET)
    @ResponseBody
    public String seedLockers() {
        for (int i = 0; i < 12; ++i){
            phoneLockerService.create(new PhoneLocker(true));
        }
        return "Seeded lockers";
    }


}