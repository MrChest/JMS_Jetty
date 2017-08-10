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
        //super.doGet(req, resp);
//        FileReader fis = new FileReader("index.html");
//        BufferedReader reader = new BufferedReader(fis);
//
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        while (reader.ready()){
//            writer.print(reader.readLine());
//        }
//
//        reader.close();
//        fis.close();
//
//        writer.close();

        String sessionId = req.getSession().getId();
        UserSession userSession = sessionIdToUserS.get(sessionId);
        if (userSession == null){
            return;
        }
        if (userSession.getUserId()!=null){
            Map<String, Object> root = new HashMap<>();
            root.put("userId", userSession.getUserId());
            resp.getWriter().println(PageGenerator.getInstance().getPage("auth.html", root));
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if (req.getPathInfo().equals("/auth")){
        System.out.println(req.getPathInfo());
            String sessionId = req.getSession().getId();
            String name = req.getParameter("login");
            UserSession userSession = new UserSession(name, sessionId, ms.getAddressService());
            sessionIdToUserS.put(sessionId, userSession);
            ms.sendMessage(new MsgToAS(address, userSession.getAccountService(), name, sessionId));

            resp.setContentType("text/html;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);

            //responseUserPage(response, "authorization started");
        //}
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
