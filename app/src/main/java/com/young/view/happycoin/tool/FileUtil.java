package com.young.view.happycoin.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;

import com.young.view.happycoin.HappyCoinApplication;


public class FileUtil {
	
	public static String path;
	private static final String DIR_NAME = "Android/data/com.sgcc";
	/**
	 *
	 * 
	 * @param folderPath
	 *
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			try {
				myFilePath.delete(); //
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			HappyCoinApplication.loger.info(e.getMessage());
		}
	}

	/**
	 *
	 * 
	 * @param path
	 *
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {//isDirectory()
			return flag;
		}
		String[] tempList = file.list();//
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);//
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			try {
				if (temp.isFile()) {
					temp.delete();//
				}
			} catch (Exception e) {
				HappyCoinApplication.loger.info(e.getMessage());
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}


	public static String getDir() {
		String resPath = Environment.getExternalStorageDirectory() + "/" + DIR_NAME;
		File resDirSD = new File(resPath);
		
		try {
			if(resDirSD.exists()){
				return resPath;
			}else{
				resDirSD.mkdirs();
				return resPath;
			}
			
		} catch (Exception e) {
			HappyCoinApplication.loger.info(e.getMessage());
			resPath = "";
		}
		return resPath;
	}

	/**
	 *
	 * 
	 * @param srcFile
	 * @param destFile
	 * @return
	 */
	public static long copyFile(File srcFile, File destFile) {
		long copySizes = 0;
		if (!srcFile.exists()) {
			System.out.println("no source file");
			copySizes = -1;
		} else {
			try {
				try {
					if (!destFile.exists())
						destFile.createNewFile();
				} catch (Exception e) {

				}
				BufferedInputStream bin = new BufferedInputStream(new FileInputStream(srcFile));
				BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(destFile));
				int b = 0, i = 0;
				while ((b = bin.read()) != -1) {
					bout.write(b);
					i++;
				}
				bout.flush();
				bin.close();
				bout.close();
				copySizes = i;
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
			}
		}
		return copySizes;
	}
	
	public static long copyFile(InputStream is, File destFile) {
		long copySizes = 0;
			try {
				try {
					if (!destFile.exists())
						destFile.createNewFile();
				} catch (Exception e) {
					HappyCoinApplication.loger.info(e.getMessage());
				}
				BufferedInputStream bin = new BufferedInputStream(is);
				BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(destFile));
				int b = 0, i = 0;
				// long t1 = System.currentTimeMillis();
				while ((b = bin.read()) != -1) {
					bout.write(b);
					i++;
				}
				bout.flush();
				bin.close();
				bout.close();
				copySizes = i;
				// long t = t2-t1;
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
			}
		return copySizes;
	}
	
	

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static int createFile(String fileName) {
		int createFlag = -1;
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				if (file.createNewFile()) {
					createFlag = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
			}
		} else {
			createFlag = 0;
		}
		return createFlag;
	}
	public static boolean createDir(String fileName) {
		int createFlag = -1;
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				if (file.mkdirs()) {
					createFlag = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
			}
		} else {
			createFlag = 0;
		}
		return createFlag == 1;
	}

	public static File getFile(String fileName) {
		File file = new File(fileName);

		int flag = 0;
		if (!file.exists()) {
			flag = createFile(fileName);
		}

		if (flag != -1) {
			return file;
		} else {
			return null;
		}
	}

	public static void writeLogFromString(File file, String str) {
		try {
			FileWriter fw = new FileWriter(file, false);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			HappyCoinApplication.loger.info(e.getMessage());
		}
	}

	/**
	 * 
	 * @param
	 * @param fos
	 */
	public static void createFromRawDbFiles(List<InputStream> isList, FileOutputStream fos) {
		try {
			for (InputStream is : isList) {
				int totalLength = 0;
				try {
					totalLength = is.available();
				} catch (IOException e) {
					e.printStackTrace();
					HappyCoinApplication.loger.info(e.getMessage());
				}

				byte[] buffer = new byte[totalLength];

				int len = 0;
				try {
					len = is.read(buffer);
				} catch (IOException e) {
				}

				fos.write(buffer, 0, len);
				is.close();
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			HappyCoinApplication.loger.info(e.getMessage());
		}
	}

	/**
	 *
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isFileValid(String path) {
		boolean exist = false;
		if (path != null && !path.equals("")) {
			try {
				File file = new File(path);
				exist = file.exists() && !file.isDirectory() && file.isFile();
			} catch (Exception e) {
				e.printStackTrace();
				HappyCoinApplication.loger.info(e.getMessage());
				exist = false;
			}
		}
		return exist;
	}

	/**
	 *
	 * 
	 * @param parentFile
	 * @param
	 * @return
	 */
	public static ArrayList<String> getFileList(File parentFile, FileFilter filter) {
		ArrayList<String> fileNames = null;
		if (parentFile != null && parentFile.exists()) {
			fileNames = new ArrayList<String>();
			String path = parentFile.getAbsolutePath();
			if (parentFile.isDirectory()) {
				File[] list = parentFile.listFiles(filter);
				if (list != null && list.length > 0) {
					for (File f : list) {
						String childName = path + File.separatorChar + f.getName();
						File child = new File(childName);
						ArrayList<String> childs = getFileList(child, filter);
						fileNames.addAll(childs);
					}
				}
			} else {
				fileNames.add(path);
			}
		}
		return fileNames;
	}


	public static String getFileText(String paramString) {
	    File localFile = new File(paramString);
	    if (localFile.exists())
	      try
	      {
	        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(localFile))));
	        StringBuffer localStringBuffer = new StringBuffer();
	        while (true)
	        {
	          if (!localBufferedReader.ready())
	          {
	            localBufferedReader.close();
	            return localStringBuffer.toString();
	          }
	          localStringBuffer.append(localBufferedReader.readLine());
	        }
	      }catch (IOException localIOException)
	      {
	        localIOException.printStackTrace();
			  HappyCoinApplication.loger.info(localIOException.getMessage());
	      }
	    return null;
	}


