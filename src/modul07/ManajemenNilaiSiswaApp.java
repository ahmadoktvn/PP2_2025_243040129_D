package modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManajemenNilaiSiswaApp extends JFrame {

    
    // Variabel Global

    private JTextField inputNama;
    private JTextField inputNilai;
    private JComboBox<String> inputMatkul;

    private JTable tableData;
    private DefaultTableModel tableModel;

    private JTabbedPane tabPane;

    // ============================
    // Panel Input Data
    // ============================
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Nama Siswa:"));
        inputNama = new JTextField();
        panel.add(inputNama);

        panel.add(new JLabel("Nilai:"));
        inputNilai = new JTextField();
        panel.add(inputNilai);

        panel.add(new JLabel("Mata Kuliah:"));
        inputMatkul = new JComboBox<>(new String[]{
                "Pemrograman II",
                "Struktur Data",
                "Basis Data",
                "Jaringan Komputer"
        });
        panel.add(inputMatkul);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Tambahan: Tombol Reset
        JButton btnReset = new JButton("Reset Input");
        panel.add(btnReset);

        // Event klik simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        // Event tombol reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputNama.setText("");
                inputNilai.setText("");
            }
        });

        return panel;
    }

   
    // Panel Tabel Output (Tab Daftar Nilai)

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama", "Nilai", "Mata Kuliah", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(tableData);
        panel.add(scroll, BorderLayout.CENTER);

        // POIN 2: Tambah tombol hapus
        JButton btnHapus = new JButton("Hapus Data");

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected = tableData.getSelectedRow();

                if (selected > -1) {
                    tableModel.removeRow(selected);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
                }
            }
        });

        JPanel panelBawah = new JPanel();
        panelBawah.add(btnHapus);

        panel.add(panelBawah, BorderLayout.SOUTH);

        return panel;
    }

   
    // Proses Simpan Data

    private void prosesSimpan() {
        String nama = inputNama.getText();
        String strNilai = inputNilai.getText();
        String matkul = (String) inputMatkul.getSelectedItem();

        // POIN 3: Validasi minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Nama minimal harus 3 karakter!");
            return;
        }

        int nilai;

        // Validasi nilai harus angka
        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus berupa angka!");
            return;
        }

        // Validasi range nilai 0–100
        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus berada di antara 0–100!");
            return;
        }

        
        // 1: Switch Case Grade
       
        String grade;
        int kategori;

        if (nilai >= 85) kategori = 1;
        else if (nilai >= 75) kategori = 2;
        else if (nilai >= 60) kategori = 3;
        else if (nilai >= 40) kategori = 4;
        else kategori = 5;

        switch (kategori) {
            case 1:
                grade = "A";
                break;
            case 2:
                grade = "B";
                break;
            case 3:
                grade = "C";
                break;
            case 4:
                grade = "D";
                break;
            default:
                grade = "E";
        }

        // Tambah ke tabel
        tableModel.addRow(new Object[]{nama, nilai, matkul, grade});

        JOptionPane.showMessageDialog(this,
                "Data berhasil disimpan!");
    }

   
    // Konstruktor Utama

    public ManajemenNilaiSiswaApp() {
        super("Aplikasi Manajemen Nilai");

        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabPane = new JTabbedPane();
        tabPane.addTab("Input Data", createInputPanel());
        tabPane.addTab("Daftar Nilai", createTablePanel());

        add(tabPane);
    }

  
    // Method Main
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManajemenNilaiSiswaApp app = new ManajemenNilaiSiswaApp();
            app.setVisible(true);
        });
    }
}
