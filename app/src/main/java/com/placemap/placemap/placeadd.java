package com.placemap.placemap;

import com.google.api.client.util.Key;

import java.io.Serializable;

/**
 * Created by AKHIL on 03-May-2016.
 */
public class placeadd implements Serializable {
    @Key
    public String status;

    @Key
    public String place_id;

    @Key
    public String scope;

    @Key
    public String reference;

    @Key
    public String id;
}
