package com.placemap.placemap;

import java.io.Serializable;
import java.util.List;
import com.google.api.client.util.Key;
/**
 * Created by AKHIL on 11-Apr-2016.
 */
public class PlacesList implements Serializable {

    @Key
    public String status;

    @Key
    public List<place> results;

}