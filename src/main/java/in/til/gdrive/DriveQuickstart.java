package in.til.gdrive;

import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class DriveQuickstart {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        System.out.println("Starting Upload.....");
       /**
        upload("feb2.csv",
                "/Users/c-anubhav.sippy/Desktop/feb.csv",
                "text/csv"
        );
       */
    // how to call
        String march = "/Users/c-anubhav.sippy/Desktop/march.csv";
        java.io.File uploadFile = new java.io.File(march);
        GDriveService gDriveService = new GDriveService();
        gDriveService.uploadCsv(uploadFile);

        System.out.println("Finished Uploading.....");

        System.out.println("Reading File List");
        System.out.println("******************************************");
        //getList();
        System.out.println("******************************************");
    }

    private static void getList() throws IOException, GeneralSecurityException {
        GDriveService gDriveService = new GDriveService();
        List<File> files = gDriveService.getList();
        System.out.println("Files:");
        for (File file : files) {
            System.out.printf("%s (%s)\n", file.getName(), file.getId());
        }
    }

    public static void upload(String driveFileName, String inputFile, String mimeType) throws IOException, GeneralSecurityException {
        GDriveService gDriveService = new GDriveService();
        gDriveService.upload(driveFileName, inputFile, mimeType);
    }

}