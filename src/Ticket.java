/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garrett-PC
 */
public class Ticket {
    private String nombre;
    private double MontoOriginal;
    private double montoPagado;
    private boolean esPalindromo;

    public Ticket(String nombre, double MontoOriginal, double montoPagado, boolean esPalindromo) {
        this.nombre = nombre;
        this.MontoOriginal = MontoOriginal;
        this.montoPagado = montoPagado;
        this.esPalindromo = esPalindromo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMontoOriginal() {
        return MontoOriginal;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public boolean isEsPalindromo() {
        return esPalindromo;
    }
    
    
}
