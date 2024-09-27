
package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import prueva.login.formPrincipal;


public class Clogin {
    
    public void validarusuario(JTextField Usuario, JPasswordField Contraseña){
        
        try {
            
            ResultSet rs = null;
            PreparedStatement  ps = null;
            
            clases.Cconexion objetoconexion = new clases.Cconexion();
            objetoconexion.establecerConexion();
            
            String consulta = "SELECT * FROM elite.usuarios where usuarios.Usuario = (?) and usuarios.Contraseña= (?);";
            
            ps = objetoconexion.establecerConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(Contraseña.getPassword());
            
            ps.setString(1,Usuario.getText());
            ps.setString(2,contra);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
            
                //JOptionPane.showConfirmDialog(null,"El Usuario es correcto");
                formPrincipal objetomenu = new formPrincipal();
                objetomenu.setVisible(true);
            }
            else{
            
                JOptionPane.showConfirmDialog(null, " Usuario  es incorreto");
                        
            }
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showConfirmDialog(null, " Error"+e.toString());
        }
    }
}
