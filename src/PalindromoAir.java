
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garrett-PC
 */
public class PalindromoAir {
    public static final int Capacidad=30;
    public static final double Tarifa_Base=100.0;
    public static final double Porcentaje_Desc=0.20;
    
    private Ticket[]asientos;
    private JButton[]BotonesAsientos;

    public PalindromoAir(Ticket[] asientos, JButton[] BotonesAsientos) {
        this.asientos = new Ticket[Capacidad];
        this.BotonesAsientos = new JButton[Capacidad];
        
    }

    public Ticket[] getAsientos() {
        return asientos;
    }
    
    public void setBotonesAsientos(JButton[] botones){
        this.BotonesAsientos=botones;
    }
    
    public int FirstAvailable(int i){
        if(i>=Capacidad){
            return
        }
    }
}
