import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class bai11_docFile2phan {
    public static class MonHoc {
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

        public SinhVien(int Mssv, String Ten, int Tuoi, ArrayList<MonHoc> listMH) {
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

    public static void loadSV(String src) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(src)));
        int size = dis.readInt();
        ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();
        for (int i = 0; i < size; i++) {
            int mssv = dis.readInt();
            String name = dis.readUTF();
            int age = dis.readInt();
            int sizemh = dis.readInt();
            ArrayList<MonHoc> listMH = new ArrayList<MonHoc>();
            for (int j = 0; j < sizemh; j++) {
                String tenMonHoc = dis.readUTF();
                int tinChi = dis.readInt();
                double diem = dis.readDouble();
                MonHoc mh1 = new MonHoc(tenMonHoc, tinChi, diem);
                listMH.add(mh1);
            }
            listSV.add(new SinhVien(mssv, name, age, listMH));
        }
        for (SinhVien sv : listSV) {
            System.out.println(sv.toString());
        }
        dis.close();
    }

    public static void main(String[] args) throws IOException {
        loadSV("");
    }
}
