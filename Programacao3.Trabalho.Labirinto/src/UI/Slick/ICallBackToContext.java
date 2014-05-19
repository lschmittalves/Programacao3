/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick;

import Modelos.Labirinto;

/**
 *
 * @author leonardo.alves
 */
public interface ICallBackToContext {

    public void onOpenScreen();
    public void salvarLabirinto(Labirinto labirinto);
    
}
