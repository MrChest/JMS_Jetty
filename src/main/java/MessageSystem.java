import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by chestnov.v on 09.08.2017.
 */
public class MessageSystem {
    private Map<Address, ConcurrentLinkedDeque<Msg>> messages = new HashMap<>();
    private AddressService addressService = new AddressService();

    public void addService(Abonent abonent){
        messages.put(abonent.getAddress(), new ConcurrentLinkedDeque<Msg>());
    }

    public void sendMessage(Msg message){
        Queue<Msg> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    public void execForAbonent(Abonent abonent){
        Queue<Msg> messageQueue = messages.get(abonent.getAddress());
        if (messageQueue==null){
            return;
        }
        while (!messageQueue.isEmpty()){
            Msg message = messageQueue.poll();
            message.exec(abonent);
        }

    }

    public AddressService getAddressService() {
        return addressService;
    }
}
