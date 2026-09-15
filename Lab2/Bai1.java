package Lab2;

public class Bai1 {
    public static  class Sanpham {
        int maSanPham;
        String tenSanPham;
        int donGia;
        int soLuongTonKho;

        public Sanpham(int maSanPham, String tenSanPham, int donGia, int soLuongTonKho) {
            this.maSanPham = maSanPham;
            this.tenSanPham = tenSanPham;
            this.donGia = donGia;
            this.soLuongTonKho = soLuongTonKho;
        }

        public int getMaSanPham() {
            return maSanPham;
        }

        public void setMaSanPham(int maSanPham) {
            this.maSanPham = maSanPham;
        }

        public String getTenSanPham() {
            return tenSanPham;
        }

        public void setTenSanPham(String tenSanPham) {
            this.tenSanPham = tenSanPham;
        }

        public int getDonGia() {
            return donGia;
        }

        public void setDonGia(int donGia) {
            this.donGia = donGia;
        }

        public int getsoLuongTonKho() {
            return soLuongTonKho;
        }

        public void setsoLuongTonKho(int soLuongTonKho) {
            this.soLuongTonKho = soLuongTonKho;
        }

        public double tinhThanhTien() {
            return donGia * soLuongTonKho;
        }

        public void nhapHang(int soLuongTonKhoNhap) {
            if (soLuongTonKhoNhap <= 0)
                System.out.println("So luong hang hoa nhap vao phai lon hon 0");
            soLuongTonKho += soLuongTonKhoNhap;
        }

        public boolean banHang(int soLuongBan) {
            if (soLuongBan <= 0) {
                System.out.println("So luong ban phai lon hon 0");
                return false;

            }
            if (soLuongBan > soLuongTonKho) {
                System.out.println("So luong ban vuot qua so luong ton kho");
                return false;
            }
            soLuongTonKho -= soLuongBan;
            return true;
        }

        public void hienThiThongTin(){
            System.out.println("San Pham " + tenSanPham + " co ma la " + maSanPham + " co don gia " + donGia + " va so luon ton kho la " + soLuongTonKho);
        }
    }

    public static void main(String[] args) {
        Sanpham sp = new Sanpham(762863, "Chuột razer Orochi v2", 800000, 3);
        Sanpham sp1 = new Sanpham(892479, "Bàn phím AttackShark HE", 2000000, 10);
        sp.nhapHang(9);
        sp1.nhapHang(10);
        sp.banHang(2);
        sp1.banHang(100);
        sp.hienThiThongTin();
        sp1.hienThiThongTin();
    }
}
