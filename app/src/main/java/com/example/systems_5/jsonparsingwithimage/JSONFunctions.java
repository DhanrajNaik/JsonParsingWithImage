package com.example.systems_5.jsonparsingwithimage;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by DHANRAJ NAIK  on 13-07-2015.
 */
public class JSONFunctions {

    public static JSONObject getJsonFromUrl(String url){

        InputStream is =null ;
        String result ="";
        JSONObject jArray =null;

        //download JSON data from url

        try{
            HttpClient httpclient =new DefaultHttpClient();
            HttpPost httppost =new HttpPost(url);
            HttpResponse response =httpclient.execute(httppost);
            HttpEntity entity= response.getEntity();
            is=entity.getContent();

        }catch (Exception e){
            Log.e("log_tag","error in http connection :" + e.toString());

        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb =new StringBuilder();
            String line =null;
            while ((line=reader.readLine()) != null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }catch (Exception e){
            Log.e("log_tag","ERROR CONVERTING RESULT:"+e.toString());

        }

        try{
            jArray=new JSONObject(result);

        }catch (Exception e){
            Log.e("log_tag","ERROR PARSING DATA: "+e.toString());
        }


        return jArray;
    }
}
