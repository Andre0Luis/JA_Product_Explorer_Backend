package br.com.ja_product_explorer_backend.service.impl;

import org.springframework.stereotype.Component;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Component
public class GoogleDriveServiceImpl {

    private Drive drive;

    public GoogleDriveServiceImpl() throws IOException {
        FileInputStream serviceAccountStream = new FileInputStream("src/main/resources/credenciais.json");

        GoogleCredential credential = GoogleCredential.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE_READONLY));

        drive = new Drive.Builder(
                credential.getTransport(),
                credential.getJsonFactory(),
                credential)
                .setApplicationName("MeuAppDrive")
                .build();
    }

    public Drive getDrive() {
        return drive;
    }
}
