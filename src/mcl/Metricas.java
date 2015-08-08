/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcl;

/**
 *
 * @author Estefanis
 */
public class Metricas {
    private double entropia;
    private double contraste;

    public Metricas(double entropia, double contraste) {
        this.entropia = entropia;
        this.contraste = contraste;
    }

    @Override
    public String toString() {
        return "Metricas{" + "entropia=" + entropia + ", contraste=" + contraste + '}';
    }
    
    
    
}
