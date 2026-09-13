import java.io.File;

public class bai8_findFile  {
    public void findFile(String source, String key){
        File file = new File(source);
        if(file.exists()){
            if(file.isFile()){
                if(file.getName().endsWith(key)){
                    System.out.println(file.getAbsolutePath());
                }
            }
            File[] listFiles = file.listFiles();
            if(listFiles != null){
                for(File f: listFiles){
                    findFile(f.getAbsolutePath(),key);
                }
            }else{
                System.out.print("Source khong ton tai");
            }
        }
    }
}
    