import java.text.DecimalFormat;
import java.util.ArrayList;

public class Method_Naive_Bayes {
    
    DecimalFormat df = new DecimalFormat("#.##");
    
    // Paramater untuk perhitungan probabilitas data baru dengan data yang lama
    double[] pDataBoleh = new double[7], pDataTidak = new double[7];
    double[] pDataBolehHemoglobin = new double[3], pDataTidakHemoglobin = new double[3];
    double[] pDataBolehTensiDarah = new double[3], pDataTidakTensiDarah = new double[3];
    double[] pDataBolehBeratBadan = new double[3], pDataTidakBeratBadan = new double[3];
    double[] pDataBolehUsia = new double[3], pDataTidakUsia = new double[3];
    double[] pDataBolehJenisKelamin = new double[2], pDataTidakJenisKelamin = new double[2];
    double[] pDataBolehRiwayatPenyakitMenular = new double[2], pDataTidakRiwayatPenyakitMenular = new double[2];
    double totalPBoleh = 1, totalPTidak = 1;
    
    // Fungsi untuk menghitung probabilitas klasifikasi untuk boleh dan tidak boleh dengan parameter data yang ada
    public void ProbabilitasKlasifikasi(ArrayList<Data> data)
    {
        // Parameter untuk klasifikasi
        int boleh = 0, tidak = 0, total;
        double pBoleh, pTidak;
    
        // Proses pengecekan klasifikasi boleh dan tidak
        for (Data ds : data) 
        {
            if (ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh++;
            } else {
                tidak++;
            }
        }      
        
        // Proses perhitungan probabilitas klasifikasi
        total = boleh + tidak;           
        pBoleh = (double) boleh/total;
        pTidak = (double) tidak/total;
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        pDataBoleh[0] = pBoleh;
        pDataTidak[0] = pTidak;
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Klasifikasi");
        System.out.println("------------------------------------------------------------");
        System.out.println("Klasifikasi | Jumlah | Probalitasi");
        System.out.println("Boleh       \t" + boleh + "\t" + df.format(pBoleh));
        System.out.println("Tidak Boleh \t" + tidak + "\t" + df.format(pTidak));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total       \t" + total);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitungan probabilitas hemoglobin
    public void ProbabilitasHemoglobin(ArrayList<Data> data)
    {   
        // Paramater untuk hemoglobin
        int[] boleh = new int[3], tidak = new int[3];
        double[] pBoleh = new double[3], pTidak = new double[3];
        int totalBoleh = 0, totalTidak = 0;
        
        // Proses pengecekan hemoglobin untuk klasifikasi boleh dan tidak 
        for (Data ds : data) 
        {
            if (ds.hemoglobin < 12 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.hemoglobin >= 12 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            else if (ds.hemoglobin > 20 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[2]++;
            }
            //------------------------------------------------------------------//
            if (ds.hemoglobin < 12 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.hemoglobin >= 12 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
            else if (ds.hemoglobin > 20 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[2]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk hemoglobin
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehHemoglobin[i] = pBoleh[i];
            pDataTidakHemoglobin[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Hemoglobin");
        System.out.println("------------------------------------------------------------");
        System.out.println("Tensi Darah     \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  < 12          \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println(" 12 - 20        \t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("  > 20          \t  " + boleh[2] + "\t\t" + tidak[2] + "\t" + df.format(pBoleh[2]) + "\t\t" + df.format(pTidak[2]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas tensi darah
    public void ProbabilitasTensiDarah(ArrayList<Data> data)
    {
        // Paramater untuk tensi darah
        int[] boleh = new int[3], tidak = new int[3];
        double[] pBoleh = new double[3], pTidak = new double[3];
        int totalBoleh = 0, totalTidak=  0;
        
        // Proses pengecekan tensi darah untuk klasifikasi boleh dan tidak         
        for (Data ds : data) 
        {
            if (ds.tensiDarah < (100/70) && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.tensiDarah >= (100/70) && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            else if (ds.tensiDarah > (150/100) && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[2]++;
            }
            //------------------------------------------------------------------//
            if (ds.tensiDarah < (100/70) && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.tensiDarah >= (100/70) && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
            else if (ds.tensiDarah > (150/100) && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[2]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk tensi darah
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }       
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehTensiDarah[i] = pBoleh[i];
            pDataTidakTensiDarah[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Tensi Darah");
        System.out.println("------------------------------------------------------------");
        System.out.println("Tensi Darah     \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  < 100/70      \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println("100/70 - 150/100\t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("  > 150/100     \t  " + boleh[2] + "\t\t" + tidak[2] + "\t" + df.format(pBoleh[2]) + "\t\t" + df.format(pTidak[2]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas berat badan
    public void ProbabilitasBeratBadan(ArrayList<Data> data)
    {
        // Paramater untuk berat badan
        int[] boleh = new int[3], tidak = new int[3];
        double[] pBoleh = new double[3], pTidak = new double[3];
        int totalBoleh = 0, totalTidak=  0;
        
        // Proses pengecekan berat badan untuk klasifikasi boleh dan tidak 
        for (Data ds : data) 
        {
            if (ds.beratBadan < 45 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.beratBadan >= 45 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            else if (ds.beratBadan > 70 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[2]++;
            }
            //------------------------------------------------------------------//
            if (ds.beratBadan < 45 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.beratBadan >= 45 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
            else if (ds.beratBadan > 70 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[2]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk berat badan
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }       
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehBeratBadan[i] = pBoleh[i];
            pDataTidakBeratBadan[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Berat Badan");
        System.out.println("------------------------------------------------------------");
        System.out.println("Berat Badan     \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  < 45          \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println(" 45 - 70        \t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("  > 70          \t  " + boleh[2] + "\t\t" + tidak[2] + "\t" + df.format(pBoleh[2]) + "\t\t" + df.format(pTidak[2]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas usia
    public void ProbabilitasUsia(ArrayList<Data> data)
    {
        // Paramater untuk usia
        int[] boleh = new int[3], tidak = new int[3];
        double[] pBoleh = new double[3], pTidak = new double[3];
        int totalBoleh = 0, totalTidak=  0;
        
        // Proses pengecekan usia untuk klasifikasi boleh dan tidak 
        for (Data ds : data) 
        {
            if (ds.usia < 18 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.usia >= 18 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            else if (ds.usia > 30 && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[2]++;
            }
            //------------------------------------------------------------------//
            if (ds.usia < 18 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.usia >= 18 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
            else if (ds.usia > 30 && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[2]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk usia
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }       
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehUsia[i] = pBoleh[i];
            pDataTidakUsia[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Usia");
        System.out.println("------------------------------------------------------------");
        System.out.println("Usia            \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  < 18          \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println(" 18 - 30        \t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("  > 30          \t  " + boleh[2] + "\t\t" + tidak[2] + "\t" + df.format(pBoleh[2]) + "\t\t" + df.format(pTidak[2]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas jenis kelamin
    public void ProbabilitasJenisKelamin(ArrayList<Data> data)
    {
        // Paramater untuk jenis kelamin
        int[] boleh = new int[2], tidak = new int[2];
        double[] pBoleh = new double[2], pTidak = new double[2];
        int totalBoleh = 0, totalTidak=  0;
        
        // Proses pengecekan jenis kelamin untuk klasifikasi boleh dan tidak 
        for (Data ds : data) 
        {
            if (ds.jenisKelamin.equalsIgnoreCase("Pria") && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.jenisKelamin.equalsIgnoreCase("Wanita")&& ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            //------------------------------------------------------------------//
            if (ds.jenisKelamin.equalsIgnoreCase("Pria") && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.jenisKelamin.equalsIgnoreCase("Wanita") && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk jenis kelamin
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }       
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehJenisKelamin[i] = pBoleh[i];
            pDataTidakJenisKelamin[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Jenis Kelamin");
        System.out.println("------------------------------------------------------------");
        System.out.println("Jenis Kelmain   \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  Pria          \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println(" Wanita         \t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas riwayat penyakit menular
    public void ProbabilitasRiwayatPenyakitMenular(ArrayList<Data> data)
    {
        // Paramater untuk riwayat penyakit menular
        int[] boleh = new int[2], tidak = new int[2];
        double[] pBoleh = new double[2], pTidak = new double[2];
        int totalBoleh = 0, totalTidak=  0;
        
        // Proses pengecekan riwayat penyakit menular untuk klasifikasi boleh dan tidak 
        for (Data ds : data) 
        {
            if (ds.riwayatPenyakitMenular.equalsIgnoreCase("Iya") && ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[0]++;
            } 
            else if (ds.riwayatPenyakitMenular.equalsIgnoreCase("Tidak")&& ds.klasifikasi.equalsIgnoreCase("Boleh")) {
                boleh[1]++;
            }
            //------------------------------------------------------------------//
            if (ds.riwayatPenyakitMenular.equalsIgnoreCase("Iya") && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[0]++;
            } 
            else if (ds.riwayatPenyakitMenular.equalsIgnoreCase("Tidak") && ds.klasifikasi.equalsIgnoreCase("Tidak")) {
                tidak[1]++;
            }
        }
        
        // Proses perhitungan total klasifikasi boleh dan tidak untuk riwayat penyakit menular
        for (int i = 0; i < boleh.length; i++) 
        {
            totalBoleh += boleh[i];
            totalTidak += tidak[i];
        }
        
        // Proses perhitungan probabilitas boleh dan tidak
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pBoleh[i] = (double) boleh[i] / totalBoleh;
            pTidak[i] = (double) tidak[i] / totalTidak;
        }       
        
        // Proses penyimpanan probabilitas boleh dan tidak data lama untuk digunakan dengan data baru
        for (int i = 0; i < pBoleh.length; i++) 
        {            
            pDataBolehRiwayatPenyakitMenular[i] = pBoleh[i];
            pDataTidakRiwayatPenyakitMenular[i] = pTidak[i];
        }
        
        // Menampilkan data
        System.out.println("\nTabel Probabilitas Riwayat Penyakit Menular");
        System.out.println("------------------------------------------------------------");
        System.out.println("Riwayat         \t| Boleh | Tidak Boleh | P(Boleh) | P(Tidak Boleh)");
        System.out.println("  Iya           \t  " + boleh[0] + "\t\t" + tidak[0] + "\t" + df.format(pBoleh[0]) + "\t\t" + df.format(pTidak[0]));
        System.out.println(" Tidak          \t  " + boleh[1] + "\t\t" + tidak[1] + "\t" + df.format(pBoleh[1]) + "\t\t" + df.format(pTidak[1]));
        System.out.println("------------------------------------------------------------");
        System.out.println("Total           \t  " + totalBoleh + "\t\t"+ totalTidak);
        System.out.println("------------------------------------------------------------");
    }
    
    // Fungsi untuk menghitung probabilitas klasifikasi data yang baru dengan data yang sudah ada
    public void ProbabilitasDataBaruDenganDataYangAda(Data data)
    {
        ProbabilitasDataBaruHemoglobin(data);
        ProbabilitasDataBaruTensiDarah(data);
        ProbabilitasDataBaruBeratBadan(data);
        ProbabilitasDataBaruUsia(data);
        ProbabilitasDataBaruJenisKelamin(data);
        ProbabilitasDataBaruRiwayatPenyakitMenular(data);
        
        PenentuanKlasifikasiDataBaru();
    }
    
    private void ProbabilitasDataBaruHemoglobin(Data data)
    {
        // Parameter untuk data hemoglogin yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan hemoglobin data yang baru dengan data yang sudah ada
        if (data.hemoglobin < 12) {
            pBoleh = pDataBolehHemoglobin[0];
            pTidak = pDataTidakHemoglobin[0];
        } 
        else if (data.hemoglobin >= 12) {
            pBoleh = pDataBolehHemoglobin[1];
            pTidak = pDataTidakHemoglobin[1];
        }
        else if (data.hemoglobin > 20) {
            pBoleh = pDataBolehHemoglobin[2];
            pTidak = pDataTidakHemoglobin[2];
        }  
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk homglobin
        pDataBoleh[1] = pBoleh;
        pDataTidak[1] = pTidak;
    }
    
    private void ProbabilitasDataBaruTensiDarah(Data data)
    {
        // Parameter untuk data tensi darah yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan tensi darah data yang baru dengan data yang sudah ada
        if (data.tensiDarah < (100/70)) {
            pBoleh = pDataBolehTensiDarah[0];
            pTidak = pDataTidakTensiDarah[0];
        } 
        else if (data.tensiDarah >= (100/70)) {
            pBoleh = pDataBolehTensiDarah[1];
            pTidak = pDataTidakTensiDarah[1];
        }
        else if (data.tensiDarah > (150/100)) {
            pBoleh = pDataBolehTensiDarah[2];
            pTidak = pDataTidakTensiDarah[2];
        }
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk tensi darah
        pDataBoleh[2] = pBoleh;
        pDataTidak[2] = pTidak;
    }
    
    private void ProbabilitasDataBaruBeratBadan(Data data)
    {
        // Parameter untuk data berat badan yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan berat badan data yang baru dengan data yang sudah ada
        if (data.beratBadan < 45) {
            pBoleh = pDataBolehBeratBadan[0];
            pTidak = pDataTidakBeratBadan[0];
        } 
        else if (data.beratBadan >= 45) {
            pBoleh = pDataBolehBeratBadan[1];
            pTidak = pDataTidakBeratBadan[1];
        }
        else if (data.beratBadan > 70) {
            pBoleh = pDataBolehBeratBadan[2];
            pTidak = pDataTidakBeratBadan[2];
        }
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk berat badan
        pDataBoleh[3] = pBoleh;
        pDataTidak[3] = pTidak;
    }
    
    private void ProbabilitasDataBaruUsia(Data data)
    {
        // Parameter untuk data berat badan yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan berat badan data yang baru dengan data yang sudah ada
        if (data.usia < 18) {
            pBoleh = pDataBolehUsia[0];
            pTidak = pDataTidakUsia[0];
        } 
        else if (data.usia >= 18) {
            pBoleh = pDataBolehUsia[1];
            pTidak = pDataTidakUsia[1];
        }
        else if (data.usia > 30) {
            pBoleh = pDataBolehUsia[2];
            pTidak = pDataTidakUsia[2];
        }       
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk berat badan
        pDataBoleh[4] = pBoleh;
        pDataTidak[4] = pTidak;
    }
    
    private void ProbabilitasDataBaruJenisKelamin(Data data)
    {
        // Parameter untuk data berat badan yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan berat badan data yang baru dengan data yang sudah ada
        if (data.jenisKelamin.equalsIgnoreCase("Pria")) {
            pBoleh = pDataBolehJenisKelamin[0];
            pTidak = pDataTidakJenisKelamin[0];
        } 
        else if (data.jenisKelamin.equalsIgnoreCase("Wanita")) {
            pBoleh = pDataBolehJenisKelamin[1];
            pTidak = pDataTidakJenisKelamin[1];
        }
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk berat badan
        pDataBoleh[5] = pBoleh;
        pDataTidak[5] = pTidak;
    }
    
    private void ProbabilitasDataBaruRiwayatPenyakitMenular(Data data)
    {
        // Parameter untuk data berat badan yang baru
        double pBoleh = 0, pTidak = 0;
        
        // Proses pengecekan berat badan data yang baru dengan data yang sudah ada
        if (data.riwayatPenyakitMenular.equalsIgnoreCase("Iya")) {
            pBoleh = pDataBolehRiwayatPenyakitMenular[0];
            pTidak = pDataTidakRiwayatPenyakitMenular[0];
        } 
        else if (data.riwayatPenyakitMenular.equalsIgnoreCase("Tidak")) {
            pBoleh = pDataBolehRiwayatPenyakitMenular[1];
            pTidak = pDataTidakRiwayatPenyakitMenular[1];
        }
        
        // Proses penyimpanan data probabilitas boleh dan tidak untuk berat badan
        pDataBoleh[6] = pBoleh;
        pDataTidak[6] = pTidak;
    }
    
    private void PenentuanKlasifikasiDataBaru()
    {
        System.out.println("\nKlasifikasi Data Baru \nProbabilitas Boleh | Probabilitas Tidak");
        
        for (int i = 0; i < pDataBoleh.length; i++) 
        {            
            totalPBoleh *= pDataBoleh[i];
            totalPTidak *= pDataTidak[i];
            
            System.out.println("\t" + df.format(pDataBoleh[i]) + "\t\t" + df.format(pDataTidak[i]));            
        }
        
        System.out.println("---------------------------X");
        System.out.println("Total: " + df.format(totalPBoleh) + "\t\t" + df.format(totalPTidak));
        
        if (totalPBoleh > totalPTidak) {
            System.out.println("\nKet : Termasuk Klasifikasi Boleh");
        } else {
            System.out.println("\nKet :Termasuk Klasifikasi Tidak Boleh");
        }
    }
    
}
