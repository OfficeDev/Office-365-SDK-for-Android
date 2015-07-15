package com.microsoft.samples.onenotesample;

import com.google.common.collect.Iterators;

import java.util.Arrays;

public class Constants {
    final static public String CLIENT_ID = "00000000441364FF"; // get your own please
    final static public String[] SCOPES = {
            "wl.signin",
            "wl.basic",
            "wl.offline_access",
            "wl.skydrive_update",
            "wl.contacts_create",
            "office.onenote_create"
    };
    final static public String ONENOTE_API_ROOT = "https://www.onenote.com/api/v1.0";
}
