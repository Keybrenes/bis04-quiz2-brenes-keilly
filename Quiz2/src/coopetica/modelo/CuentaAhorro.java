/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 *
 * @author keybr
 */
public class CuentaAhorro extends Cuenta {
    
    private final double tasaInteres;

    public CuentaAhorro(String numero, String cedulaAsociado,
                        double saldo, double tasaInteres) {
        super(numero, cedulaAsociado, saldo);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() { return tasaInteres; }

    public double calcularInteresMensual() {
        return getSaldo() * tasaInteres / 12;
    }

    @Override
    public String getTipo() {
        return "Ahorro";
    }

    @Override
    public String toString() {
        return super.toString() + " - interes mensual "
                + Math.round(calcularInteresMensual());
    }
}
