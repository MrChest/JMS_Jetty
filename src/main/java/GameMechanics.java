/**
 * Created by chestnov.v on 15.08.2017.
 */
public class GameMechanics implements Abonent, Runnable{
    private MessageSystem ms;
    private Address address;

    public GameMechanics(MessageSystem ms) {
        this.ms = ms;
        address = new Address();
        ms.getAddressService().setGameMechhanics(address);
        ms.addService(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true){
            ms.execForAbonent(this);
            TimeHelper.sleep( 100);
        }
    }

    public MessageSystem getMs() {
        return ms;
    }
}
