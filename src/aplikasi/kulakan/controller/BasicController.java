/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import javax.swing.JPanel;

/**
 *
 * @author cicak
 */
public interface BasicController {
    public void init();
    public void start();
    public void stop();
    public JPanel getPage();
}
