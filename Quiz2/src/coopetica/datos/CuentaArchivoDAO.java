/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.datos;

import coopetica.modelo.Cuenta;
import coopetica.modelo.CuentaAhorro;
import coopetica.modelo.CuentaCorriente;
import java.io.*;
import java.util.ArrayList;

public class CuentaArchivoDAO {
    private final String nombreArchivo = "datos/cuentas.txt";

    public CuentaArchivoDAO() throws IOException {
        File carpeta = new File("datos");
        if (!carpeta.exists()) { carpeta.mkdir(); }
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) { archivo.createNewFile(); }
    }

    public ArrayList<Cuenta> listar() throws IOException {
        ArrayList<Cuenta> lista = new ArrayList<>();
        try (BufferedReader bR = new BufferedReader(
                new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = bR.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 5) {
                    if (d[0].equals("A")) {
                        lista.add(new CuentaAhorro(d[1], d[2],
                                Double.parseDouble(d[3]),
                                Double.parseDouble(d[4])));
                    } else if (d[0].equals("C")) {
                        lista.add(new CuentaCorriente(d[1], d[2],
                                Double.parseDouble(d[3]),
                                Double.parseDouble(d[4])));
                    }
                }
            }
        }
        return lista;
    }

    public void guardarTodas(ArrayList<Cuenta> cuentas) throws IOException {
        try (BufferedWriter bW = new BufferedWriter(
                new FileWriter(nombreArchivo))) {
            for (Cuenta c : cuentas) {
                String marca;
                String extra;
                if (c instanceof CuentaAhorro) {
                    marca = "A";
                    extra = "" + ((CuentaAhorro) c).getTasaInteres();
                } else if (c instanceof CuentaCorriente ){
                    marca = "C";
                    extra = "" + ((CuentaCorriente) c).getLimiteSobregiro();
                } else{
                    continue;
                }
                bW.append(marca + "," + c.getNumero() + ","
                        + c.getCedulaAsociado() + "," + c.getSaldo()
                        + "," + extra);
                bW.newLine();
            }
        }
    }
}

