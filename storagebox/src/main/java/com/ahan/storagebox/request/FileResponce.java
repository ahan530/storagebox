package com.ahan.storagebox.request;

import android.net.Uri;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description:
 */
public class FileResponce {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private boolean success;
    private Uri uri;
}
