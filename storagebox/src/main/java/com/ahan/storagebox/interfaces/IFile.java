package com.ahan.storagebox.interfaces;

import android.content.Context;

import com.ahan.storagebox.request.BaseRequest;
import com.ahan.storagebox.request.FileResponce;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description: 接口
 */
public  interface IFile {
    //增
    <T extends BaseRequest> FileResponce newCreateFile(Context context, T besResponce);
    //删
    <T extends BaseRequest> FileResponce delete(Context context, T besResponce);
    //改
    <T extends BaseRequest> FileResponce renameTo(Context context, T whre, T request);
    //复制
    <T extends BaseRequest> FileResponce copyFile(Context context, T besResponce);
    //查询
    <T extends BaseRequest> FileResponce query(Context context, T besResponce);
}
