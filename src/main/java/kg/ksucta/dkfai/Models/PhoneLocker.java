package kg.ksucta.dkfai.Models;


import java.util.ArrayList;
import java.util.List;

public class PhoneLocker {

    public Boolean isOpen;
    public int id;
    public static int counter = 0;

    public PhoneLocker(){}
    public PhoneLocker(Boolean isOpen){
        this.id = counter++;
        this.isOpen = isOpen;
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

}
