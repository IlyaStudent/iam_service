package com.social_media.iam_service.utils;

import com.social_media.iam_service.model.constants.ApiConstants;

public class ApiUtils {

    public static String getMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } catch (Exception e) {
            return ApiConstants.UNDEFINED;
        }
    }
}
