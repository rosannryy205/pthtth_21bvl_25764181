package Lab2;

import java.time.Year;

public class Bai2 {

    public static  class Nguoi {
        private String hoTen;
        private int namSinh;
        private String diaChi;

        public Nguoi(String hoTen, int namSinh, String diaChi) {
            this.hoTen = hoTen;
            this.namSinh = namSinh;
            this.diaChi = diaChi;
        }

        public String getHoTen() {
            return hoTen;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }

        public int getNamSinh() {
            return namSinh;
        }

        public void setNamSinh(int namSinh) {
            this.namSinh = namSinh;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }

        public int tinhTuoi() {
            int currentYear = Year.now().getValue();
            return currentYear - namSinh;
        }

        public void hienThiThongTin() {
            System.out.printf("Họ tên: %-18s | Tuổi: %-2d | Địa chỉ: %-15s", hoTen, tinhTuoi(), diaChi);
        }
    }

    public static class SinhVien extends Nguoi {
        private String maSinhVien;
        private String nganhHoc;
        private double diemTrungBinh;

        public SinhVien(String hoTen, int namSinh, String diaChi, String maSinhVien, String nganhHoc,
                double diemTrungBinh) {
            super(hoTen, namSinh, diaChi);
            this.maSinhVien = maSinhVien;
            this.nganhHoc = nganhHoc;
            this.diemTrungBinh = diemTrungBinh;
        }

        public String xepLoai() {
            if (diemTrungBinh >= 8.5)
                return "Giỏi";
            if (diemTrungBinh >= 7.0)
                return "Khá";
            if (diemTrungBinh >= 5.0)
                return "Trung bình";
            return "Yếu";
        }

        @Override
        public void hienThiThongTin() {
            super.hienThiThongTin();
            System.out.printf(" | Mã SV: %-6s | Ngành: %-10s | ĐTB: %-4.1f | Xếp loại: %s\n",
                    maSinhVien, nganhHoc, diemTrungBinh, xepLoai());
        }
    }

    public static class GiangVien extends Nguoi {
        private String maGiangVien;
        private String chuyenMon;
        private double luongCoBan;
        private double heSoLuong;

        public GiangVien(String hoTen, int namSinh, String diaChi, String maGiangVien, String chuyenMon, double luongCoBan, double heSoLuong){
            super(hoTen, namSinh, diaChi);
            this.maGiangVien = maGiangVien;
            this.chuyenMon = chuyenMon;
            this.luongCoBan = luongCoBan;
            this.heSoLuong = heSoLuong;
        }

        public String getMaGiangVien() {
            return maGiangVien;
        }

        public void setMaGiangVien(String maGiangVien) {
            this.maGiangVien = maGiangVien;
        }

        public String getChuyenMon() {
            return chuyenMon;
        }

        public void setChuyenMon(String chuyenMon) {
            this.chuyenMon = chuyenMon;
        }

        public double getLuongCoBan() {
            return luongCoBan;
        }

        public void setLuongCoBan(double luongCoBan) {
            this.luongCoBan = luongCoBan;
        }

        public double getHeSoLuong() {
            return heSoLuong;
        }

        public void setHeSoLuong(double heSoLuong) {
            this.heSoLuong = heSoLuong;
        }

        public double tinhLuong(){
            return luongCoBan * heSoLuong;
        }

        @Override
        public void hienThiThongTin() {
            super.hienThiThongTin();
        }
    }

    public static void main(String[] args) {
        SinhVien sv = new SinhVien("Võ Minh Khánh", 2005, "ấp 1, Mỹ An, Tây Ninh", "25764181", "Công nghệ thông tin", 9.0);
        sv.hienThiThongTin();
    }
}