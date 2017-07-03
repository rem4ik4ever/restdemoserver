package kg.ksucta.dkfai.errors;

public class PhoneLockerNotFoundException extends RuntimeException {

    public PhoneLockerNotFoundException(String id) {
        super(String.format("No Phone Locker entry found with id: <%s>", id));
    }
}