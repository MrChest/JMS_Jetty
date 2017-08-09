/**
 * Created by chestnov.v on 09.08.2017.
 */
abstract class Msg {
    private Address from;
    private  Address to;

    public Msg(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    abstract void exec(Abonent abonent);
}
