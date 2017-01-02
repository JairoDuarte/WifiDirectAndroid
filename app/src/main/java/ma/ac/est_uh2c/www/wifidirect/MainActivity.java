package ma.ac.est_uh2c.www.wifidirect;


import android.net.wifi.WifiInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import adapter.ListAdapter;
import javafiles.ImportDialog;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button setWifi;
    WifiManager wifiManager;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    List<String> listOfProvider;
    ListAdapter adapter;
    ListView listViwProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       listOfProvider = new ArrayList<String>();

		listViwProvider = (ListView) findViewById(R.id.list_view_wifi);
        setWifi = (Button) findViewById(R.id.btn_wifi);
        setWifi.setOnClickListener(this);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);


        Log.i("1", Integer.toString(wifiManager.getWifiState()));

        if (wifiManager.isWifiEnabled() == true) {
            setWifi.setText("ON");
            scaning();
        }

        listViwProvider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                ImportDialog action = new ImportDialog(MainActivity.this, (wifiList.get(position)).toString());
                action.showDialog();
            }
        });

    }

    private void scaning() {

        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        receiverWifi = new WifiReceiver();

        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
    }


    @Override
    public void onClick(View arg0) {

        if (wifiManager.isWifiEnabled() == true) {
            wifiManager.setWifiEnabled(false);
            setWifi.setText("OFF");
            listViwProvider.setVisibility(ListView.GONE);
        }

        else if (wifiManager.isWifiEnabled() == false) {
            setWifi.setText("ON");
            listViwProvider.setVisibility(ListView.VISIBLE);
            scaning();
        }
    }

    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            wifiList = wifiManager.getScanResults();
            WifiInfo info= wifiManager.getConnectionInfo();
            //info.getIpAddress();
            //wifiList.get(1)

			/* sorting of wifi provider based on level */
            Collections.sort(wifiList, new Comparator<ScanResult>() {
                @Override
                public int compare(ScanResult lhs, ScanResult rhs) {
                    return (lhs.level > rhs.level ? -1
                            : (lhs.level == rhs.level ? 0 : 1));
                }
            });
            listOfProvider.clear();
            String providerName;
            for (int i = 0; i < wifiList.size(); i++) {
				/* to get SSID and BSSID of wifi provider*/
                providerName = (wifiList.get(i).SSID).toString()
                        +"\n"+(wifiList.get(i).BSSID).toString();
                listOfProvider.add(providerName);
            }


            adapter = new ListAdapter(MainActivity.this, listOfProvider);

            listViwProvider.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }
}