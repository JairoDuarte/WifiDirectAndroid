package wifi.model;

import android.net.wifi.WifiInfo;

import java.util.List;

/**
 * Created by mac on 02/01/2017.
 */

public class WifiDetails {

    private  String SSID;
    private  String BSSID;
    private  String capabilities;
    private  int primaryFrequency;
    private  int level;
    private  WifiConnected wifiConnected;
    public String getSSID() {
        return SSID;
    }

    public WifiDetails(String BSSID, String capabilities, int level, int primaryFrequency, String SSID, WifiConnected wifiConnected) {
        this.BSSID = BSSID;
        this.capabilities = capabilities;
        this.level = level;
        this.primaryFrequency = primaryFrequency;
        this.SSID = SSID;
        this.wifiConnected = wifiConnected;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getPrimaryFrequency() {
        return primaryFrequency;
    }

    public void setPrimaryFrequency(int primaryFrequency) {
        this.primaryFrequency = primaryFrequency;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public WifiDetails(String SSID, String BSSID, String capabilities, int primaryFrequency, int level) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.capabilities = capabilities;
        this.primaryFrequency = primaryFrequency;
        this.level = level;
        wifiConnected =null;
    }

    public  WifiDetails(){}

    public WifiConnected getWifiConnected() {
        return wifiConnected;
    }

    public void setWifiConnected(WifiConnected wifiConnected) {
        this.wifiConnected = wifiConnected;
    }
}
