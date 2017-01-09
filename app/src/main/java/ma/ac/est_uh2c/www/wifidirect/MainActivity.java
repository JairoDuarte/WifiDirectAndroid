package ma.ac.est_uh2c.www.wifidirect;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import wifi.WifiReceiver;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button setWifi;
    WifiManager wifiManager;
    WifiReceiver receiverWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);


        Log.i("1", Integer.toString(wifiManager.getWifiState()));
        scaning();



    }

    private void scaning() {

        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        receiverWifi = new WifiReceiver(MainActivity.this,wifiManager);

        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
    }


    @Override
    public void onClick(View arg0) {

    }

    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }


}