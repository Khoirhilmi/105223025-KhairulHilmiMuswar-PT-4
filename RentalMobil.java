// Kelas Mobil
class Mobil {
    private String nomorPlat;
    private String merek;
    private double hargaSewaPerHari;
    private boolean tersedia;

    // Konstruktor default
    public Mobil() {
        this("", "", 0.0, true);
    }

    // Konstruktor berparameter
    public Mobil(String nomorPlat, String merek, double hargaSewaPerHari, boolean tersedia) {
        this.nomorPlat = nomorPlat;
        this.merek = merek;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.tersedia = tersedia;
    }

    public void tampilkanInfo() {
        System.out.println("Nomor Plat: " + nomorPlat);
        System.out.println("Merek: " + merek);
        System.out.println("Harga Sewa per Hari: " + Utility.formatMataUang(hargaSewaPerHari));
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Tidak Tersedia"));
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void ubahStatus(boolean status) {
        this.tersedia = status;
    }

    public double getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }
}

// Kelas Pelanggan
class Pelanggan {
    private String nama;
    private String nomorKTP;
    private String nomorHP;

    public Pelanggan(String nama, String nomorKTP, String nomorHP) {
        this.nama = nama;
        this.nomorKTP = nomorKTP;
        this.nomorHP = nomorHP;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Nomor KTP: " + nomorKTP);
        System.out.println("Nomor HP: " + nomorHP);
    }
}

// Kelas Sewa
class Sewa {
    private Pelanggan pelanggan;
    private Mobil mobil;
    private int durasi;
    private double totalBiaya;

    public Sewa(Pelanggan pelanggan, Mobil mobil, int durasi) {
        this.pelanggan = pelanggan;
        this.mobil = mobil;
        this.durasi = durasi;
        prosesPenyewaan();
    }

    private void prosesPenyewaan() {
        if (mobil.isTersedia()) {
            double biayaSebelumDiskon = mobil.getHargaSewaPerHari() * durasi;
            totalBiaya = Utility.hitungDiskon(biayaSebelumDiskon, durasi);
            mobil.ubahStatus(false);
            cetakStruk();
        } else {
            System.out.println("Transaksi gagal! Mobil tidak tersedia.");
        }
    }

    private void cetakStruk() {
        System.out.println("\n--- Struk Penyewaan ---");
        pelanggan.tampilkanInfo();
        mobil.tampilkanInfo();
        System.out.println("Durasi Sewa: " + durasi + " hari");
        System.out.println("Total Biaya: " + Utility.formatMataUang(totalBiaya));
        System.out.println("Status: Berhasil");
    }
}

// Kelas Utility
class Utility {
    public static double hitungDiskon(double biaya, int durasi) {
        return durasi > 5 ? biaya * 0.9 : biaya;
    }

    public static String formatMataUang(double jumlah) {
        return "Rp " + String.format("%,.2f", jumlah);
    }
}

// Kelas RentalMobil (pengganti Main)
public class RentalMobil {
    public static void main(String[] args) {
        // Membuat objek mobil
        Mobil mobil1 = new Mobil("B1234CD", "Toyota Avanza", 500000, true);
        Mobil mobil2 = new Mobil("D5678EF", "Honda Jazz", 600000, true);
        
        // Menampilkan daftar mobil
        System.out.println("\nDaftar Mobil yang Tersedia:");
        mobil1.tampilkanInfo();
        mobil2.tampilkanInfo();
        
        // Membuat objek pelanggan
        Pelanggan pelanggan1 = new Pelanggan("Hilmi", "1234567890123456", "081234567890");
        
        // Melakukan transaksi penyewaan
        Sewa sewa1 = new Sewa(pelanggan1, mobil1, 7);
        
        // Menampilkan status mobil setelah penyewaan
        System.out.println("\nStatus Mobil setelah penyewaan:");
        mobil1.tampilkanInfo();
    }
}