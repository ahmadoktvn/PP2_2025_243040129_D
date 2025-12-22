/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul10.Controller;

import modul10.Model.MahasiswaModel;
import modul10.View.MahasiswaView;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Ahmad
 */



public class MahasiswaController {

    MahasiswaView view;
    MahasiswaModel model;

    public MahasiswaController(MahasiswaView view, MahasiswaModel model) {
        this.view = view;
        this.model = model;

        model.loadData(view.model);

        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        view.btnSimpan.addActionListener(e -> simpan());
        view.btnEdit.addActionListener(e -> edit());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnClear.addActionListener(e -> clear());
    }

    void simpan() {
        try {
            model.tambahData(
                view.txtNama.getText(),
                view.txtNIM.getText(),
                view.txtJurusan.getText()
            );
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            model.loadData(view.model);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    void edit() {
        try {
            model.ubahData(
                view.txtNama.getText(),
                view.txtJurusan.getText(),
                view.txtNIM.getText()
            );
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
            model.loadData(view.model);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    void hapus() {
        try {
            model.hapusData(view.txtNIM.getText());
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
            model.loadData(view.model);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    void clear() {
        view.txtNama.setText(null);
        view.txtNIM.setText(null);
        view.txtJurusan.setText(null);
    }
}
