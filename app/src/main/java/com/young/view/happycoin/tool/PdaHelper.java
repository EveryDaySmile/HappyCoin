package com.young.view.happycoin.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import android.device.DeviceManager;
import android.telephony.TelephonyManager;
public class PdaHelper {
	static String PdaNumberStr = "";
	static String IMSINumber = "";
	static String NativePhoneNumber = "";
	/**
	 * ��ȡsim��id
	 * @return id
	 */
	public static String getSimId(final Context context) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			NativePhoneNumber = telephonyManager.getLine1Number();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return NativePhoneNumber;
	}
	public static String getIMEI(final Context context) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			// IMSINumber = telephonyManager.getDeviceId();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return IMSINumber;
	}
	/**
	 * ��ȡ�豸id
	 * 
	 * @return
	 */
	public static String getPdaId(final Context context) {

		try {
			DeviceManager deviceManager = new DeviceManager();
			PdaNumberStr = deviceManager.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PdaNumberStr;
	}
	/**
	 * ��ȡ��ǰ�汾��
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersion(Context context) {
		String versionName = null;
		try {
			PackageInfo info;
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionName;
	}
	/*
	 * MD5����
	 */
	public String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		// 16λ���ܣ��ӵ�9λ��25λ
		return md5StrBuff.substring(8, 24).toString().toUpperCase();
	}
	// mdt32λ����
	public final static String get32MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// ʹ��MD5����MessageDigest����
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// ��û����(int)b����˫�ֽڼ���
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
