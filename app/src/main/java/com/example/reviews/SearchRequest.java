package com.example.reviews;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://3.34.44.58/search.php";
    private Map<String, String> map;

    public SearchRequest(String key, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("key", key);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}