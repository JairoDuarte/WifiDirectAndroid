package wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapter.ListAdapter;
import androidutil.ArrayUtils;
import javafiles.ImportDialog;
import ma.ac.est_uh2c.www.wifidirect.MainActivity;
import ma.ac.est_uh2c.www.wifidirect.R;
import wifi.model.WifiConnected;
import wifi.model.WifiDetails;

/**
 * Created by mac on 02/01/2017.
 */

public  class WifiReceiver extends BroadcastReceiver {
    private ListAdapter adapter;
    private ListView listViwProvider;
    private WifiManager wifiManager;
    private List<ScanResult> wifiList;
    private MainActivity activity;
    private List<WifiDetails>  wifiDetailsList;

    public WifiReceiver(final MainActivity activity, WifiManager wifiManager) {
        this.activity = activity;
        this.wifiManager = wifiManager;
        this.wifiList = wifiManager.getScanResults();
        listViwProvider = (ListView) activity.findViewById(R.id.list_view_wifi);
        wifiDetailsList = new ArrayList<>();
        listViwProvider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImportDialog action = new ImportDialog(activity, (wifiList.get(position)));
                action.showDialog();
            }
        });

    }

    // This method call when number of wifi connections changed
    public void onReceive(Context c, Intent intent) {

        WifiDetails wifiDetails;
        wifiList = wifiManager.getScanResults();
        WifiInfo info= wifiManager.getConnectionInfo();
        WifiConnected wificonnectwed=null;

        if (!ArrayUtils.isBlank(WifiUtils.convertIpAddress(info.getIpAddress()))){
            wificonnectwed = new WifiConnected(WifiUtils.convertIpAddress(info.getIpAddress()),info.getLinkSpeed(),info.getNetworkId(),info.getRssi());

        }
			/* sorting of wifi provider based on level */
        Collections.sort(wifiList, new Comparator<ScanResult>() {
            @Override
            public int compare(ScanResult lhs, ScanResult rhs) {
                return (lhs.level > rhs.level ? -1
                        : (lhs.level == rhs.level ? 0 : 1));
            }
        });
        wifiDetailsList.clear();

        for (int i = 0; i < wifiList.size(); i++) {

            ScanResult sr = wifiList.get(i);
            String kk=info.getSSID().substring(1,info.getSSID().length()-1);
            String vv= sr.SSID;
            Log.i("blanc",info.getSSID() +"-"+sr.SSID +" * "+kk +" " +kk.equals(vv));

            if (sr.SSID.equals(info.getSSID().substring(1,info.getSSID().length()-1))){
                wifiDetails = new WifiDetails(sr.BSSID,sr.capabilities,sr.level,sr.frequency,sr.SSID,wificonnectwed);
                Log.i("blanc","ee");
            }
            else
                wifiDetails = new WifiDetails(sr.SSID, sr.BSSID, sr.capabilities, sr.frequency, sr.level);

            wifiDetailsList.add(wifiDetails);
        }

        adapter = new ListAdapter(activity, wifiDetailsList);

        listViwProvider.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}