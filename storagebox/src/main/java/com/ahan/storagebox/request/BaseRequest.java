package com.ahan.storagebox.request;

import java.io.File;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description:
 */
public class BaseRequest {
    //相抵路径
    private File file;

    //文件类型
    private String type;

    public BaseRequest(File file) {
        this.file = file;

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
