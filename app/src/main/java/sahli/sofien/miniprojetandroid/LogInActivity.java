package sahli.sofien.miniprojetandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sahli.sofien.miniprojetandroid.Api.RetrofitClient;
import sahli.sofien.miniprojetandroid.Api.UserService;
import sahli.sofien.miniprojetandroid.Entities.InscriptionBody;
import sahli.sofien.miniprojetandroid.Entities.Response.LoginResponse;
import sahli.sofien.miniprojetandroid.Entities.User;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.BLogin)
    Button bLogin ;
    @BindView(R.id.etPasswordLogin)
    EditText etPasswordLogin ;
    @BindView(R.id.etUsernameInscription)
    EditText etUsernameLogin ;
    @BindView(R.id.tvInscription)
    TextView tvInscription ;
    
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        bLogin.setOnClickListener(this);
        tvInscription.setOnClickListener(this);
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            etPasswordLogin.setText(bundle.getString("password"));
            etPasswordLogin.setText(bundle.getString("mail"));
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BLogin){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Processing ...");
            progressDialog.show();
            InscriptionBody user = new InscriptionBody() ;
            user.setMail(etUsernameLogin.getText().toString());
            user.setPassword(etPasswordLogin.getText().toString());
            RetrofitClient retrofitClient = new RetrofitClient() ;
            UserService.LoginInterface loginInterface = retrofitClient.getRetrofit().create(UserService.LoginInterface.class) ;
            Call<LoginResponse> call = loginInterface.userLogin(user);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.e("Ok" , response.body().getUser().toString());
                    progressDialog.dismiss();
                    Intent intent = new Intent( LogInActivity.this,  MainAcitvity.class) ;
                    startActivity(intent);
/////////////////////////////////////

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                }
            });

        }else if (v.getId() == R.id.tvInscription){
            Intent intent = new Intent(this, InscriptionAcitvity.class) ;
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

 private void TestUpload (){
        int i=1+1;
 }
}
