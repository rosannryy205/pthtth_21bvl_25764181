import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class bai9_copyFile {
    public boolean copyFile(String source, String dest) throws FileNotFoundException, IOException{
        //file nguon
        File sourceFile = new File(source);
        //file dich
        File destFile = new File(dest);
        // kiem tra nguon co ton tai khong
        if(sourceFile.exists()){
            //luong doc file
            FileInputStream fis = new FileInputStream(sourceFile);
            //luong ghi file
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] arr = new byte[1024];
            while ((fis.read(arr)) != -1) {
                fos.write(arr);
                fos.flush();
            }
            fis.close();
            fos.close();
            System.out.print("Copy thanh cong");
            return true;
        } else {
            System.out.print("File khong ton tai");
            return false;
        }
    }
}
