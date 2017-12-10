package com.example.admin.easywallet;

import com.example.admin.easywallet.model.WalletItem;

import java.util.ArrayList;


public class WalletData {
    private static WalletData sInstance;
    public ArrayList<WalletItem> walletDataList;

    public static WalletData getInstance(){
        if(sInstance == null){
            sInstance = new WalletData();
        }
        return  sInstance;
    }
}
