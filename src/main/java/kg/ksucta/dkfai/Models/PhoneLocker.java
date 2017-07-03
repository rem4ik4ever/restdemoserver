package kg.ksucta.dkfai.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Document(collection="phone_lockers")
public class PhoneLocker {

    @Id
    public String id;
    public Boolean isOpen;

    @DBRef
    @Field("sessions")
    private List<LockerSession> lockerSessions;

    @DBRef
    public LockerSession currentSession;

    public PhoneLocker(){}

    public PhoneLocker(Boolean isOpen){
        this.isOpen = isOpen;
        this.lockerSessions = new ArrayList<>();
    }

    public static List<PhoneLocker> initialise() {
        List<PhoneLocker> names = new ArrayList<>();
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        names.add(new PhoneLocker(true));
        return names;
    }

    public String getId(){
        return id;
    }

    public List<LockerSession> getLockerSessions(){
        return lockerSessions;
    }

    public void addSession(LockerSession session){
        lockerSessions.add(session);
    }

    public LockerSession setCurrentSession(LockerSession session){
        currentSession = session;
        return currentSession;
    }

    public Boolean unsetCurrentSession(){
        currentSession = null;

        return true;
    }



}
