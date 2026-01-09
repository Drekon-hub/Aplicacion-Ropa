import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // 1. Iniciamos los datos (Modelo)
        GestorInventario gestor = new GestorInventario();

        // 2. Iniciamos la ventana (Vista)
        InterfazNegocio ventana = new InterfazNegocio();

        // 3. Programamos la acción del botón "Guardar" que está en la pantalla de Agregar
        // Usamos los componentes públicos de la clase InterfazNegocio
        ventana.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extraemos los datos de los campos de texto de la interfaz
                    int id = Integer.parseInt(ventana.txtId.getText());
                    String nombre = ventana.txtNombre.getText();
                    String talle =  ventana.txtTalle.getText();
                    double precio = Double.parseDouble(ventana.txtPrecio.getText());
                    int stock = Integer.parseInt(ventana.txtStock.getText());

                    // Creamos el objeto y lo guardamos
                    Producto p = new Producto(id, nombre, talle, precio, stock);
                    gestor.agregarProducto(p);

                    // Actualizamos la tabla que está en la otra pantalla (Inventario)
                    ventana.actualizarTabla(gestor.getListaProductos());

                    // Limpiamos los campos y avisamos al usuario
                    limpiarCampos(ventana);
                    JOptionPane.showMessageDialog(ventana, "¡Producto guardado con éxito!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventana, "Error: Verifica que ID, Precio y Stock sean números.");
                }
            }
        });

        // 4. Hacemos visible la aplicación
        SwingUtilities.invokeLater(() -> {
            ventana.setVisible(true);
        });
    }

    private static void limpiarCampos(InterfazNegocio v) {
        v.txtId.setText("");
        v.txtNombre.setText("");
        v.txtTalle.setText("");
        v.txtPrecio.setText("");
        v.txtStock.setText("");
    }
}