/**
 * Created by chestnov.v on 09.08.2017.
 */
public class UserSession {
    private Address accountService;

    private String name;
    private String sessionId;
    private Long userId;

    public UserSession(String name, String sessionId, AddressService addressService) {
        this.accountService = addressService.getAccountService();
        this.name = name;
        this.sessionId =  sessionId;
    }

    public Address getAccountService() {
        return accountService;
    }

    public String getName() {
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
