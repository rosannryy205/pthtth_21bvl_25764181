import java.io.File;
import java.io.IOException;

public class bai7_TH1_TH2_TH3 {
    private void deleteFile(String source) {
        // new file
        File file = new File(source);
        // check if file exists
        // new ton tai
        if (file.exists()) {
            System.out.println("File " + source + " tồn tại");
            file.delete();
            System.out.println("File " + source + " đã được xóa");
        } else {
            System.out.println("File " + source + " không tồn tại");
        }
    }

    // TH1: Delet thư mục rỗng
    public boolean deleteEmptyFolder(String source) {
        File folder = new File(source);
        // Kiểm tra nếu thư mục tồn tại và rỗng thì xóa
        if (folder.exists()) {
            folder.delete();
            System.out.println("Thư mục " + source + " đã được xóa");
            return true;
        } else {
            System.out.println("Thư mục " + source + " không tồn tại");
            return false;
        }
    }

    // TH2: Delet thư mục có chứa file
    public boolean deleteListFileInfolder(String source) {
        File folder = new File(source);
        // Kiểm tra nếu thư mục tồn tại và có chứa file thì xóa
        if (folder.exists()) {
            // danh sach file
            File[] listfiles = folder.listFiles();
            if (listfiles.length > 0) {
                for (File f : listfiles) {
                    // neu f la file thi xoa
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            }
            folder.delete();
            System.out.println("Thư mục " + source + " đã được xóa");
            return true;
        } else {
            System.out.println("Thư mục " + source + " không tồn tại");
            return false;
        }
    }

    // TH3: Delete thư mục chứa danh sách thư mục con và trong thư mục con chứa danh
    // sách file
    public class DeleteDirTH3 {
        public boolean deleteListFileInFolder(String source) throws IOException {
            File folder = new File(source);
            // folder tồn tại
            if (folder.exists()) {
                File[] listFiles = folder.listFiles();
                if (listFiles.length != 0) {
                    for (File f : listFiles) {
                        if (f.isFile()) {
                            f.delete();
                        }
                        if (f.isDirectory()) {
                            deleteListFileInFolder(f.getAbsolutePath());
                        }
                    }
                }
                folder.delete();
                System.out.println("Thư mục " + source + " đã được xóa");
                return true;
            } else {
                System.out.println("Thư mục " + source + " không tồn tại");
                return false;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        bai7_TH1_TH2_TH3 bai7 = new bai7_TH1_TH2_TH3();
        String source = "Lab1/bai6_Animal.java";
        bai7.deleteFile(source);

        DeleteDirTH3 deleteDirTH3 = bai7.new DeleteDirTH3();
        String source1 = "Lab1/Folder1";
        deleteDirTH3.deleteListFileInFolder(source1);
    }
}
