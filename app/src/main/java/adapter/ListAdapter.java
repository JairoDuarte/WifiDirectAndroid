package adapter;

import java.util.List;

import android.app.Activity;
import android.renderscript.Double2;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.mobilemerit.wifi.R;
import ma.ac.est_uh2c.www.wifidirect.R;
import wifi.WifiLevel;
import wifi.WifiUtils;
import wifi.model.WifiConnected;
import wifi.model.WifiDetails;
/**
 * Created by Jairo Duarte on 03/01/2017.
 */

public class ListAdapter extends ArrayAdapter<WifiDetails> {
	List<WifiDetails> wifiDetailsList;
	private LayoutInflater inflater;

	public ListAdapter(Activity a, List<WifiDetails> wifiDetailses) {
		super(a, R.layout.wifi_view, wifiDetailses);
		inflater = LayoutInflater.from(a);
		this.wifiDetailsList = wifiDetailses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.wifi_view, parent, false);
		TextView ssid = (TextView) convertView.findViewById(R.id.ssid);
        TextView capabilities = (TextView) convertView.findViewById(R.id.capabilities);
        TextView channel_frequency = (TextView) convertView.findViewById(R.id.channel_frequency_range);
        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        TextView level = (TextView) convertView.findViewById(R.id.level);
        ImageView levelImage = (ImageView) convertView.findViewById(R.id.levelImage);
        ImageView securityImage = (ImageView) convertView.findViewById(R.id.securityImage);

        WifiDetails wifiDetails= wifiDetailsList.get(position);

        ssid.setText(wifiDetails.getSSID()+" ("+wifiDetails.getBSSID()+")");
        capabilities.setText(wifiDetails.getCapabilities());
        channel_frequency.setText(Integer.toString(wifiDetails.getPrimaryFrequency())+"MHz");
        double a = WifiUtils.calculateDistance(wifiDetails.getPrimaryFrequency(),wifiDetails.getLevel());
        distance.setText(WifiUtils.distanceToString(a) +"m");
        level.setText(Integer.toString(wifiDetails.getLevel())+"dBm");

        WifiLevel strength = WifiLevel.calculate(wifiDetails.getLevel());
        levelImage.setImageResource(strength.imageResource());
        levelImage.setColorFilter(ContextCompat.getColor(getContext(), strength.colorResource()));
        if (WifiUtils.isOpen(wifiDetails.getCapabilities())) securityImage.setImageResource(R.drawable.ic_lock_open_black_18dp);
        else  securityImage.setImageResource(R.drawable.ic_lock_outline_black_18dp);
        WifiConnected wif = wifiDetails.getWifiConnected();

        if (wif!=null){
            Log.i("wf",wif.getIpAddress());
            TextView ip = (TextView) convertView.findViewById(R.id.ipaddress);
            TextView sp = (TextView) convertView.findViewById(R.id.speed);
            ip.setText(wif.getIpAddress());
           // channel.setText(Integer.toString(wif.getLinkSpeed()));

            sp.setText(Integer.toString(wif.getLinkSpeed()) +"Mbps");
            //channel.setText(Integer.toString(wif.getRssi()));

        }
		return convertView;
	}

}
