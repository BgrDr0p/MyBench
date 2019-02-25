package mybench.parisnanterre.fr.mybench;

package mybench.parisnanterre.fr.mybench;



import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.lang.String;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpBench{

    private OkHttpClient client = new OkHttpClient();

    private String SERVEUR = "https://mybench.000webhostapp.com/service.php";
    

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String Create(String url, String title, String snippet, String lat, String lng) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action", url)
                .addFormDataPart("title", title)
                .addFormDataPart("snippet", snippet)
                .addFormDataPart("lat", lat)
                .addFormDataPart("lng",lng)
                .build();

        Request request = new Request.Builder()
                .url(SERVEUR)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}


