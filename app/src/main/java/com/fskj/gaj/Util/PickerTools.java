package com.fskj.gaj.Util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;


import com.fskj.gaj.AppConfig;

import java.io.File;

/**
 * 功能说明:拍照或者 选择图片后的处理
 * 作    者:zhengwei
 * 创建日期:2016/8/18 11:11
 * 所属项目:fireeye
 */
public class PickerTools {
    public interface PickerToolsListener {
        public void afterDo(String filepath);
    }

    public static void DealImage(Activity activity, int requestCode, String filepath, Intent data, PickerToolsListener listener) {
        switch (requestCode) {
            case AppConfig.Album1:
                Uri imageFilePath = data.getData();
                File source = Tools.getImageFromSys(activity, imageFilePath);
                if (source == null || source.exists() == false) {
                    return;
                }
                try {
                    //"_small.jpg"
                    File toFile = YFileManager.getImageFile(System
                            .currentTimeMillis()+".jpg");

                    if (Tools.zoomPic(source, toFile)) {
                        listener.afterDo(toFile.getPath());
                    } else {
                        listener.afterDo(filepath);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(activity, "图片选择失败，请重试", Toast.LENGTH_SHORT)
                            .show();
                }

                break;

            case AppConfig.Camera:
                if (filepath.equals("") == false) {
                    File newpic = null;
                    try {
                        newpic = YFileManager.getImageFile(System
                                .currentTimeMillis() + ".jpg");

                        File fileFromAlbum = YFileManager
                                .getImageFile(filepath);
                        if (fileFromAlbum == null
                                || fileFromAlbum.exists() == false) {
                            Toast.makeText(activity, "拍照失败，请重试",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (Tools.zoomPic(fileFromAlbum, newpic)) {
                            listener.afterDo(newpic.getPath());
                        } else {
                            listener.afterDo(filepath);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(activity, "拍照失败，请重试",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(activity, "拍照失败，请重试..",
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
