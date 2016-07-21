package com.young.view.happycoin.tool;


import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * 判读网络方法的工具类 提供一些常用方法 
 * @version 1.0
 * @since JDK6.0
 * @author hewx
 */
public class NetworkUtil {

	/**
	 * 当调用 getNetworkType（Context context）这个方法时，如果没有活动的网络则返回该常量
	 */
	public static final int TYPE_NOActive = -1;

	/**
	 * 返回当前活动网络的类型
	 * 
	 * @param context
	 * @return 网络类型，返回的数据跟ConnectivityManager定义的常量一样， 没有可用网络返回TYPE_NOActive（-1）,
	 */
	public static int getNetworkType(Context context) {
		try {
			// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理） 
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象 
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接 
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return info.getType();
					}
				}
			}
		} catch (Exception e) {
			return TYPE_NOActive;
		}
		return TYPE_NOActive;
	}

	/**
	 * 网络是否可用
	 * 
	 * @param
	 * @return
	 */
	public static boolean checkNetActive(Context context) {
		try {
			// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理） 
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象 
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接 
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * Gps是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * wifi是否打开
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * 判断当前网络是否是wifi网络
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager==null){
			return false;
		}
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前网络是否是3G网络
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager==null){
			return false;
		}
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/*
	 * 
	 *  获取DeviceId
	 */
	public static String getDeviceId(Context context){
		return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}
	/*
	 *  获取移动运营商
	 */
	public static String getNetworkOperator(Context context){
	    return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
	}
	/*
	 *  返回移动网络运营商的名字(SPN)
	 */
	public static String getNetworkOperatorName(Context context){
	    return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();
	}
	  /**
	   * 枚举网络状态
	   * NET_WIFI：wifi
	   * NET_2G:2g网络
	   * NET_3G：3g网络
	   * NET_4G：4g网络
	   * NET_UNKNOWN：未知网络
	   * NET_NO：没有网络
	   */
	  public static enum NetType{NET_WIFI,NET_2G,NET_3G,NET_4G,NET_UNKNOWN,NET_NO};
	/**
	 * 获取当前要写日志的文件
	 * @param context
	 * @param
	 * @return
	 */
	public static NETWORK_TYPE getNetType(Context context){
		NETWORK_TYPE stateCode = NETWORK_TYPE.NET_NO;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni != null && ni.isConnectedOrConnecting()) {
			switch (ni.getType()) {
			case ConnectivityManager.TYPE_WIFI:
				stateCode = NETWORK_TYPE.NET_WIFI;
				break;
			case ConnectivityManager.TYPE_MOBILE:
				switch (ni.getSubtype()) {
				case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
				case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
				case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
				case TelephonyManager.NETWORK_TYPE_1xRTT:
				case TelephonyManager.NETWORK_TYPE_IDEN:
					stateCode = NETWORK_TYPE.NET_2G;
					break;
				case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
				case TelephonyManager.NETWORK_TYPE_UMTS:
				case TelephonyManager.NETWORK_TYPE_EVDO_0:
				case TelephonyManager.NETWORK_TYPE_HSDPA:
				case TelephonyManager.NETWORK_TYPE_HSUPA:
				case TelephonyManager.NETWORK_TYPE_HSPA:
				case TelephonyManager.NETWORK_TYPE_EVDO_B:
				case TelephonyManager.NETWORK_TYPE_EHRPD:
				case TelephonyManager.NETWORK_TYPE_HSPAP:
					stateCode = NETWORK_TYPE.NET_3G;
					break;
				case TelephonyManager.NETWORK_TYPE_LTE:
					stateCode = NETWORK_TYPE.NET_4G;
					break;
				default:
					stateCode = NETWORK_TYPE.NET_UNKNOWN;
				}
				break;
			default:
				stateCode = NETWORK_TYPE.NET_UNKNOWN;
			}
		}
		return stateCode;
	}

	/**
	 * 枚举网络状态
	 * NET_WIFI：wifi
	 * NET_2G:2g网络
	 * NET_3G：3g网络
	 * NET_4G：4g网络
	 * NET_UNKNOWN：未知网络
	 * NET_NO：没有网络
	 */
	public static enum NETWORK_TYPE  {
		NET_WIFI("WIFI"),
		NET_2G("2G"),
		NET_3G("3G"),
		NET_4G("4G"),
		NET_UNKNOWN("其他"),
		NET_NO("无");
		private String desc;
		private NETWORK_TYPE(String desc) {
			this.desc = desc;
		}
		public static String desc(int ordinal){
			for(NETWORK_TYPE type : NETWORK_TYPE.values()){
				if(type.ordinal() == ordinal)
					return type.desc;
			}
			return NETWORK_TYPE.NET_UNKNOWN.desc;
		}
		public static NETWORK_TYPE get(int ordinal){
			for(NETWORK_TYPE type : NETWORK_TYPE.values()){
				if(type.ordinal() == ordinal)
					return type;
			}
			return NETWORK_TYPE.NET_UNKNOWN;
		}
	}
}
