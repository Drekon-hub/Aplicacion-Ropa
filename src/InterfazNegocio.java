import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InterfazNegocio extends JFrame {
    // 1. DECLARACIÓN DE COMPONENTES COMO PÚBLICOS
    public JTextField txtId, txtNombre, txtTalle, txtPrecio, txtStock;
    public JButton btnGuardar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JPanel cards;
    private CardLayout cl;

    public InterfazNegocio() {
        setTitle("Sistema de Ropa");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- BARRA LATERAL ---
        JPanel menuLateral = new JPanel(new GridLayout(10, 1, 5, 5));
        JButton btnIrInventario = new JButton("Ver Inventario");
        JButton btnIrAgregar = new JButton("Agregar Producto");
        JButton btnIrCorbor = new JButton("Cobrar");
        menuLateral.add(btnIrInventario);
        menuLateral.add(btnIrAgregar);
        menuLateral.add(btnIrCorbor);
        add(menuLateral, BorderLayout.WEST);

        // --- PANEL CENTRAL (CARDLAYOUT) ---
        cl = new CardLayout();
        cards = new JPanel(cl);

        // Agregamos las pantallas
        cards.add(crearPantallaInventario(), "INVENTARIO");
        cards.add(crearPantallaAgregar(), "AGREGAR");
        cards.add(crearPantallaCobrar(), "COBRAR");

        add(cards, BorderLayout.CENTER);

        // Eventos para cambiar de pantalla
        btnIrInventario.addActionListener(e -> cl.show(cards, "INVENTARIO"));
        btnIrAgregar.addActionListener(e -> cl.show(cards, "AGREGAR"));
        btnIrCorbor.addActionListener(e -> cl.show(cards, "COBRAR"));
    }

    private JPanel crearPantallaInventario() {
        JPanel p = new JPanel(new BorderLayout());
        String[] columnas = {"ID", "Nombre", "Talle", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return p;
    }

    private JPanel crearPantallaAgregar() {
        JPanel p = new JPanel(new GridLayout(7, 2, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // INICIALIZACIÓN DE LOS COMPONENTES PÚBLICOS
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtTalle = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        btnGuardar = new JButton("Guardar Producto");

        p.add(new JLabel("ID:")); p.add(txtId);
        p.add(new JLabel("Nombre:")); p.add(txtNombre);
        p.add(new JLabel("Talle:")); p.add(txtTalle);
        p.add(new JLabel("Precio:")); p.add(txtPrecio);
        p.add(new JLabel("Stock:")); p.add(txtStock);
        p.add(new JLabel("")); p.add(btnGuardar);

        return p;
    }

    private JPanel crearPantallaCobrar(){
        JPanel p = new JPanel(new GridLayout(7, 2, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtId = new JTextField();
        p.add(new JLabel("ID:")); p.add(txtId);
        txtId = new JTextField("");
        return p;
    }

    public void actualizarTabla(List<Producto> lista) {
        modeloTabla.setRowCount(0);
        for (Producto p : lista) {
            Object[] fila = {p.getId(), p.getNombre(), p.getTalle(), p.getPrecio(), p.getStock()};
            modeloTabla.addRow(fila);
        }
    }
}