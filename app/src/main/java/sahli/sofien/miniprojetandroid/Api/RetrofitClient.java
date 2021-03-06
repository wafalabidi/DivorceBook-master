package sahli.sofien.miniprojetandroid.Api;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sofien on 02/11/2017.
 */

public class RetrofitClient extends AppCompatActivity {

  //  private static final String BASE_URL ="http://172.16.153.116:8888/DivroceBook/";
    private static final String BASE_URL ="http://172.19.6.94:8888/DivroceBook/";
    protected Retrofit retrofit;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}


