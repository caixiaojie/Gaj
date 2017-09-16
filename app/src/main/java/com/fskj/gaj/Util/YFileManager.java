package com.fskj.gaj.Util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class YFileManager {

	public static void createDirectory() {
		File nf = new File(Environment.getExternalStorageDirectory(), "ulabor");
		if (nf.exists() == false) {
			Log.e("ulabor","ulabor");
			nf.mkdirs();
		}else{

		}

		File img = new File(nf, "Images");
		if (img.exists() == false)
			img.mkdirs();
		
		File cache = new File(nf, "Cache");
		if (cache.exists() == false)
			cache.mkdirs();
	}

	public File getRootDirectory() throws IOException {
		return new File(Environment.getExternalStorageDirectory(), "ulabor");
	}

	public static boolean checkSDCard() {
		String st = Environment.getExternalStorageState();
		if (st == null || st.equals("")) {
			return false;
		} else {
			return st.equals(Environment.MEDIA_MOUNTED);
		}

	}

	public static boolean hasSDCard() {
		boolean flag = true;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED) == false) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 获取 Merchant/Images 中的图片
	 * @param filename
	 * @return
	 */
	public static File getImageFile(String filename) throws IOException,Exception {
		createDirectory();
		File root = new File(Environment.getExternalStorageDirectory(), "ulabor");
		File img = new File(root, "Images");
		if(img.exists()==false){
			img.mkdirs();
		}
		return new File(img, filename);
	}
	
	public static File getCacheFile(String filename) {
		createDirectory();
		File root = new File(Environment.getExternalStorageDirectory(), "ulabor");
		File cache = new File(root, "Cache");
		return new File(cache, filename);
	}

	public static void removeImageFiles() {
		createDirectory();
		File root = new File(Environment.getExternalStorageDirectory(), "ulabor");
		File cache = new File(root, "Images");
		File[] files = cache.listFiles();
		for (File f : files) {
			f.delete();
		}
	}

	public static void removeCacheFiles() {
		createDirectory();
		File root = new File(Environment.getExternalStorageDirectory(), "ulabor");
		File cache = new File(root, "Cache");
		File[] files = cache.listFiles();
		for (File f : files) {
			f.delete();
		}
	}

	public static boolean copyFile(File source, File toFile) {
		if (source == null || source.exists() == false || source.isDirectory()
				|| toFile == null || toFile.exists() == false
				|| toFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		try {
			InputStream fosfrom = new FileInputStream(source);
			OutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


}
