package myutils;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class VolleyHelper {
    public final static String URL = "http://172.16.4.186:80/volley/greetings.php";

    public static Network network = new BasicNetwork(new HurlStack());
}
