package softLaunch.domain;

import softLaunch.domain.WhiteList;

import java.util.List;

public class RequestWrapper {

    private List<Response> whiteLists;

    public RequestWrapper(List<Response> whiteLists) {
        this.whiteLists = whiteLists;
    }

    public RequestWrapper() {
    }

    public List<Response> getWhiteLists() {
        return whiteLists;
    }

    public void setWhiteLists(List<Response> whiteLists) {
        this.whiteLists = whiteLists;
    }
}
