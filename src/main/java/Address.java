import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chestnov.v on 09.08.2017.
 */
public class Address {
    private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentID;

    public Address() {
        this.abonentID = abonentIdCreator.incrementAndGet();
    }

    @Override
    public int hashCode() {
        return abonentID;
    }
}
