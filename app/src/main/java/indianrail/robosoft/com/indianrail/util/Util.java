package indianrail.robosoft.com.indianrail.util;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by deena on 6/5/16.
 */
public class Util {
    URL url;

    public static String httpUrlConnection(String Url) {
        HttpURLConnection httpURLConnection;
        String response = null;
        try {
            URL url = new URL(Url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            response = covertInputStreamToString(httpURLConnection.getInputStream()).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static StringBuffer covertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuffer data = new StringBuffer();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return data;
    }
}
