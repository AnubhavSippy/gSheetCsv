package in.til.gdrive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GDriveService {

    public void upload(String driveFileName, java.io.File inputFile, String mimeType)
            throws IOException, GeneralSecurityException {
        Drive driveService = GDriveUtils.getDriveService();

        File fileMetadata = new File();
        fileMetadata.setName(driveFileName);

        FileContent mediaContent = new FileContent(mimeType, inputFile);

        File file = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());
    }

    public void upload(String driveFileName, String filesPath, String mimeType) throws IOException, GeneralSecurityException {
        java.io.File inputFile = new java.io.File(filesPath);
        upload(driveFileName, inputFile, mimeType);
    }

    public void uploadCsv(String driveFileName, java.io.File csvFile) throws IOException, GeneralSecurityException {
        upload(driveFileName, csvFile, "text/csv");
    }

    public void uploadCsv(java.io.File csvFile) throws IOException, GeneralSecurityException {
        String csvFileName = csvFile.getName();
        uploadCsv(csvFileName, csvFile);
    }


    public List<File> getList() throws IOException, GeneralSecurityException {
        Drive service = GDriveUtils.getDriveService();

        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
            return Collections.emptyList();
        }
        return files;
    }

}
