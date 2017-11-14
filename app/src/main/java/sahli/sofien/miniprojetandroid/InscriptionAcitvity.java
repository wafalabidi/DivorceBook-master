package sahli.sofien.miniprojetandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sahli.sofien.miniprojetandroid.Api.RetrofitClient;
import sahli.sofien.miniprojetandroid.Api.UserService;
import sahli.sofien.miniprojetandroid.Entities.ConfirmationResponse;
import sahli.sofien.miniprojetandroid.Entities.InscriptionBody;
import sahli.sofien.miniprojetandroid.Entities.User;
import sahli.sofien.miniprojetandroid.Api.RetrofitClient;

public class InscriptionAcitvity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.etLastNameInscription)
    EditText etLastNameInscription;
    @BindView(R.id.etUsernameInscription)
    EditText etUsernameInscription;
    @BindView(R.id.BInscription)
    Button bInscription;
    @BindView(R.id.etPasswordInscription)

    EditText etPasswordInscription;
    @BindView(R.id.etPasswordConfirmInscription)
    EditText etPasswordConfirmInscription;
    @BindView(R.id.etMailInscription)
    EditText etMailInscription;
    @BindView(R.id.fb_login_btn)
    Button facebook;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_acitvity);
        ButterKnife.bind(this);
        bInscription.setOnClickListener(this);
        callbackManager = CallbackManagerImpl.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if ((!TextUtils.isEmpty(etLastNameInscription.getText().toString()))
                && (!TextUtils.isEmpty(etUsernameInscription.getText()))
                && (!TextUtils.isEmpty(etPasswordConfirmInscription.getText()))
                && (!TextUtils.isEmpty(etPasswordConfirmInscription.getText()))
                && (!TextUtils.isEmpty(etPasswordInscription.getText()))
                && (!TextUtils.isEmpty(etMailInscription.getText().toString()))
                ) {
            final InscriptionBody user = new InscriptionBody();
            user.setFirstName(etUsernameInscription.getText().toString());
            user.setLastName(etLastNameInscription.getText().toString());
            user.setPassword(etPasswordConfirmInscription.getText().toString());
            user.setMail(etMailInscription.getText().toString());
            RetrofitClient retrofitClient = new RetrofitClient();
            UserService.RegisterInterface registerInterface = retrofitClient.getRetrofit().create(UserService.RegisterInterface.class);
            Call<ConfirmationResponse> call = registerInterface.studentLogin(user);
            call.enqueue(new Callback<ConfirmationResponse>() {
                @Override
                public void onResponse(Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(InscriptionAcitvity.this , LogInActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("mail", user.getMail());
                        bundle.putString("password", user.getPassword());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InscriptionAcitvity.this);
                    dialog.setMessage("failed to register");
                    dialog.create().show();

                }
            });


        } else {
        }
    }

}
