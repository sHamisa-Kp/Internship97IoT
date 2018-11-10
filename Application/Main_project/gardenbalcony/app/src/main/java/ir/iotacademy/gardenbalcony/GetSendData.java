package ir.iotacademy.gardenbalcony;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Nazgol on 10/17/2018.
 */

public class GetSendData {
String data;
    public GetSendData(){
        data=null;
    }

    public void SetActuator(String url,String status){

        if(status.equals("1")) {

            HttpGetRequest get=new HttpGetRequest();

            try {
                String js= get.execute(url).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else if (status.equals("0")){
            HttpGetRequest get=new HttpGetRequest();

            try {
                String js= get.execute(url).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public String GetData(String url) {


        HttpGetRequest get = new HttpGetRequest();
        try {
            String json = get.execute(url).get();
            // Temprature.setText(json);
            if (json != null) {
                String num = getSerial(json);
                data= num;
            } else {
                data= "No";
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getSerial(String json)
    {
        try {

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("feeds");
            JSONObject data = array.getJSONObject(0);
            String serial = data.getString("field1");
            if(serial.indexOf('.') != -1 )
            {
                serial = serial.substring(0, serial.indexOf('.'));
            }
            int num = Integer.parseInt(serial)+1;

            return serial;

        }
        catch (JSONException e) {
            e.printStackTrace();

        }
        return null;

    }
}
