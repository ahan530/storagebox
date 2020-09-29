package com.ahan.storagebox.impl;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ahan.storagebox.annotion.DbFied;
import com.ahan.storagebox.interfaces.IFile;
import com.ahan.storagebox.request.BaseRequest;
import com.ahan.storagebox.request.FileResponce;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description: android 11的实现
 */
public class MediaStoreAccessImp implements IFile {
    //URI
    public static final String AUDIO = "Audio";
    public static final String VIDEO = "Video";
    public static final String IMAGE = "Pictrues";
    public static final String DOWNLOADS = "DownLoads";

    public HashMap<String, Uri> uriMap = new HashMap<>();

    private static MediaStoreAccessImp accessImp;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static MediaStoreAccessImp getInstance() {
        if (accessImp == null) {
            synchronized (MediaStoreAccessImp.class) {
                if (accessImp == null) {
                    accessImp = new MediaStoreAccessImp();
                }
            }
        }
        return accessImp;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private MediaStoreAccessImp() {
        uriMap.put(AUDIO, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        uriMap.put(VIDEO, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        uriMap.put(IMAGE, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        uriMap.put(DOWNLOADS, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
    }

    //创建文件
    @Override
    public <T extends BaseRequest> FileResponce newCreateFile(Context context, T besResponce) {

        Uri uri = uriMap.get(besResponce.getType());
        ContentValues contentValues = getContentValues(besResponce);

        Uri insertUri = context.getContentResolver().insert(uri, contentValues);
        if (insertUri == null) {
            Log.e("---", "创建失败");
        } else {
            Log.e("---", "创建成功");
        }

        FileResponce fileResponce = new FileResponce();
        fileResponce.setSuccess(true);
        fileResponce.setUri(insertUri);
        return fileResponce;
    }

    private <T extends BaseRequest> ContentValues getContentValues(T besResponce) {
        ContentValues contentValues = new ContentValues();

        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
        Field[] fields = besResponce.getClass().getDeclaredFields();

        for (Field field : fields) {
            DbFied dbFied = field.getAnnotation(DbFied.class);
            if (dbFied != null) {
                continue;
            }
            String key = dbFied.value();
            //拿到 path 属性

            String path = field.getName();
            String values = null;
            char c = Character.toUpperCase(path.charAt(0));

            String substring = path.substring(1);
            String method = "get" + c + substring;

            try {
                Method method1 = besResponce.getClass().getMethod(method);
                values = (String) method1.invoke(besResponce);

                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(values)) {
                    contentValues.put(key, values);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return contentValues;
    }

    @Override
    public <T extends BaseRequest> FileResponce delete(Context context, T besRequset) {

        //查询后再删除
        Uri uri = query(context, besRequset).getUri();
        FileResponce fileResponce = new FileResponce();
        if (uri!=null){
            int delete = context.getContentResolver().delete(uri, null, null);

            //delete  The number of rows deleted.
            if (delete<0){
                fileResponce.setSuccess(false);
            }else {
                fileResponce.setSuccess(true);
            }

        }
        return fileResponce;
    }

    @Override
    public <T extends BaseRequest> FileResponce renameTo(Context context, T whre, T request) {
        return null;
    }

    @Override
    public <T extends BaseRequest> FileResponce copyFile(Context context, T besResponce) {
        return null;
    }


    //查询
    @Override
    public <T extends BaseRequest> FileResponce query(Context context, T besResponce) {
        Uri uri = uriMap.get(besResponce.getType());
        ContentValues contentValues = getContentValues(besResponce);
        Condition condition = new Condition(contentValues);
        Cursor query = null;
        try {
            String[] strings = {MediaStore.Images.Media._ID};
            query = context.getContentResolver().query(uri, strings, condition.whereCasue, condition.whereArgs, null);

        } catch (Exception e) {

        }

        FileResponce responce = new FileResponce();
        Uri uriFile = null;
        if (query != null && query.moveToFirst()) {
            uriFile = ContentUris.withAppendedId(uri, query.getLong(0));
            query.close();

            if (uriFile != null) {
                //查询成功
                responce.setSuccess(true);
                responce.setUri(uriFile);
            }else {
                responce.setSuccess(false);
            }
        }

        return responce;
    }

    private class Condition {
        private String whereCasue;
        private String[] whereArgs;
        private ContentValues contentValues;

        public Condition(ContentValues contentValues) {
            this.contentValues = contentValues;
            StringBuffer buffer = new StringBuffer();
            buffer.append("1=1");
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, Object>> iterator = contentValues.valueSet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                String key = next.getKey();
                String value = (String) next.getValue();
                if (value != null) {
                    buffer.append("and" + key + "=?");
                    arrayList.add(value);
                }
            }
            this.whereCasue = buffer.toString();
            this.whereArgs = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
    }
}
