package com.mindhub.finalProject.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.HttpURLConnection;
import java.net.URL;

public final class APIUtils {

    private APIUtils(){}

    public static ResponseEntity<Object> apiConnection(String urlRequest){
        try {
            URL url = new URL(urlRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 201){
                return new ResponseEntity<>("El código de respuesta fue: " + responseCode, HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("El código de respuesta fue: " + responseCode, HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
