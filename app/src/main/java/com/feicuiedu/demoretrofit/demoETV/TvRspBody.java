package com.feicuiedu.demoretrofit.demoETV;

import java.util.List;

public class TvRspBody<T> {

    int error_code;
    String reason;
    List<T> result;

}
