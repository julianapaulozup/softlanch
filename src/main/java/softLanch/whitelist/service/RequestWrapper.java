package softLanch.client.service;

import java.util.List;

public class RequestWrapper {

    private List<Client> clients;

    public RequestWrapper(List<Client> clients) {
        this.clients = clients;
    }

    public RequestWrapper() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
