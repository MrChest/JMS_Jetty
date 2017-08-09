import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by chestnov.v on 01.08.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        MessageSystem ms = new MessageSystem();
        FrontEnd frontEnd = new FrontEnd(ms);
        AccountService accountService = new AccountService(ms);

        new Thread(frontEnd).start();
        new Thread(accountService).start();

        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(frontEnd), "/auth");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setResourceBase("static");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});
        server.setHandler(handlerList);

        server.start();
        server.join();

    }
}
