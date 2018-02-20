package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe Coordenadas, responsável pela criação de coordenadas, para cadastro de
 * um local.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Coordenadas implements Serializable {
    
    private final double latitude, longitude;
    
    /** Construtor da classe, responsável pela criação de uma nova coordenada,
     * recebe uma latitude e uma longitude.
     * 
     * @param latitude
     * @param longitude
     */
    public Coordenadas (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** Método que retorna uma latitude.
     * 
     * @return double latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /** Método que retorna uma longitude.
     * 
     * @return double longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /** Método que retorna um código único das coordenadas, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.latitude) ^
               (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.longitude) ^
               (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }
    
    /** Método que verifica se duas coordenadas são iguais retornando um booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {            
        return ((latitude == getLatitude()) && (longitude == getLongitude()));
    }

    /** Método que dá uma representação em String de uma coordenada.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return (", Coordenadas - " + "Latitude: " + latitude + ", Longitude:" + longitude);
    }
}