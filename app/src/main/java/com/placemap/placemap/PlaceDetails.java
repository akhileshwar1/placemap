package com.placemap.placemap;

import java.io.Serializable;
import com.google.api.client.util.Key;

/**
 * Created by AKHIL on 11-Apr-2016.
 */
public class PlaceDetails implements Serializable {

    @Key
    public String status;

    @Key
    public place result;

    @Override
    public String toString() {
        if (result!=null) {
            return result.toString();
        }
        return super.toString();
    }
}

