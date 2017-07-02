package kg.ksucta.dkfai.Models;

import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.Random;

public class LockerSession {

    @Id
    public int id;

    public Date timeStarted;
    public Date timeEnded;
    public String userName;
    public Number cost;
    public int pin;

    public static int count = 0;

    public LockerSession() {};

    public LockerSession(Date timeStarted, String userName){
        this.id = count++;
        this.timeStarted = timeStarted;
        this.userName = userName;
        Random random = new Random();
        this.pin = random.nextInt(9999 - 1000 + 1) + 1000;
    }


    public void finishSession(Date timeEnded){
        this.timeEnded = timeEnded;

    }


}
