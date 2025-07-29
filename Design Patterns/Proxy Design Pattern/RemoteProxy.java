interface IDataService {
    String fetchData();
    void connect();
    void disconnect();
    String getStatus();
}

// Real subject
class RealDataService implements IDataService {
    private boolean connected = false;

    public RealDataService() {
        System.out.println("[RealDataService] Initialized (simulating remote setup)");
    }

    @Override
    public void connect() {
        connected = true;
        System.out.println("[RealDataService] Connected to remote server.");
    }

    @Override
    public void disconnect() {
        connected = false;
        System.out.println("[RealDataService] Disconnected from remote server.");
    }

    @Override
    public String fetchData() {
        if (!connected) {
            return "[RealDataService] ERROR: Not connected.";
        }
        return "[RealDataService] Data from server.";
    }

    @Override
    public String getStatus() {
        return connected ? "[RealDataService] Status: Connected" : "[RealDataService] Status: Disconnected";
    }
}

// Remote Proxy
class DataServiceProxy implements IDataService {
    private final RealDataService realService;

    public DataServiceProxy() {
        realService = new RealDataService();
    }

    @Override
    public void connect() {
        System.out.println("[DataServiceProxy] Proxy handling connection...");
        realService.connect();
    }

    @Override
    public void disconnect() {
        System.out.println("[DataServiceProxy] Proxy handling disconnection...");
        realService.disconnect();
    }

    @Override
    public String fetchData() {
        System.out.println("[DataServiceProxy] Proxy fetching data...");
        return realService.fetchData();
    }

    @Override
    public String getStatus() {
        System.out.println("[DataServiceProxy] Checking status through proxy...");
        return realService.getStatus();
    }
}

// Client
public class RemoteProxy {
    public static void main(String[] args) {
        IDataService dataService = new DataServiceProxy();

        dataService.connect();
        System.out.println(dataService.fetchData());
        System.out.println(dataService.getStatus());

        dataService.disconnect();
        System.out.println(dataService.fetchData());
    }
}
