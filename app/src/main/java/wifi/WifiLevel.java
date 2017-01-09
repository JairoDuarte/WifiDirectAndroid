package wifi;

import ma.ac.est_uh2c.www.wifidirect.R;

/**
 * Created by Jairo Duarte on 02/01/2017.
 */


public enum  WifiLevel {
    ZERO(R.drawable.ic_signal_wifi_0_bar_black_36dp, R.color.error_color),
    ONE(R.drawable.ic_signal_wifi_1_bar_black_36dp, R.color.warning_color),
    TWO(R.drawable.ic_signal_wifi_2_bar_black_36dp, R.color.warning_color),
    THREE(R.drawable.ic_signal_wifi_3_bar_black_36dp, R.color.success_color),
    FOUR(R.drawable.ic_signal_wifi_4_bar_black_36dp, R.color.success_color);

    private final int imageResource;
    private final int colorResource;

    WifiLevel(int imageResource, int colorResource) {
        this.imageResource = imageResource;
        this.colorResource = colorResource;
    }

    public static WifiLevel calculate(int level) {
        int index = WifiUtils.calculateSignalLevel(level, values().length);
        return WifiLevel.values()[index];
    }

    public static WifiLevel reverse(WifiLevel strength) {
        int index = WifiLevel.values().length - strength.ordinal() - 1;
        return WifiLevel.values()[index];
    }

    public int colorResource() {
        return colorResource;
    }

    public int imageResource() {
        return imageResource;
    }

    public boolean weak() {
        return ZERO.equals(this);
    }

}
