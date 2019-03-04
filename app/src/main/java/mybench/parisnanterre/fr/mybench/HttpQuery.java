package mybench.parisnanterre.fr.mybench;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpQuery {

    private OkHttpClient client = new OkHttpClient();

    private String ENDPOINT = "https://mybench.000webhostapp.com/service.php";

    public HttpQuery(){

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    // Formation d'une url pour envoyer les données vers la BDD
    public String Create(String url, String BENCH_TITLE, String BENCH_POSITION, String BENCH_ENVIRONNEMENT, String BENCH_POLLUTION, String BENCH_BRUIT) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action", url)
                .addFormDataPart("BENCH_TITLE", BENCH_TITLE)
                .addFormDataPart("BENCH_POSITION", BENCH_POSITION)
                .addFormDataPart("BENCH_ENVIRONNEMENT", BENCH_ENVIRONNEMENT)
                .addFormDataPart("BENCH_POLLUTION", BENCH_POLLUTION)
                .addFormDataPart("BENCH_BRUIT", BENCH_BRUIT)
                .build();

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String Read(String url) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action", url)
                .build();

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }





}