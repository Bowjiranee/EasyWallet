package com.example.admin.easywallet.model;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class WalletItem {
    public final int id;
    public final String title;
    public final String money;
    public final String picture;

    public WalletItem(int id, String title, String money, String picture) {
        this.id = id;
        this.title = title;
        this.money = money;
        this.picture = picture;
    }

//    public Drawable getPictureDrawable(Context context) {
//        AssetManager am = context.getAssets();
//        try {
//            InputStream inputStream = am.open(picture);
//            Drawable drawable = Drawable.createFromStream(inputStream, "");
//            return drawable;
//
//        } catch (IOException e) {
//            Log.e("ic_income", "Error Openning File: " + picture);
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public String toString() {
        return title;
    }
}
