package com.young.view.happycoin.tool;




import com.young.view.happycoin.HappyCoinApplication;

import java.io.File;
public class FilePaths {
	public final static String LOG_PATH = HappyCoinApplication.PATH_RES+"/log/";// ��־·��
	public final static String ERR_STRING = HappyCoinApplication.PATH_RES+"/error/";// ���������־·��

	public static void createFile() {
		File errfiles = new File(ERR_STRING);
		File logfiles = new File(LOG_PATH);

		if (!errfiles.exists()) {
			errfiles.mkdirs();
		}

		if(!logfiles.exists())
			logfiles.mkdirs();

		String cmdlog = "chmod 777 " + logfiles.getAbsolutePath();
		String cmderror = "chmod 777 " + errfiles.getAbsolutePath();

		try {
			Runtime.getRuntime().exec(cmdlog);
			Runtime.getRuntime().exec(cmderror);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
