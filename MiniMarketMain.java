import java.util.*;
import java.sql.*;

class Produk {
    private int id;
    private String nama;
    private double harga;

    public Produk(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}

interface Transaksi {
    double hitungTotal();
    void cetakStruk();
}

class Pembelian {
    protected List<Produk> produk;

    public Pembelian() {
        produk = new ArrayList<>();
    }

    public void tambahProduk(Produk produk) {
        this.produk.add(produk);
    }
}

class Penjualan extends Pembelian implements Transaksi {
    private java.util.Date tanggal;

    public Penjualan() {
        super();
        tanggal = new java.util.Date();
    }

    public double hitungTotal() {
        double total = 0;
        for (Produk produk : produk) {
            total += produk.getHarga();
        }
        return total;
    }

    public void cetakStruk() {
        System.out.println("Struk - " + tanggal);
        System.out.println("----------------------------");
        for (Produk produk : produk) {
            System.out.println(produk.getNama() + ": Rp " + produk.getHarga());
        }
        System.out.println("----------------------------");
        System.out.println("Total: Rp " + hitungTotal());
    }
}

class Karyawan {
    private int id;
    private String nama;
    private String posisi;

    public Karyawan(int id, String nama, String posisi) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }
}

class DatabaseKaryawan {
    private List<Karyawan> karyawan;

    public DatabaseKaryawan() {
        karyawan = new ArrayList<>();
    }

    public void tambahKaryawan(Karyawan karyawan) {
        this.karyawan.add(karyawan);
    }
}

public class MiniMarketMain {
    private static List<Produk> daftarProduk = new ArrayList<>();
    private static List<Karyawan> daftarKaryawan = new ArrayList<>();
    private static int idProdukCounter = 1;
    private static int idKaryawanCounter = 1;
    private static Scanner scanner = new Scanner(System.in);

    private static void tambahProduk() {
        System.out.println("Masukkan nama produk:");
        String nama = scanner.nextLine();
        System.out.println("Masukkan harga produk:");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        Produk produk = new Produk(idProdukCounter++, nama, harga);
        daftarProduk.add(produk);
        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void tampilkanProduk() {
        System.out.println("Daftar Produk:");
        for (Produk produk : daftarProduk) {
            System.out.println("ID: " + produk.getId() + ", Nama: " + produk.getNama() + ", Harga: " + produk.getHarga());
        }
    }

    private static void updateProduk() {
        tampilkanProduk();
        System.out.println("Masukkan ID produk yang akan diupdate:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produk produkToUpdate = null;
        for (Produk produk : daftarProduk) {
            if (produk.getId() == id) {
                produkToUpdate = produk;
                break;
            }
        }

        if (produkToUpdate != null) {
            System.out.println("Masukkan harga baru untuk produk " + produkToUpdate.getNama() + ":");
            double hargaBaru = scanner.nextDouble();
            scanner.nextLine();
            produkToUpdate.setHarga(hargaBaru);
            System.out.println("Produk berhasil diupdate.");
        } else {
            System.out.println("Produk dengan ID tersebut tidak ditemukan.");
        }
    }

    private static void hapusProduk() {
        tampilkanProduk();
        System.out.println("Masukkan ID produk yang akan dihapus:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Iterator<Produk> iterator = daftarProduk.iterator();
        while (iterator.hasNext()) {
            Produk produk = iterator.next();
            if (produk.getId() == id) {
                iterator.remove();
                System.out.println("Produk berhasil dihapus.");
                return;
            }
        }

        System.out.println("Produk dengan ID tersebut tidak ditemukan.");
    }

    private static void tambahKaryawan() {
        System.out.println("Masukkan nama karyawan:");
        String nama = scanner.nextLine();
        System.out.println("Masukkan posisi karyawan:");
        String posisi = scanner.nextLine();

        Karyawan karyawan = new Karyawan(idKaryawanCounter++, nama, posisi);
        daftarKaryawan.add(karyawan);
        System.out.println("Karyawan berhasil ditambahkan.");
    }

    private static void tampilkanKaryawan() {
        System.out.println("Daftar Karyawan:");
        for (Karyawan karyawan : daftarKaryawan) {
            System.out.println("ID: " + karyawan.getId() + ", Nama: " + karyawan.getNama() + ", Posisi: " + karyawan.getPosisi());
        }
    }

    public static void main(String[] args) {
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Tampilkan Produk");
            System.out.println("3. Update Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Tambah Karyawan");
            System.out.println("6. Tampilkan Karyawan");
            System.out.println("7. Keluar");
            System.out.println("Pilih menu:");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahProduk();
                    break;
                case 2:
                    tampilkanProduk();
                    break;
                case 3:
                    updateProduk();
                    break;
                case 4:
                    hapusProduk();
                    break;
                case 5:
                    tambahKaryawan();
                    break;
                case 6:
                    tampilkanKaryawan();
                    break;
                case 7:
                    berjalan = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
