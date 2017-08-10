import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chestnov.v on 01.08.2017.
 */
public class FrontEnd extends HttpServlet implements Runnable, Abonent{
    private Address address;
    private Map<String, UserSession > sessionIdToUserS = new HashMap<>();
    private MessageSystem ms;

    public FrontEnd(MessageSystem ms) {
        this.ms = ms;
        address = new Address();
        ms.addService(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String sessionId = req.getSession().getId();
        UserSession userSession = sessionIdToUserS.get(sessionId);
        Map<String, Object> root = new HashMap<>();
        if (userSession == null){
            return;
        }
        if (userSession.getUserId()==null) {
            root.put("userId", "Ждите авторизации");
            resp.getWriter().println(PageGenerator.getInstance().getPage("auth.html", root));
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        root.put("userId", userSession.getUserId());
        resp.getWriter().println(PageGenerator.getInstance().getPage("auth.html", root));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/auth")) {
            String sessionId = req.getSession().getId();
            String name = req.getParameter("login");
            UserSession userSession = new UserSession(name, sessionId, ms.getAddressService());
            if (!sessionIdToUserS.containsKey(sessionId)){
                sessionIdToUserS.put(sessionId, userSession);
                ms.sendMessage(new MsgToAS(address, userSession.getAccountService(), name, sessionId));
            }

            doGet(req, resp);
        }
    }

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

    @Override
    public Address getAddress() {
        return address;
    }

    public void setId(String sessionId, Long userId){
        UserSession userSession = sessionIdToUserS.get(sessionId);
        if (userSession == null){
            System.out.append("Can't find user session for: ").append(sessionId);
            return;
        }
        userSession.setUserId(userId);
    }
}
