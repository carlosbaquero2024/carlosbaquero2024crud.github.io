
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


public class CrudArmas {
    
    int idArma;
    String Marca;
    String SerialArma;
    String TipoArma;
    String Calibre;
    String BuscarSerial;

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getSerialArma() {
        return SerialArma;
    }

    public void setSerialArma(String SerialArma) {
        this.SerialArma = SerialArma;
    }

    public String getTipoArma() {
        return TipoArma;
    }

    public void setTipoArma(String TipoArma) {
        this.TipoArma = TipoArma;
    }

    public String getCalibre() {
        return Calibre;
    }

    public void setCalibre(String Calibre) {
        this.Calibre = Calibre;
    }

    public String getBuscarSerial() {
        return BuscarSerial;
    }

    public void setBuscarSerial(String BuscarSerial) {
        this.BuscarSerial = BuscarSerial;
    }
    
    
    
    // ESTE ES EL METODO  INSERTAR O GUARDAR
    
    public void InsertarArmas(JTextField Marca, JTextField SerialArma, JTextField TipoArma, JTextField Calibre){
    
    setMarca(Marca.getText());
    setSerialArma(SerialArma.getText());
    setTipoArma(TipoArma.getText());
    setCalibre(Calibre.getText());

    
    Cconexion Objetoconexion = new Cconexion();
    
    String consulta = "INSERT INTO elite.armas(Marca, SerialArma, TipoArma, Calibre) values (?, ?, ?, ?);";
    
        try {
            
            CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getMarca());
            cs.setString(2,getSerialArma());
            cs.setString(3,getTipoArma());
            cs.setString(4, getCalibre());
            
            cs.execute();
            
            JOptionPane.showConfirmDialog(null,"se inserto correctamente el registro");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showConfirmDialog(null,"Error"+e.toString());
        }
        
    
    }
    
    
    
    
    
    //ESTE ES EL METODO MOSTRAR , LA INFROMACION SE MUESTRA EN LA TABLA LA CUAL LA CREAMOS POR MEDIO DE NUESTRO CODIGO 
    
    public void MostrarArmas(JTable TableArmas){
    
     Cconexion ObjetoConexion = new Cconexion();
     
     DefaultTableModel modelo = new  DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
     TableArmas.setRowSorter(OrdenarTabla);
     
     String consulta = "";
     
     modelo.addColumn("idArma");
     modelo.addColumn("Marca");
     modelo.addColumn("SerialArma");
     modelo.addColumn("TipoArma");
     modelo.addColumn("Calibre");
     
     TableArmas.setModel(modelo);
     
     consulta = "SELECT * FROM armas;";
     
     String[] datos = new String[5];
     
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
                
                modelo.addRow(datos);
            }
            
            TableArmas.setModel(modelo);
            
        } catch (SQLException e) {
            
            JOptionPane.showConfirmDialog(null,"NO se pudo mostrar los registros"+e.toString());
        }
     
    
    }
    
    
    
    
    // ESTE ES EL METODO DE SELECCIONAR EL CUAL AL DAR CLIP EN UN REGISTRO SE SELCIONA Y LO MUESTRA  EN LAS CAJAS DE TEXTO
    
    public void  SelcionarRegistro(JTable TableArmas,JTextField idArma, JTextField Marca, JTextField SerialArma, JTextField TipoArma, JTextField Calibre){
    
        try {
            int fila = TableArmas.getSelectedRow();
            if (fila >= 0 ){
                
                idArma.setText((String) (TableArmas.getValueAt(fila,0)));
                Marca.setText((String) (TableArmas.getValueAt(fila,1)));
                SerialArma.setText((String) (TableArmas.getValueAt(fila,2)));
                TipoArma.setText((String) (TableArmas.getValueAt(fila,3)));
                Calibre.setText((String) (TableArmas.getValueAt(fila,4)));
            }
            else{
            JOptionPane.showConfirmDialog(null,"REgistro seleccionado con exito");
            }
        } catch (HeadlessException e) {
            
            JOptionPane.showConfirmDialog(null,"Error en selecion"+e.toString());
        }
        
    }
    
    
    
    
    
    // ESTE ES EL METODO DE ACTUALIZAR
    
    public void ActualizarRegistros(JTextField idArma, JTextField Marca, JTextField SerialArma, JTextField TipoArma, JTextField Calibre){
    
       // setidArma(Integer.parseInt(idArma.getSelectedText()));
        setMarca(Marca.getText());
        setSerialArma(SerialArma.getText());
        setTipoArma(TipoArma.getText());
        setCalibre(Calibre.getText());
        
        Cconexion ObjetoConexion = new Cconexion();
        
        String consulta = "update armas set armas.Marca = ?, armas.SerialArma = ?, armas.TipoArma = ?, armas.Calibre = ? where armas.idArma = ?;";
        
        try {
            CallableStatement cs = ObjetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,getMarca());
            cs.setString(2,getSerialArma());
            cs.setString(3,getTipoArma());
            cs.setString(4,getCalibre());
        cs.setInt(5, Integer.parseInt(idArma.getText()));
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Modificacion exitosa");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showMessageDialog(null,"error al modificar"+e.toString());
        }
    }
    
    
    
    
    
    // ESTE ES EL METODO DE ELIMINAR
    
    
    public void EliminarArma(JTextField SerialArma){
        setSerialArma(SerialArma.getText());
        Cconexion Objetoconexion = new Cconexion();
        
        String consulta = "delete  From armas where armas.SerialArma = (?);";
        
        try {
            CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,SerialArma.getText());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimio corretamente el regisrto");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar"+e.toString());
        }
        
        
    }    
 
    
    
    
    // ESTE ES EL METODO BUSCAR CON ESTE METODO BUSCAMOS UN  REGISTRO EN ESPECICO.
    
    public void BuscarRegistro(JTextField BuscarSerial){
     
         setSerialArma(BuscarSerial.getText());
         
         Cconexion Objetoconexion = new Cconexion();
         
         String consulta = "SELECT * FROM armas where SerialArma = (?);";
         
         
         
         try {
          
             CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
              cs.setString(1,BuscarSerial.getText());
        
             cs.execute();
           
             
             JOptionPane.showMessageDialog(null, "Se encontro corretamente el regisrto");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    
    }
  
       
}
