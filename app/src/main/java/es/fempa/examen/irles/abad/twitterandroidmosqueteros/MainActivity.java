package es.fempa.examen.irles.abad.twitterandroidmosqueteros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso 1: crear objeto OAuthService
        final OAuth10aService service = new ServiceBuilder("oPEcKBHQUQ0crgkwviXV0uhrp")
                .apiSecret("6kxuXeMRISPAJqy3GnqhQTBT6FP1jU2a6IJ5ASlzKQUj3uiUuQ")
                .build(TwitterApi.instance());
        //Paso 2: Obtener el token
        try {
            final OAuth1RequestToken requestToken = service.getRequestToken();
            //Paso 3: Validar el token
            String authUrl = service.getAuthorizationUrl(requestToken);

            //Paso 4: Obtener acceso al token
            final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, "https://www.google.es");

            //Paso 5: Aceptar la solicitud
            final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);
            System.out.println(response.getBody());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}