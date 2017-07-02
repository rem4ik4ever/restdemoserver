package kg.ksucta.dkfai.Models;

import org.springframework.data.annotation.Id;

/**
 * Created by kimrem on 2017-07-02.
 */
public class LockerUser
{

    public int id;
    public String name;
    private static int counter = 0;

    public LockerUser(){}

    public LockerUser(String name){
        this.id = counter++;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }
}
