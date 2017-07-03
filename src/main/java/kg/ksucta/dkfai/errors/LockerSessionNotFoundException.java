package kg.ksucta.dkfai.errors;

public class LockerSessionNotFoundException extends RuntimeException {

    public LockerSessionNotFoundException(String id) {
        super(String.format("No Locker Session entry found with id: <%s>", id));
    }
}