package softLaunch.domain;

import softLaunch.domain.WhiteList;

import java.util.List;

public class RequestWrapper {

    private List<WhiteList> whiteLists;

    public RequestWrapper(List<WhiteList> whiteLists) {
        this.whiteLists = whiteLists;
    }

    public RequestWrapper() {
    }

    public List<WhiteList> getWhiteLists() {
        return whiteLists;
    }

    public void setWhiteLists(List<WhiteList> whiteLists) {
        this.whiteLists = whiteLists;
    }
}
