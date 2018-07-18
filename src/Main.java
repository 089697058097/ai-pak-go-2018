
import java.util.ArrayList;

public class Main 
{
    // Inisialisasi variable data dengan tipe arraylist dengan parameter data
    static ArrayList<Data> data = new ArrayList<>();
    static ArrayList<Data> listDataBaru = new ArrayList<>();
    
    // Inisialisasi variable perhitungan dari class Method_Naive_Bayes
    static Method_Naive_Bayes perhitungan = new Method_Naive_Bayes();
    
    // Inisialisasi data baru
    static Data data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;
    static Data dataBaru;     
    
    public static void main(String[] args) 
    {        
        System.out.println("Aplikasi Pengklasifikasian Pendonor Darah Dengan Metode Naive Bayes \n");
        
        data();
                
        perhitungan.ProbabilitasKlasifikasi(data);

        perhitungan.ProbabilitasHemoglobin(data);
        
        perhitungan.ProbabilitasTensiDarah(data);
        
        perhitungan.ProbabilitasBeratBadan(data);
        
        perhitungan.ProbabilitasUsia(data);
        
        perhitungan.ProbabilitasJenisKelamin(data);
        
        perhitungan.ProbabilitasRiwayatPenyakitMenular(data);
        
        dataBaru();
        
        perhitungan.ProbabilitasDataBaruDenganDataYangAda(dataBaru);
    }
    
    // Fungsi Data Sample
    static void data()
    {
        data1 = new Data(12.6, 137/80, 65, 21, "Pria", "Tidak", "Boleh");
        data2 = new Data(11.0, 90/60, 30, 16, "Wanita", "Tidak", "Tidak");
        data3 = new Data(12.0, 110/90, 50, 36, "Pria", "Tidak", "Boleh");
        data4 = new Data(15.0, 120/70, 45, 26, "Pria", "Tidak", "Boleh");
        data5 = new Data(11.2, 89/60, 35, 26, "Wanita", "Tidak", "Tidak");
        data6 = new Data(10.3, 70/60, 32, 22, "Wanita", "Tidak", "Tidak");
        data7 = new Data(14.0, 190/100, 50, 34, "Wanita", "Tidak", "Boleh");
        data8 = new Data(16.2, 150/89, 54, 43, "Pria", "Tidak", "Boleh");
        data9 = new Data(10.0, 79/56, 33, 17, "Pria", "Tidak", "Tidak");
        data10 = new Data(14.0, 190/140, 42, 27, "Pria", "Tidak", "Boleh");
        
        data.add(data1);
        data.add(data2);        
        data.add(data3);        
        data.add(data4);        
        data.add(data5);        
        data.add(data6);        
        data.add(data7);        
        data.add(data8);        
        data.add(data9);        
        data.add(data10);        
        
        System.out.println("Jumlah Data : " + data.size() + "\n");
        System.out.println("No | Hemoglobin | Berat Badan | Usia | Jenis Kelamin | Riwayat Penyakit Menular | Klasifikasi ");
        
        int no = 0;
        for (Data ds : data) {
            no++;
            System.out.println(no + "\t" + ds.hemoglobin + "\t\t" + ds.beratBadan + "\t" + ds.usia + "\t" + ds.jenisKelamin + "\t\t\t" + ds.riwayatPenyakitMenular + "\t\t\t" + ds.klasifikasi);
        }
    }
    
    // Fungsi Data Sample
    static void dataBaru()
    {
        dataBaru = new Data(13.0, 132/70, 70, 29, "Pria", "Tidak", "");
    }
}
