/**
 * Created by chestnov.v on 15.08.2017.
 */
public class MsgToGM extends Msg{

    public MsgToGM(Address from, Address to) {
        super(from, to);
    }

    @Override
    void exec(Abonent abonent) {
        if (abonent instanceof GameMechanics){
            GameMechanics gm = (GameMechanics) abonent;

        }
    }
}
