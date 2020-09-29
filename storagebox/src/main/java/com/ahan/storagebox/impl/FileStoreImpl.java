package com.ahan.storagebox.impl;

import android.content.Context;

import com.ahan.storagebox.interfaces.IFile;
import com.ahan.storagebox.request.BaseRequest;
import com.ahan.storagebox.request.FileResponce;


/**
 * Time:2020/8/4
 * Author:ahan
 * Description: android 11 以下的实现
 */
public class FileStoreImpl implements IFile {
    @Override
    public <T extends BaseRequest> FileResponce newCreateFile(Context context, T besResponce) {
        return null;
    }

    @Override
    public <T extends BaseRequest> FileResponce delete(Context context, T besResponce) {
        return null;
    }

    @Override
    public <T extends BaseRequest> FileResponce renameTo(Context context, T whre, T request) {
        return null;
    }

    @Override
    public <T extends BaseRequest> FileResponce copyFile(Context context, T besResponce) {
        return null;
    }

    @Override
    public <T extends BaseRequest> FileResponce query(Context context, T besResponce) {
        return null;
    }
}
