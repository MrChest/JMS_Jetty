import java.util.HashMap;
import java.util.Map;

/**
 * Created by chestnov.v on 09.08.2017.
 */
public class AccountService implements Abonent, Runnable {
    private Address address;
    private MessageSystem ms;

    private Map<String, Long> fakeAccounter = new HashMap<>();

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true){
            try {
                ms.execForAbonent(this);
                Thread.sleep(10);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public AccountService(MessageSystem ms) {
        this.ms = ms;
        this.address = new Address();
        ms.getAddressService().setAccountService(address);
        ms.addService(this);

        fakeAccounter.put("MrChe", 1L);
        fakeAccounter.put("MrSen", 2L);
    }

    public Long getUserId(String name){
        try {
            Thread.sleep(5000);

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return fakeAccounter.get(name);
    }

    public MessageSystem getMs() {
        return ms;
    }
}
