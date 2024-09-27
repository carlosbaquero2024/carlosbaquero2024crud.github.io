
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


public class CrudGuardas {
       
    int idsupervisor;
    String Nombre;
    String Apellido;
    String Cedula;
    String Direccion;
    String Barrio;
    String Telefono;
    String Genero;
    String FechaNacimiento;

    public int getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(int idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    
    

        
    public void InsertarGuardas(JTextField Nombre, JTextField Apellido, JTextField Cedula, JTextField Direccion, JTextField Barrio, JTextField Telefono,
    JTextField Genero, JTextField FechaNacimiento){
        
        setNombre(Nombre.getText());
        setApellido(Apellido.getText());
        setCedula(Cedula.getText());
        setDireccion(Direccion.getText());
        setBarrio(Barrio.getText());
        setTelefono(Telefono.getText());
        setGenero(Genero.getText());
        setNombre(Nombre.getText());
        setFechaNacimiento(FechaNacimiento.getText());
        
        Cconexion  ObjetoConexion = new Cconexion();
        
        String consulta ="INSERT INTO elite.supervisores(Nombre,Apellido,Cedula,Direccion,Barrio,Telefono,Genero,FechaNacimiento) values(?,?,?,?,?,?,?,?);";
        
        try {
            CallableStatement cs = ObjetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,getNombre());
            cs.setString(2,getApellido());
            cs.setString(3,getCedula());
            cs.setString(4,getDireccion());
            cs.setString(5,getBarrio());
            cs.setString(6,getTelefono());
            cs.setString(7,getGenero());
            cs.setString(8,getFechaNacimiento());
            
            cs.execute();
            
            JOptionPane.showConfirmDialog(null,"Se inserto correctamente el regisro");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showConfirmDialog(null,"N se inserto correctamente el registro"+e.toString());
        }
    }

    
    
    
    
   public void MostrarGuarda(JTable Tablaguardas){
       
       Cconexion ObjetoConexion = new Cconexion();
       
       DefaultTableModel modelo = new DefaultTableModel();
       
       TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
       Tablaguardas.setRowSorter(OrdenarTabla);
       
       String sql;
       
       modelo.addColumn("idsupervisor");
       modelo.addColumn("Nombre");
       modelo.addColumn("Apellido");
       modelo.addColumn("Cedula");
       modelo.addColumn("Direccion");
       modelo.addColumn("Barrio");
       modelo.addColumn("Telefono");
       modelo.addColumn("Genero");
       modelo.addColumn("FechaNacimiento");
       
       Tablaguardas.setModel(modelo);
       
       sql = "SELECT * FROM elite.supervisores;";
       
       String[] datos = new String[9];
       Statement st;
       
       try {
           st = ObjetoConexion.establecerConexion().createStatement();
           ResultSet rs = st.executeQuery(sql);
           
           while(rs.next()){
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               datos[4] = rs.getString(5);
               datos[5] = rs.getString(6);
               datos[6] = rs.getString(7);
               datos[7] = rs.getString(8);
               datos[8] = rs.getString(9);
               
               modelo.addRow(datos);
             
           }
           
           Tablaguardas.setModel(modelo);
           
       } catch (SQLException e) {
           
           JOptionPane.showConfirmDialog(null,"NO se pudo mostrar registro de guardas"+ e.toString());
       }
   }
   
   
   
   
   public void SeleccionarGuarda(JTable Tablaguardas,JTextField idsupervisor,JTextField Nombre, JTextField Apellido, JTextField Cedula, JTextField Direccion, JTextField Barrio, JTextField Telefono,
    JTextField Genero, JTextField FechaNacimiento){
       
       try {
           int fila = Tablaguardas.getSelectedRow();
           
           if(fila >= 0){
               idsupervisor.setText((String) (Tablaguardas.getValueAt(fila, 0).toString()));
               Nombre.setText((String) (Tablaguardas.getValueAt(fila, 1).toString()));
               Apellido.setText((String) (Tablaguardas.getValueAt(fila, 2).toString()));
               Cedula.setText((String) (Tablaguardas.getValueAt(fila, 3).toString()));
               Direccion.setText((String) (Tablaguardas.getValueAt(fila, 4).toString()));
               Barrio.setText((String) (Tablaguardas.getValueAt(fila, 5).toString()));
               Telefono.setText((String) (Tablaguardas.getValueAt(fila, 6).toString()));
               Genero.setText((String) (Tablaguardas.getValueAt(fila, 7).toString()));
               FechaNacimiento.setText((String) (Tablaguardas.getValueAt(fila, 8).toString()));
               
               
           }
           else{
               JOptionPane.showConfirmDialog(null,"No se selecciono una fila");
           }
           
       } catch (HeadlessException e) {
           
           JOptionPane.showConfirmDialog(null,"Error de seleccion"+e.toString());
       }
   }
   
   
   
   
   public void Actualizar(JTextField idsupervisor,JTextField Nombre, JTextField Apellido, JTextField Cedula, JTextField Direccion, JTextField Barrio, JTextField Telefono,
    JTextField Genero, JTextField FechaNacimiento){
       
      
       setNombre(Nombre.getText());
       setApellido(Apellido.getText());
       setCedula(Cedula.getText());
       setDireccion(Direccion.getText());
       setBarrio(Barrio.getText());
       setTelefono(Telefono.getText());
       setGenero(Genero.getText());
       setFechaNacimiento(FechaNacimiento.getText());
       
       Cconexion  Objetoconexion = new Cconexion();
       
       String consulta = "UPDATE elite.supervisores SET supervisores.Nombre = ?, supervisores.Apellido = ?, supervisores.Cedula = ?, supervisores.Direccion = ?,supervisores.Barrio = ?, supervisores.Telefono = ?, supervisores.Genero = ?, supervisores.FechaNacimiento = ? where supervisores.idsupervisor = ?;";
   
       try {
           
           CallableStatement cs = Objetoconexion.establecerConexion().prepareCall(consulta);
           
           cs.setString(1,getNombre());
           cs.setString(2,getApellido());
           cs.setString(3,getCedula());
           cs.setString(4,getDireccion());
           cs.setString(5,getBarrio());
           cs.setString(6,getTelefono());
           cs.setString(7,getGenero());
           cs.setString(8,getFechaNacimiento());
           
           cs.setInt(9, Integer.parseInt(idsupervisor.getText()));
           
           cs.execute();
           
           JOptionPane.showConfirmDialog(null,"Se  actualizo el registro correctament");
           
           
           
       } catch (HeadlessException | SQLException e) {
           
           JOptionPane.showConfirmDialog(null,"Error al actualizar "+e.toString());
       }
   }
   
   
   
   
   
