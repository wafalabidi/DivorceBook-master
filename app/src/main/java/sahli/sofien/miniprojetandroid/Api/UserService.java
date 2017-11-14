package sahli.sofien.miniprojetandroid.Api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sahli.sofien.miniprojetandroid.Entities.ConfirmationResponse;
import sahli.sofien.miniprojetandroid.Entities.InscriptionBody;
import sahli.sofien.miniprojetandroid.Entities.Response.LoginResponse;
import sahli.sofien.miniprojetandroid.Entities.User;

/**
 * Created by sofien on 02/11/2017.
 */

public interface UserService {
   public interface RegisterInterface{
    @POST("registerUser.php")
    Call<ConfirmationResponse> studentLogin(@Body InscriptionBody inscriptionBody);
   }
   public interface LoginInterface {
       @POST("logIn.php")
       Call<LoginResponse> userLogin(@Body InscriptionBody InscriptionBody);
   }

}
