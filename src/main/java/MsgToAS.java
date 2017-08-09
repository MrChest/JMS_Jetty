/**
 * Created by chestnov.v on 09.08.2017.
 */
public class MsgToAS extends Msg {
    private String name;
    private String sessionId;

    public MsgToAS(Address from, Address to, String name, String sessionId) {
        super(from, to);
        this.name = name;
        this.sessionId = sessionId;
    }

    @Override
    void exec(Abonent abonent) {
        if (abonent instanceof AccountService){
            AccountService as = (AccountService) abonent;
            Long id = as.getUserId(name);
            as.getMs().sendMessage(new MsgToFrontEnd(getTo(), getFrom(), id, sessionId));
        }
    }
}
