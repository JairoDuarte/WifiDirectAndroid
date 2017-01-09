package wifi.model;

/**
 * Created by Jairo Duarte on 04/01/2017.
 */

public class WifiConnected {
    private  int rssi;
    private int networkId;
    private  int linkSpeed;
    private String ipAddress;

    public WifiConnected() {}

    public WifiConnected(String ipAddress, int linkSpeed, int networkId, int rssi) {
        this.ipAddress = ipAddress;
        this.linkSpeed = linkSpeed;
        this.networkId = networkId;
        this.rssi = rssi;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(int linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
