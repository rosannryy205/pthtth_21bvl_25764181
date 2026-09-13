import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class bai10_ghiFile2phan {
    public static  class MonHoc {
        private String tenMonHoc;
        private int tinChi;
        private double diem;

        public MonHoc(String tenMonHoc, int tinChi, double diem) {
            this.tenMonHoc = tenMonHoc;
            this.tinChi = tinChi;
            this.diem = diem;

        }

        public String getTenMonHoc() {
            return tenMonHoc;
        }

        public void setTenMonHoc(String tenMonHoc) {
            this.tenMonHoc = tenMonHoc;
        }

        public int getTinChi() {
            return tinChi;
        }

        public void setTinChi(int tinChi) {
            this.tinChi = tinChi;
        }

        public double getDiem() {
            return diem;
        }

        public void setDiem(double diem) {
            this.diem = diem;
        }

    }

    public static class SinhVien {
        private int Mssv;
        private String Ten;
        private int Tuoi;
        private ArrayList<MonHoc> listMH;

        public SinhVien(int Mssv, String Ten, int Tuoi) {
            this.Mssv = Mssv;
            this.Ten = Ten;
            this.Tuoi = Tuoi;
            this.listMH = new ArrayList<>();
        }

        public int getMssv() {
            return Mssv;
        }

        public void setMssv(int mssv) {
            Mssv = mssv;
        }

        public String getTen() {
            return Ten;
        }

        public void setTen(String ten) {
            Ten = ten;
        }

        public int getTuoi() {
            return Tuoi;
        }

        public void setTuoi(int tuoi) {
            Tuoi = tuoi;
        }

        public ArrayList<MonHoc> getListMH() {
            return listMH;
        }

        public void setListMH(ArrayList<MonHoc> listMH) {
            this.listMH = listMH;
        }

    }

    public static void saveSV(String src, ArrayList<SinhVien> listSV) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(src)));
        dos.writeInt(listSV.size());
        for (SinhVien sv : listSV) {
            dos.writeInt((sv.getMssv()));
            dos.writeUTF((sv.getTen()));
            dos.writeInt(sv.getTuoi());
            dos.writeInt(sv.getListMH().size());
            for (MonHoc mh : sv.getListMH()) {
                dos.writeUTF(mh.getTenMonHoc());
                dos.writeInt(mh.getTinChi());
                dos.writeDouble(mh.getDiem());
            }
        }
        dos.flush();
        dos.close();
    }

    public static void main(String[] args) throws IOException {
        MonHoc mh = new MonHoc("An Ninh He Thong", 3, 9.0);
        MonHoc mh1 = new MonHoc("Lap Trinh Java", 4, 9.0);
        ArrayList<MonHoc>listMH = new ArrayList<>();
        listMH.add(mh1);
        listMH.add(mh);
        ArrayList<SinhVien>listSV = new ArrayList<>();
        SinhVien sv = new SinhVien(25764181, "Vo Minh Khanh", 21);
        SinhVien sv1 = new SinhVien(26784191, "Vo Bui Minh Khanh", 22);
        listSV.add(sv1);
        listSV.add(sv);
        saveSV("C:\\text.txt", listSV);

    }
}
