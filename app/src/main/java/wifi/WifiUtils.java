package wifi;

/**
 * Created by mac on 02/01/2017.
 */

import android.util.Log;

import java.math.BigInteger;
import java.net.InetAddress;
import androidutil.ArrayUtils;

public final class WifiUtils {

    private static final double DISTANCE_MHZ_M = 27.55;
    private static final int MIN_RSSI = -100;
    private static final int MAX_RSSI = -55;
    private static final String QUOTE = "\"";

    public static double calculateDistance(int frequency, int level) {
        return Math.pow(10.0, (DISTANCE_MHZ_M - (20 * Math.log10(frequency)) + Math.abs(level)) / 20.0);
    }
    public static String distanceToString(double distance) {
        String dist1="", dist2=Double.toString(distance);
        int i=0;
        Log.i("distance",dist2);
        while (dist2.charAt(i)!='.'){
            dist1+=""+dist2.charAt(i);
            i++;
        }
        dist1+="."+dist2.charAt(i+1);
        Log.i("distance",dist1);
        return dist1;
    }
    public static boolean isOpen(String capabilities){
        String s = capabilities.substring(1,4);
        Log.i("distance",s);
        if (s.equals("WPA")) return false;
        return true;
    }
    public static int calculateSignalLevel(int rssi, int numLevels) {
        if (rssi <= MIN_RSSI) {
            return 0;
        }
        if (rssi >= MAX_RSSI) {
            return numLevels - 1;
        }
        return (rssi - MIN_RSSI) * (numLevels - 1) / (MAX_RSSI - MIN_RSSI);
    }

    public static String convertIpAddress(int ipAddress) {
        try {
            byte[] bytes = BigInteger.valueOf(ipAddress).toByteArray();
            ArrayUtils.reverse(bytes);
            return InetAddress.getByAddress(bytes).getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }

}
