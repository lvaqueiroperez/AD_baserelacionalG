package ex24_baserelacionalg;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex24_baserelacionalG {

    Connection conn;

    public void Conexion() throws SQLException {

        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

        conn = DriverManager.getConnection(url);

    }

    public void Cerrar() throws SQLException {

        conn.close();

    }

    public void accesoFuncion(String codigo) throws SQLException {
        //ponemos un interrogante como parámetro de salida donde almacenaremos
        //el resultado de la funcion
        CallableStatement calS = conn.prepareCall("{?=call prezo_produto(?)}");
        //especificamos los parámetros de entrada y salida
        calS.registerOutParameter(1, Types.INTEGER);
        calS.setString(2, codigo);

        //recibimos los resultados:
        calS.execute();

        System.out.println(calS.getInt(1));

    }

    public static void main(String[] args) {

        Ex24_baserelacionalG obj = new Ex24_baserelacionalG();

        try {
            obj.Conexion();

            obj.accesoFuncion("p2");

            obj.Cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Ex24_baserelacionalG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
