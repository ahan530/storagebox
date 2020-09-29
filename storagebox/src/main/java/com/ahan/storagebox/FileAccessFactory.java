package com.ahan.storagebox;

import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import com.ahan.storagebox.impl.FileStoreImpl;
import com.ahan.storagebox.impl.MediaStoreAccessImp;
import com.ahan.storagebox.interfaces.IFile;
import com.ahan.storagebox.request.BaseRequest;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description: 文件接收 针对外部存储external 内部存储以前怎么做还是怎么做
 */
public class FileAccessFactory {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static IFile getIFile(BaseRequest baseRequest) {
        //不允许兼容模式的就是11
        if (!Environment.isExternalStorageLegacy()) {

            setFileType(baseRequest);

            return MediaStoreAccessImp.getInstance();
        } else {

            return new FileStoreImpl();
        }
    }

    //通过文件后缀识别文件
    private static void setFileType(BaseRequest baseRequest) {

        if (baseRequest.getFile().getAbsolutePath().endsWith(".mp3") ||
                baseRequest.getFile().getAbsolutePath().endsWith(".wav")) {
            baseRequest.setType(MediaStoreAccessImp.AUDIO);
        } else if (
                baseRequest.getFile().getAbsolutePath().startsWith(MediaStoreAccessImp.VIDEO) ||
                        baseRequest.getFile().getAbsolutePath().endsWith(".rmvb") ||
                        baseRequest.getFile().getAbsolutePath().endsWith(".mp4") ||
                        baseRequest.getFile().getAbsolutePath().endsWith(".avi")) {
            baseRequest.setType(MediaStoreAccessImp.VIDEO);
        } else if (baseRequest.getFile().getAbsolutePath().startsWith(MediaStoreAccessImp.IMAGE) ||
                baseRequest.getFile().getAbsolutePath().endsWith(".jpg") ||
                baseRequest.getFile().getAbsolutePath().endsWith(".jpeg") ||
                baseRequest.getFile().getAbsolutePath().endsWith(".png")) {
            baseRequest.setType(MediaStoreAccessImp.IMAGE);
        } else {
            baseRequest.setType(MediaStoreAccessImp.DOWNLOADS);
        }
    }
}
