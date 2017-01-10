package javafiles;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.wifi.ScanResult;

public class ImportDialog {
	final CharSequence[] items = { "Take Photo From Gallery",
			"Take Photo From Camera" };
	Activity activity;
	AlertDialog dialog;
	AlertDialog.Builder builder;
	ScanResult detailProvader;

	public ImportDialog(Activity a, ScanResult detailProvader) {
		this.activity = a;
		this.detailProvader = detailProvader;
		builder = new AlertDialog.Builder(a);
	}

	public void showDialog() {

		builder.setTitle("Wifi" + detailProvader.SSID+"Details ");
		builder.setMessage(detailProvader.toString());

		AlertDialog alert = builder.create();
		alert.show();
	}
}
