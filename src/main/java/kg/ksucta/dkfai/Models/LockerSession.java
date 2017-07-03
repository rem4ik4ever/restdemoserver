package kg.ksucta.dkfai.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Random;

@Document(collection="locker_sessions")
public class LockerSession {

    @Id
    public String id;

    public Date timeStarted;
    public Date timeEnded;
    public String userName;
    public String cost;
    public int pin;

    @DBRef
    @Field("locker")
    private PhoneLocker phoneLocker;

    public LockerSession() {
        Random random = new Random();
        this.pin = random.nextInt(9999 - 1000 + 1) + 1000;
    };

    public LockerSession(Date timeStarted, String userName){
        this.timeStarted = timeStarted;
        this.userName = userName;
        Random random = new Random();
        this.pin = random.nextInt(9999 - 1000 + 1) + 1000;
    }

    public String getId(){
        return id;
    }

    public String getUsername(){
        return userName;
    }

    public void setUsername(String name){
        this.userName = name;
    }

    public void setPhoneLocker(PhoneLocker locker){
        this.phoneLocker = locker;
    }
}

