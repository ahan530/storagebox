package com.ahan.storagebox.request;

import android.os.Environment;
import android.provider.MediaStore;

import com.ywy.myshopping.utils.fileload.annotion.DbFied;

import java.io.File;

/**
 * Time:2020/8/5
 * Author:ahan
 * Description: 图片创建器
 */
public class ImageRequest extends BaseRequest{

    @DbFied(MediaStore.Images.Media.MIME_TYPE)
    private String mineType; //格式  "image/jpeg"
    @DbFied(MediaStore.Images.Media.DISPLAY_NAME)
    private String dispalyName;
    @DbFied(MediaStore.Images.Media.RELATIVE_PATH)
    private String path;

    public String getMineType() {
        return mineType;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getPath() {
        return Environment.DIRECTORY_PICTURES +"/"+ path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageRequest(File file) {
        super(file);
    }
}