//	public static List<String> getAllTaskInfoFileName(){
//		String srcPath=SgccApplication.PUT_DATA_FILE;//��Դ�ļ���·��
//		List<String> alllist=new ArrayList<String>();
//		try{
//			File f=new File(srcPath);
//			if(f.exists()){
//				File[] fList=f.listFiles();
//				for(int j=0;j<fList.length;j++){
//					if(fList[j].exists()){
//						alllist.add(fList[j].getName());
//						System.out.println(fList[j].getName());
//					}
//				}
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			SgccApplication.loger.info(e.getMessage());
//		}
//
//		return alllist;
//	}
//
//
//	public static String getLastTaskInfoFileName(){
//		String srcPath=SgccApplication.PUT_DATA_FILE;//��Դ�ļ���·��
//		long maxTaskInfoNum=0;
//		String taskInfoIDNew="";
//		try {
//			File f = new File(srcPath);
//			if (f.isDirectory()) {
//				File[] fList = f.listFiles();
//				long taskInfoNum=0;
//				for (int j = 0; j < fList.length; j++) {
//					if (fList[j].isDirectory()) {
//						String taskInfoID=fList[j].getName();
//						taskInfoNum=Long.parseLong(taskInfoID);
//						if(taskInfoNum>maxTaskInfoNum){
//							maxTaskInfoNum=taskInfoNum;
//							taskInfoIDNew=taskInfoID;
//						}
//						System.out.println(fList[j].getName());
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			SgccApplication.loger.info(e.getMessage());
//		}
//
//		return taskInfoIDNew;
//
//
//	}
//	public static List<String> getALLTaskInfoFiles(String taskInfoID){
//		String srcPath=SgccApplication.PUT_DATA_FILE+"/"+taskInfoID;
//		List<String> resultList=new ArrayList<String>();
//		try {
//			File f = new File(srcPath);
//			if (f.isDirectory()) {
//				File[] fList = f.listFiles();
//				for (int j = 0; j < fList.length; j++) {
//					if (fList[j].isFile()) {
//						resultList.add(fList[j].getName());
//						System.out.println(fList[j].getName());
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			SgccApplication.loger.info(e.getMessage());
//		}
//
//		return resultList;
//
//
//	}

	public static String getTaskInfoSubFileName(String name){
		String[] names=name.split("_");
		int start=(names[0]+names[1]+"_").length()+1;
		int end=name.indexOf(".");
		
		
		return name.substring(start, end);
		
		
	}

	public static File getAssetFileToCacheDir(Context context, String fileName) { 
        try { 
            File cacheDir = getCacheDir(context); 
            final String cachePath = cacheDir.getAbsolutePath() + File.separator + fileName; 
            InputStream is = context.getAssets().open(fileName); 
            File file = new File(cachePath); 
            file.createNewFile(); 
            FileOutputStream fos = new FileOutputStream(file); 
            byte[] temp = new byte[1024]; 
 
            int i = 0; 
            while ((i = is.read(temp)) > 0) { 
                fos.write(temp, 0, i); 
            } 
            fos.close(); 
            is.close(); 
            return file; 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 

    public static File getCacheDir(Context context) { 
        File dir = context.getCacheDir(); 
        if (!dir.exists()) { 
            dir.mkdirs(); 
        } 
        return dir; 
    } 

}
