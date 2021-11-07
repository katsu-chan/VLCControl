package VLCControl;

import com.google.api.client.http.GenericUrl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {

        try {
            VLCServer s = new VLCServer(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), args[3]);
            Status st = s.in_play(new GenericUrl(args[2]));
            System.out.print(st.toString());
        } catch (UnknownHostException e) {
            //TODO: handle UnknownHostException
            e.printStackTrace();
        }

    }

}
