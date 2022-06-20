package sbs.baka.cheetah.edgenuity.api.requests;

import org.jsoup.*;

public interface ResponseHandler {

    void onResponse(Connection.Response response);

}
