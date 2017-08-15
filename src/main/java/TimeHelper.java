/**
 * Created by chestnov.v on 15.08.2017.
 */
public class TimeHelper {
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