   public void EliminarGuarda(JTextField Cedula){
       
       Cconexion ObjetoEliminar = new Cconexion();
       
         String consulta ="DELETE FROM supervisores where supervisores.Cedula = ?;";
        
        try {
           CallableStatement cs = ObjetoEliminar.establecerConexion().prepareCall(consulta);     
           cs.setString(1,Cedula.getText());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null,"se Elimino  el registro");

           
        } catch (HeadlessException | SQLException e) { 
            
            JOptionPane.showMessageDialog(null,"Error al Eliminar el registro del supervisor"+ e.toString());
         }
        
   }
   
   
   
   
   public void BuscarSupervisor(JTextField BuscarCedula, JTextField idsupervisor, JTextField Nombre, JTextField Apellido,JTextField Cedula, JTextField Direccion, JTextField Barrio,
           JTextField Telefono, JTextField Genero, JTextField FechaNacimiento){
          
        Cconexion objetoConexion = new Cconexion();
        String consulta = "SELECT  idsupervisor, Nombre, Apellido, Cedula, Direccion, Barrio, Telefono, Genero, FechaNacimiento from elite.supervisores where supervisores.Cedula = (?);";
      
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, BuscarCedula.getText());
            cs.execute();
            
            ResultSet rs = cs.executeQuery();
            
            if(rs.next()){
            
                JOptionPane.showMessageDialog(null,"Registro encontrado con exito");
                
                idsupervisor.setText(rs.getString("idsupervisor"));
                Nombre.setText(rs.getString("Nombre"));
                Apellido.setText(rs.getString("Apellido"));
                Cedula.setText(rs.getString("Cedula"));
                Direccion.setText(rs.getString("Direccion"));
                Barrio.setText(rs.getString("Barrio"));
                Telefono.setText(rs.getString("Telefono"));
                Genero.setText(rs.getString("Genero"));
                FechaNacimiento.setText(rs.getString("FechaNacimiento"));
                
                
            }else{
            JOptionPane.showMessageDialog(null,"Registro no econtrado");
            } 
             
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null," Error"+ e.toString());
           
            idsupervisor.setText("");
            Nombre.setText("");
            Apellido.setText("");
            Cedula.setText("");
            Direccion.setText("");
            Barrio.setText("");
            Telefono.setText("");
            Genero.setText("");
            FechaNacimiento.setText("");
            Cedula.setText("");
        }
        
    }
   
      
}



 

