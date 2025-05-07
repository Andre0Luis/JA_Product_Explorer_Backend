package br.com.ja_product_explorer_backend.config;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.DateTime;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.io.InputStreamReader;

import static com.google.api.client.json.gson.GsonFactory.getDefaultInstance;

@Configuration
public class GoogleDriveConfig {

    private static final String APPLICATION_NAME = "luizinhos-house";
    private static final JsonFactory JSON_FACTORY = getDefaultInstance();

    @Bean
    public Drive driveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private Credential authorize() throws IOException {
        // Pega a variável de ambiente com as credenciais codificadas em Base64
        String encodedCredentials = System.getenv("GOOGLE_CREDENTIALS");

        if (encodedCredentials == null) {
            throw new IOException("As credenciais do Google não foram encontradas nas variáveis de ambiente");
        }

        // Decodifica a string Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);

        // Cria a credencial a partir do arquivo JSON decodificado
        GoogleCredential credential = GoogleCredential.fromStream(inputStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return credential;
    }
}
