package com.fskj.gaj.Util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fskj.gaj.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


/**
 * 功能说明:工具类
 * 作    者:zhengwei
 * 创建日期:2015/11/4 13:03
 * 所属项目:通用1.0
 */
public class Tools {

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static SharedPreferences getSharePreferences(Context context,
                                                        String name) {
		SharedPreferences sh = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);
		return sh;
	}

	public static boolean checkConnection(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.isConnected()) {
			return true;
		}
		return false;
	}

	public static void closeInputWindow(Activity activity) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(activity
					.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {

		}
	}

	public static int getScreenWidth(Context context) {
		//DisplayMetrics metric = new DisplayMetrics();
		 WindowManager wm = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
		//(WindowManager)context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		Point pp=new Point();
		 wm.getDefaultDisplay().getSize(pp); // 屏幕宽度（像素）
		int width =pp.x;
		return width;
	}

	public static String safeString(EditText et) {
		String source = et.getText().toString();
		if (source.length() == 0) {
			return "";
		} else {
			String re = source.trim().replace("}", "").replace("{", "")
					.replace("<", "").replace(">", "").replace("]", "")
					.replace("[", "").replace("\"", "");
			return re;
		}
	}
	public static String safeString(TextView tv) {
		String source = tv.getText().toString();
		if (source.length() == 0) {
			return "";
		} else {
			String re = source.trim().replace("}", "").replace("{", "")
					.replace("<", "").replace(">", "").replace("]", "")
					.replace("[", "").replace("\"", "");
			return re;
		}
	}
	public static String safeString(String str) {
		if (str == null||str.isEmpty()) {
			return "-";
		} else {
			return str;
		}
	}

	public static String safeString(String str, String unit) {
		if (str == null||str.isEmpty()) {
			return "-";
		} else {
			return str+unit;
		}
	}

	public static String safeString(boolean str) {
		if (str ) {
			return "是";
		} else {
			return "否";
		}
	}

	public static String safeString(double m){
		String s= String.format("%.2f",m);
		s=s.replace(".00","");
		return s;
	}

	public static void showCamera(Activity activity, String filename) throws Exception {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 下面这句指定调用相机拍照后的照片存储的路径
		File f = YFileManager.getImageFile(filename);
		Log.e("file",f.getAbsolutePath());
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		activity.startActivityForResult(intent, AppConfig.Camera);
	}


	public static void showPicker(Activity activity) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_PICK);
		activity.startActivityForResult(intent, AppConfig.Album1);
	}



	public static int getTextLength(TextView textView, String text) {
		TextPaint paint = textView.getPaint();
		// 得到使用该paint写上text的时候,像素为多少
		float textLength = paint.measureText(text);
		return (int) (textLength);
	}


	/*public static File getImageFromSys(Activity activity, Uri imageFilePath) {
		try {
			File re = null;
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = activity.getContentResolver().query(imageFilePath,
					proj, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				String path = cursor.getString(0);
				re = new File(path);
			}
			cursor.close();
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/


	/**
	 * 缩放图片
	 *
	 * @param source
	 * @param toFile
	 * @return
	 */

	public static boolean zoomPic(File source, File toFile) {
		if (toFile.exists()) {
			toFile.delete();
		}

		boolean flag = true;
		try {
			FileOutputStream out = new FileOutputStream(toFile);
			Bitmap bitmap = BitmapFactory.decodeFile(source.toString());
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();

			if (width > 600) {
				// 缩放
				Matrix matrix = new Matrix();
				float k = 1.0f;// 缩放率
				// 宽比高大
				k = 600f / width;

				matrix.postScale(k, k);
				int angle=readPictureDegree(source.toString());
				matrix.postRotate(angle);
				Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width,
						height, matrix, true);
				newbmp.compress(Bitmap.CompressFormat.JPEG, 80, out);
				out.close();
				newbmp.recycle();
				bitmap.recycle();
			} else {
				// 直接 COPY
				InputStream fosfrom = new FileInputStream(source);
				OutputStream fosto = new FileOutputStream(toFile);
				byte bt[] = new byte[1024];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c);
				}
				fosfrom.close();
				fosto.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}





	public static String createID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void downloadImage(Context context, final ImageView paramImageView, String url) {

		if(url != null && url.isEmpty()==false) {
			if (paramImageView!=null){
				Glide.with(context)
						.load(url)
						.diskCacheStrategy(DiskCacheStrategy.ALL)
//					 .placeholder(R.mipmap.rectangle_img_head)
						.into(new SimpleTarget<GlideDrawable>() {
							@Override
							public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

								paramImageView.setImageDrawable(resource);
							}
						});
			}



		}else{
//			 Glide.with(context).load(R.mipmap.banner).into(paramImageView);
		}

	}


	public static void downloadImageByResize(Context context, final ImageView paramImageView, String url, final int width) {

		if(url != null && url.isEmpty()==false) {
			if (paramImageView!=null){
				Glide .with(context)
						.load(url)
						.diskCacheStrategy(DiskCacheStrategy.ALL)
//					 .placeholder(R.mipmap.rectangle_img_head)
						.into(new SimpleTarget<GlideDrawable>() {
							@Override
							public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//								try {
//									int height = width * resource.getBounds().height() / resource.getBounds().width();
//									LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,height);
//									paramImageView.setLayoutParams(lp);
//								}catch (Exception e) {
//									e.printStackTrace();
//								}

								paramImageView.setImageDrawable(resource);
							}
						});
			}



		}else{
//			 Glide.with(context).load(R.mipmap.banner).into(paramImageView);
		}

	}

	public static  void loadImage(Context activity, ImageView imageView, String filepath){
		if(filepath==null||filepath.equals("")){
			return;
		}
		File ff=new File(filepath);

		if(ff.exists()==false){
			return;
		}
		BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
		bitmapFactoryOptions.inJustDecodeBounds = true;
		bitmapFactoryOptions.inPreferredConfig= Bitmap.Config.ALPHA_8;
		Bitmap bitmap = BitmapFactory.decodeFile(filepath,bitmapFactoryOptions);

		int width = bitmapFactoryOptions.outWidth;
		int height = bitmapFactoryOptions.outHeight;
		if(bitmap!=null) {
			bitmap.recycle();
		}
		int f=1;
		if(width>=height){
			f=width/400;
		}else{
			f=height/400;
		}
		if(f<1){
			f=1;
		}

		bitmapFactoryOptions.inSampleSize=f;
		bitmapFactoryOptions.inJustDecodeBounds = false;
		bitmapFactoryOptions.inPreferredConfig= Bitmap.Config.ARGB_8888;
		bitmap = BitmapFactory.decodeFile(filepath,bitmapFactoryOptions);
		if(bitmap!=null) {
			imageView.setImageBitmap(bitmap);
		}else{
			Toast.makeText(activity,"设置图片失败", Toast.LENGTH_SHORT).show();
		}
	}

	public static  void whiteText(LinearLayout root){
		int cnt=root.getChildCount();
		Log.i("ddd","cnt="+cnt);
		for(int i=0;i<cnt;i++){
			View view=root.getChildAt(i);
			if (view instanceof TextView){
				((TextView) view).setTextColor(Color.WHITE);
			}
		}
	}

	public static File getImageFromSys(Activity activity, Uri imageFilePath) {
		try {
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			String mImgPath=null;
			File re = null;
			Cursor cursor = activity.getContentResolver().query(imageFilePath,
					filePathColumn, null, null, null);
			if(cursor!=null) {
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				mImgPath = picturePath;
			}
			else
			{
				mImgPath = imageFilePath.getPath();
			}
			re = new File(mImgPath);
			return  re;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}




	/**
	 * 根据值, 设置spinner默认选中:
	 * @param spinner
	 * @param value
	 */
	public static void setSpinnerItemSelectedByValue(Spinner spinner, String value){
		SpinnerAdapter apsAdapter= spinner.getAdapter(); //得到SpinnerAdapter对象
		int k= apsAdapter.getCount();
		for(int i=0;i<k;i++){
			if(value.equals(apsAdapter.getItem(i).toString())){
				spinner.setSelection(i,true);// 默认选中项
				break;
			}
		}
	}

	/**
	 * 读取图片属性：旋转的角度
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree  = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
}
