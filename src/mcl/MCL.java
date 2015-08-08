/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcl;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

/**
 *
 * @author Estefanis
 */
public class MCL {

    public static MatlabProxyFactory factory;
    public static MatlabProxy proxy;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a proxy, which we will use to control MATLAB
        System.out.println("Crear un proxy, para utilizar MATLAB");
        try {
            factory = new MatlabProxyFactory();
            proxy = factory.getProxy();
            System.out.println("Ejecutando el MCL..");
            String comando = "[e , c] = testMCLV2('mdb005.pgm',1,2,0.5,1,10)";
            MCL.run(comando);
            System.out.println("Ejecutando el MCL..");
            comando = "[e , c] = testMCLV2('mdb001.pgm',1,2,0.5,1,10)";
            MCL.run(comando);
            System.out.println("Fin de ejecucion!");
            System.out.println("Desconectar el proxy con matlab");
            proxy.disconnect();
        } catch (MatlabConnectionException mce) {
            System.err.println("Error en la conexion con el MATLAB " + mce.getMessage());
        } catch (MatlabInvocationException mie) {
            System.err.println("Error en la invocacion de MATLAB " + mie.getMessage());
        } catch (Exception e) {
            System.err.println("Error General " + e.getMessage());
        }
    }

    public static Metricas run(String comando) throws MatlabInvocationException {
        Metricas metricas = null;
        proxy.setVariable("e", 0);
        proxy.setVariable("c", 0);        
        proxy.eval(comando);
        double entropia = ((double[]) proxy.getVariable("e"))[0];
        double contraste = ((double[]) proxy.getVariable("c"))[0];
        metricas = new Metricas(entropia, contraste);
        System.out.println(metricas.toString());
        return metricas;
    }

}
