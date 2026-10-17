
import java.awt.Color;
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

    public PalindromoAir() {
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
            return -1;
        }
        if (asientos[i]==null){
            return i;
        }
        return FirstAvailable(i+1);
    }
    public int searchPassenger(String nombre, int i){
        if (i>=Capacidad){
            return -1;
        }
        if(asientos[i]!=null){
           String buscado=preptexto(nombre);
           String existente=preptexto(asientos[i].getNombre());
           if (buscado.equals(existente)){
               return i;
        }
    }
        return searchPassenger(nombre,i+1);
    }
    public boolean isPalindromo(String nombre){
    if (nombre == null) return false;
    String limpio = preptexto(nombre);
    if (limpio.length() <= 1) {
        return true;
    }
    if (limpio.charAt(0) != limpio.charAt(limpio.length() - 1)) {
        return false;
    }

    String subTexto = limpio.substring(1, limpio.length() - 1);
    return isPalindromo(subTexto);
}
    public String preptexto(String texto) {
        if (texto == null) return "";
        return texto.toLowerCase()
                .replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[^a-z0-9]", "");
    }
    public String printPassengers(int i){
        if (i >= Capacidad) {
            return "";
        }
        String textoActual = "";
        if (asientos[i] != null) {
            Ticket t = asientos[i];
            char fila = (char) ('A' + (i / 6));
            int columna = (i % 6) + 1;
            String codigo = "" + fila + columna;

            String esPalTexto = "No";
            if (t.isEsPalindromo()) {
                esPalTexto = "Sí";
            }

            textoActual = "Asiento " + codigo + ": " + t.getNombre() + 
                          " | Pagó: $" + t.getMontoPagado() + 
                          " | Base: $" + t.getMontoOriginal() + 
                          " | Palíndromo: " + esPalTexto + "\n";
        }
        return textoActual + printPassengers(i + 1);
    }
        
    public double income(int i) {
        if (i >= Capacidad) {
            return 0.0; 
        }
        double montoActual=0.0;
        if(asientos[i]!=null){
            montoActual=asientos[i].getMontoPagado();
        }
        return montoActual+income(i+1);
    }
    public void reset(int i) {
        if (i >= Capacidad) {
            return;
        }
        asientos[i] = null;
        if (BotonesAsientos != null) {
            if (BotonesAsientos[i] != null) {
                char fila = (char) ('A' + (i / 6));
                int columna = (i % 6) + 1;
                String codigo = "" + fila + columna;

                BotonesAsientos[i].setText("<html><center><b>" + codigo + "</b><br>Libre</center></html>");
                BotonesAsientos[i].setBackground(new Color(200, 230, 201));
            }
        }
        reset(i + 1);
    }
    public boolean sellTicket(String nombre) {
        if (nombre == null) {
            return false;
        }

        String limpio = nombre.toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z0-9]", "");

        if (limpio.isEmpty()) {
            return false;
        }

        int libreIndex = FirstAvailable(0);
        if (libreIndex == -1) {
            return false;
        }

        if (searchPassenger(nombre, 0) != -1) {
            return false;
        }

        boolean esPal = isPalindromo(nombre);
        double montoPagado = Tarifa_Base;
        if (esPal) {
            montoPagado = Tarifa_Base * (1.0 - Porcentaje_Desc);
        }

        Ticket nuevoTicket = new Ticket(nombre, Tarifa_Base, montoPagado, esPal);
        asientos[libreIndex] = nuevoTicket;

        if (BotonesAsientos != null) {
            if (BotonesAsientos[libreIndex] != null) {
                char fila = (char) ('A' + (libreIndex / 6));
                int columna = (libreIndex % 6) + 1;
                String cod = "" + fila + columna;

                BotonesAsientos[libreIndex].setText("<html><center><b>" + cod + "</b><br>" + nombre + "<br>$" + (int)montoPagado + "</center></html>");
                if (esPal) {
                    BotonesAsientos[libreIndex].setBackground(new Color(255, 223, 128));
                } else {
                    BotonesAsientos[libreIndex].setBackground(new Color(240, 128, 128));
                }
            }
        }

        return true;
}
    public boolean cancelTicket(String nombre) {
        int pos = searchPassenger(nombre,0);
        if (pos == -1) {
            return false;
        }

        asientos[pos] = null;

        if (BotonesAsientos != null) {
            if (BotonesAsientos[pos] != null) {
                char fila = (char) ('A' + (pos / 6));
                int columna = (pos % 6) + 1;
                String cod = "" + fila + columna;

                BotonesAsientos[pos].setText("<html><center><b>" + cod + "</b><br>Libre</center></html>");
                BotonesAsientos[pos].setBackground(new Color(200, 230, 201));
            }
        }

        return true;
    }
    public double dispatch() {
        double totalIngresos = income(0);
        reset(0);
        return totalIngresos;
    }
}

    


    
    
    

