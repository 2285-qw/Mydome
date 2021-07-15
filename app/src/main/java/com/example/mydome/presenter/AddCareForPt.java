package com.example.mydome.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.example.mydome.MainActivity2;
import com.frame.base.BaseModel;
import com.frame.base.BasePresenter;


public class AddCareForPt extends BasePresenter<MainActivity2> {

    private static final String API = "http://8.136.220.28:86/data.json";

    public AddCareForPt(MainActivity2 activity) {
        super(activity);
    }

    public void addConcernedPeople(String phone) {
        LogUtils.d( "-----");
        createRequestBuilder()
                //.putParam("Mobile", phone)
                .setLoadStyle(BaseModel.LoadStyle.VIEW)
                .setRequestTag("addConcernedPeople")
                .create()
                .get(API);
        LogUtils.d( "222");
    }

}