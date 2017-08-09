/**
 * Created by chestnov.v on 09.08.2017.
 */
public class MsgToFrontEnd extends Msg{
    private Long userId;
    private String sessionId;

    public MsgToFrontEnd(Address from, Address to, Long userId, String sessionId) {
        super(from, to);
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @Override
    void exec(Abonent abonent) {
        if (abonent instanceof FrontEnd){
            FrontEnd frontEnd = (FrontEnd) abonent;
            frontEnd.setId(sessionId, userId);
        }
    }
}
