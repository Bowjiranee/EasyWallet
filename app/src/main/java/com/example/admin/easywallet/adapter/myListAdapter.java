package com.example.admin.easywallet.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.easywallet.R;
import com.example.admin.easywallet.model.WalletItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class myListAdapter extends ArrayAdapter<WalletItem> {
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<WalletItem> mWalletItemList;

    public myListAdapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<WalletItem> walletItemList) {
        super(context, layoutResId, walletItemList);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mWalletItemList = walletItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId,null);

        WalletItem item = mWalletItemList.get(position);

        ImageView walletImageTextView = itemLayout.findViewById(R.id.imageView_pic);
        TextView walletTitleTextView = itemLayout.findViewById(R.id.textViewTitle);
        TextView walletMoneyTextView = itemLayout.findViewById(R.id.textViewMoney);

        walletTitleTextView.setText(item.title);
        walletMoneyTextView.setText(item.money);

        walletTitleTextView.setTextSize(20);
        walletTitleTextView.setTextColor(Color.BLACK);


        walletMoneyTextView.setTextSize(15);

        String pictureFileName = item.picture;

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            walletImageTextView.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            walletImageTextView.setImageDrawable(drawable);
        }

        return itemLayout;
    }
}
