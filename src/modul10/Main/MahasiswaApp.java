/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul10.Main;

/**
 *
 * @author Ahmad
 */


import modul10.View.MahasiswaView;
import modul10.Model.MahasiswaModel;
import modul10.Controller.MahasiswaController;

public class MahasiswaApp {
    public static void main(String[] args) {
        MahasiswaView view = new MahasiswaView();
        MahasiswaModel model = new MahasiswaModel();
        new MahasiswaController(view, model);
        view.setVisible(true);
    }
}
