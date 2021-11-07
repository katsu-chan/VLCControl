package VLCControl;


import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.net.InetAddress;

public class VLCServer {

    private final HttpRequestFactory factory;

    //TODO: fix @SuppressWarnings

    /**
     * URL of base path. Default: host:port
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final GenericUrl baseUrl;
    /**
     * URL of requests path. Default: baseUrl + "requests/"
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final GenericUrl requestsUrl;
    /**
     * URL of status.xml. Default: requestsUrl + "status.xml"
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final GenericUrl statusUrl;

    public VLCServer(InetAddress ip, int port, String password) {
        //TODO: store HttpRequestFactory in static class to minimize RAM usage
        this.factory = new NetHttpTransport().createRequestFactory(new BasicAuthentication("", password));
        this.baseUrl = new GenericUrl("http://" + ip.getHostAddress() + ":" + port + "/");
        this.requestsUrl = this.baseUrl.clone();
        this.requestsUrl.appendRawPath("requests/");
        this.statusUrl = this.requestsUrl.clone();
        this.statusUrl.appendRawPath("status.xml");
    }

    public Status in_play(GenericUrl mrl) {
        GenericUrl url = statusUrl.clone();
        url.put("command", "in_play");
        url.put("input", mrl.build());

        try {
            String response = factory.buildGetRequest(url).execute().parseAsString();
            System.out.print(response);
            return null;
        } catch (IOException e) {
            //TODO: handle IOException
            e.printStackTrace();
            return null;
        }
    }
}
