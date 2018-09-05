package ir.iotacademy.gardenbalcony;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nazgol on 9/2/2018.
 */

public class Data {
    ArrayList<Date> df=new ArrayList<Date>();
    ArrayList<String> serial=new ArrayList<String>();
    ArrayList<Integer> num=new ArrayList<Integer>();
    ArrayList<String> logsign=new ArrayList<String>();
    String url;

    public String get_count() throws JSONException {
        HttpGetRequest get = new HttpGetRequest();
        String json = null;
        try {
            json = get.execute(url+"&reuslts=1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject jo = new JSONObject(json);
        JSONArray a = jo.getJSONArray("feeds");
        JSONObject data = a.getJSONObject(1);
        String x=data.getString("entry_id");
        return x;

    }
    public void make_numericData () throws ParseException, JSONException {

        df.clear();
        num.clear();
        serial.clear();
        String j=get_count();


        HttpGetRequest get = new HttpGetRequest();
        String json = null;
        try {
            json = get.execute(url+"&reuslts="+j).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("feeds");
            Date d;
            String d2;
            String time;
            for (int i = 0; i < Integer.parseInt(j); i++) {
                //Toast.makeText(this,"nazgol",Toast.LENGTH_SHORT).show();
                JSONObject data = array.getJSONObject(i);
                serial.add(data.getString("field1"));

                // d = (Date) data.get("created_at");
                d2=data.getString("created_at");
                time=d2.substring(0,d2.indexOf("T"))+" "+d2.substring(d2.indexOf("T")+1,d2.indexOf("Z"));
                //Toast.makeText(this,time,Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date=time;
                //"22-06-2016 8:36:53"
                df.add(sdf.parse(date));
                // Toast.makeText(this, df.get(i).toString(),Toast.LENGTH_SHORT).show();



                if (serial.get(i).indexOf('.') != -1) {
                    serial.add(i, serial.get(i).substring(0, serial.indexOf('.')));
                }
                num.add( Integer.parseInt(serial.get(i)) + 1);
                //Toast.makeText(this,num.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        }
        catch(JSONException e){
            e.printStackTrace();

        }

    }



    public void make_stringData() throws JSONException {
        df.clear();
        logsign.clear();
        serial.clear();
        String j=get_count();



        try {

            JSONObject jsonObj = new JSONObject(url+"&reuslts="+j);
            JSONArray array = jsonObj.getJSONArray("feeds");
            Date d;
            String d2;
            String time;
            for (int i = 0; i < Integer.parseInt(j); i++) {
                //Toast.makeText(this,"nazgol",Toast.LENGTH_SHORT).show();
                JSONObject data = array.getJSONObject(i);
                serial.add(data.getString("field1"));

                // d = (Date) data.get("created_at");
                d2=data.getString("created_at");
                time=d2.substring(0,d2.indexOf("T"))+" "+d2.substring(d2.indexOf("T")+1,d2.indexOf("Z"));
                //Toast.makeText(this,time,Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date=time;
                //"22-06-2016 8:36:53"
                df.add(sdf.parse(date));
                // Toast.makeText(this, df.get(i).toString(),Toast.LENGTH_SHORT).show();


                logsign.add(serial.get(i));
                //Toast.makeText(this,num.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        }
        catch(JSONException e){
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public ArrayList get_data(String what, String Which) throws ParseException, JSONException { //what refers to name of the channels and which to lleft or right or the number of pots
        switch(what){
            case "username":
                url="http://thingtalk.ir/channels/695/feed.json?key=48A91L1B0NQRPR3I";
                make_stringData();
                return logsign;
            case "email":
                url="http://thingtalk.ir/channels/698/feed.json?key=X1I8YN8DQ696PUB4";
                make_stringData();
                return logsign;
            case "password":
                url="http://thingtalk.ir/channels/696/feed.json?key=OXFGAZT46L7TY4RP";
                make_stringData();
                return logsign;
            default:break;

        }
        return null;

    }
}
