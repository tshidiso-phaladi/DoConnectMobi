package com.cogent.doconnect.mobileapp;

/**
 * Created by Thabane.N on 2016/10/21.
 */

public class GlobalStore {
    private int _userId;

    public GlobalStore()
    {

    }

    public int GetUserId()
    {
        return _userId;
    }
    public void SetUserId(int userID)
    {
        _userId = userID;
    }
}
