
package prueva.login;

import clases.Cconexion;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CrudEquipoEletronico {
    
    int idEquipo;
    String Radio;
    String Celular;
    String Serial;
    String DetectorMetal;
    String Camara;
    String Monitor;

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getRadio() {
        return Radio;
    }

    public void setRadio(String Radio) {
        this.Radio = Radio;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public String getDetectorMetal() {
        return DetectorMetal;
    }

    public void setDetectorMetal(String DetectorMetal) {
        this.DetectorMetal = DetectorMetal;
    }

    public String getCamara() {
        return Camara;
    }

    public void setCamara(String Camara) {
        this.Camara = Camara;
    }

    public String getMonitor() {
        return Monitor;
    }

    public void setMonitor(String Monitor) {
        this.Monitor = Monitor;
    }
    
    
    
    
    // ESTE ES EL METODO  INSERTAR O GUARDAR
    
    public void InsertarArmas(JTextField Radio, JTextField Celular, JTextField Serial, JTextField DetectorMetal, JTextField Camara, JTextField Monitor){
    
    setRadio(Radio.getText());
    setCelular(Celular.getText());
    setSerial(Serial.getText());
    setDetectorMetal(DetectorMetal.getText());
    setCamara(Camara.getText());
    setMonitor(Monitor.getText());

    
    Cconexion Objetoconexion = new Cconexion();
    
    String consulta = "INSERT INTO elite.electronico(Radio, Celular, Serial, DetectorMetal, Camara, Monitor) values (?, ?, ?, ?, ?, ?);";
    
        try {
            
            CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getRadio());
            cs.setString(2,getCelular());
            cs.setString(3,getSerial());
            cs.setString(4, getDetectorMetal());
            cs.setString(5, getCamara());
            cs.setString(6, getMonitor());
            
            cs.execute();
            
            JOptionPane.showConfirmDialog(null,"se inserto correctamente el registro");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showConfirmDialog(null,"Error"+e.toString());
        }
        
    
    }
    
    
    
    
   // //ESTE ES EL METODO MOSTRAR , LA INFROMACION SE MUESTRA EN LA TABLA LA CUAL LA CREAMOS POR MEDIO DE NUESTRO CODIGO 
    
    public void MostrarEquipoElectronico(JTable TableElectronico){
    
     Cconexion ObjetoConexion = new Cconexion();
     
     DefaultTableModel modelo = new  DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
     TableElectronico.setRowSorter(OrdenarTabla);
     
     String consulta = "";
     
     modelo.addColumn("idEquipo");
     modelo.addColumn("Radio");
     modelo.addColumn("Celular");
     modelo.addColumn("Serial");
     modelo.addColumn("DetectorMetal");
     modelo.addColumn("Camara");
     modelo.addColumn("Monitor");
     
     TableElectronico.setModel(modelo);
     
     consulta = "SELECT * FROM electronico;";
     
     String[] datos = new String[7];
     
     Statement st;
     
        try {
            st = ObjetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                
                modelo.addRow(datos);
            }
            
            TableElectronico.setModel(modelo);
            
        } catch (SQLException e) {
            
            JOptionPane.showConfirmDialog(null,"NO se pudo mostrar los registros"+e.toString());
        }
     
    
    }
    
    
    
    
    public void  SelcionarRegistro(JTable TableElectronico,JTextField idEquipo, JTextField Radio, JTextField Celular, JTextField Serial, JTextField DetectorMetal, JTextField Camara, JTextField Monitor){
    
        try {
            int fila = TableElectronico.getSelectedRow();
            if (fila >= 0 ){
                
                idEquipo.setText((String) (TableElectronico.getValueAt(fila,0)));
                Radio.setText((String) (TableElectronico.getValueAt(fila,1)));
                Celular.setText((String) (TableElectronico.getValueAt(fila,2)));
                Serial.setText((String) (TableElectronico.getValueAt(fila,3)));
                DetectorMetal.setText((String) (TableElectronico.getValueAt(fila,4)));
                Camara.setText((String) (TableElectronico.getValueAt(fila,5)));
                Monitor.setText((String) (TableElectronico.getValueAt(fila,6)));
            }
            else{
            JOptionPane.showConfirmDialog(null,"REgistro seleccionado con exito");
            }
        } catch (HeadlessException e) {
            
            JOptionPane.showConfirmDialog(null,"Error en selecion"+e.toString());
        }
        
    }
    
    
    
    // ESTE ES EL METODO DE ACTUALIZAR
    
    public void ActualizarRegistros(JTextField idEquipo, JTextField Radio, JTextField Celular, JTextField Serial, JTextField DetectorMetal, JTextField Camara, JTextField Monitor){
    
        //setidEquipo(Integer.parseInt(idEquipo.getSelectedText()));
        setRadio(Radio.getText());
        setCelular(Celular.getText());
        setSerial(Serial.getText());
        setDetectorMetal(DetectorMetal.getText());
        setCamara(Camara.getText());
        setMonitor(Monitor.getText());
        
        Cconexion ObjetoConexion = new Cconexion();
        
        String consulta = "update electronico set electronico.Radio = ?, electronico.Celular = ?,electronico.Serial = ?, electronico.DetectorMetal = ?, electronico.Camara = ?, electronico.Monitor = ? where electronico.idEquipo = ?;";
        
        try {
            CallableStatement cs = ObjetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,getRadio());
            cs.setString(2,getCelular());
            cs.setString(3,getSerial());
            cs.setString(4,getDetectorMetal());
            cs.setString(5,getCamara());
            cs.setString(6,getMonitor());
            cs.setInt(7, Integer.parseInt(idEquipo.getText()));
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Modificacion exitosa");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showMessageDialog(null,"error al modificar"+e.toString());
        }
    }
    
    
    
    
    
    // ESTE ES EL METODO DE ELIMINAR
    
    
    public void EliminarEquipoElectronico(JTextField Serial){
        setSerial(Serial.getText());
        Cconexion Objetoconexion = new Cconexion();
        
        String consulta = "delete  From electronico where electronico.Serial = (?);";
        
        try {
            CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,Serial.getText());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimio corretamente el regisrto");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar"+e.toString());
        }
        
        
    }
    
}
