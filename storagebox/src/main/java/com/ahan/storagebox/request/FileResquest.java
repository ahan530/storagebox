package com.ahan.storagebox.request;

import android.os.Environment;
import android.provider.MediaStore;


import com.ahan.storagebox.annotion.DbFied;

import java.io.File;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description: 文件创建bean
 */
public class FileResquest extends BaseRequest {

    @DbFied(MediaStore.Downloads.DISPLAY_NAME)
    private String dispalyName;
    @DbFied(MediaStore.Downloads.RELATIVE_PATH)
    private String path;
    @DbFied(MediaStore.Downloads.TITLE)
    private String title;

    public FileResquest(File file) {
        super(file);
        this.path = file.getName();
    }


    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getPath() {
        return Environment.DIRECTORY_DOWNLOADS +"/"+path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
