import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InterfazNegocio extends JFrame {
    private int num1 = 0; // Variable de clase (persiste entre clics)
    private int num2 = 0; // Variable de clase (persiste entre clics)
    private String aux = ""; // Variable de clase (persiste entre clics)
    private String operacionActual = "";


    // 1. DECLARACIÓN DE COMPONENTES COMO PÚBLICOS
    public JTextField txtId, txtNombre, txtTalle, txtPrecio, txtStock, txtPantallaCobro, txtPantallaHistorial;
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
        JPanel p = new JPanel(new GridLayout(2, 1));

// Creamos el contenedor con 0 espacio vertical entre filas
        JPanel panelPantallas = new JPanel(new GridLayout(2, 2, 0, -50));
        panelPantallas.setBackground(Color.WHITE); // Para que parezca una sola unidad

// --- Configuración Historial ---
        txtPantallaHistorial = new JTextField("");
        txtPantallaHistorial.setBorder(null);
        txtPantallaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtPantallaHistorial.setHorizontalAlignment(JTextField.RIGHT);
        txtPantallaHistorial.setEditable(false);
        txtPantallaHistorial.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5)); // Margen pequeño abajo
        txtPantallaHistorial.setBackground(Color.WHITE);

// --- Configuración Cobro ---
        txtPantallaCobro = new JTextField("0");
        txtPantallaCobro.setBorder(null);
        txtPantallaCobro.setFont(new Font("Monospaced", Font.BOLD, 35));
        txtPantallaCobro.setHorizontalAlignment(JTextField.RIGHT);
        txtPantallaCobro.setEditable(false);
// El margen superior es 0 para que se pegue al historial
        txtPantallaCobro.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        txtPantallaCobro.setBackground(Color.WHITE);

// Agregamos
        panelPantallas.add(txtPantallaHistorial);
        panelPantallas.add(txtPantallaCobro);

// IMPORTANTE: Agregamos el panel al NORTH
        p.add(panelPantallas, BorderLayout.NORTH);


        // 2. Teclado numérico
        JPanel teclado = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] botones = {
                "7", "8", "9","+",
                "4", "5", "6","-",
                "1", "2", "3","*",
                "C", "0", "OK","/",
                "="
        };
        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 20));

            // Acción de los botones
            btn.addActionListener(e -> gestionarTeclado(texto));

            teclado.add(btn);
        }

        p.add(teclado, BorderLayout.CENTER);
        return p;
    }

    // Lógica para que los botones funcionen
    private void gestionarTeclado(String tecla) {

        switch (tecla){
            case "+" -> {
                num1 += Integer.parseInt(txtPantallaCobro.getText());
                txtPantallaHistorial.setText(num1 + tecla);
                txtPantallaCobro.setText("0");
                operacionActual = "+";

            }
            case "-" -> {
                num1 = Integer.parseInt(txtPantallaCobro.getText());
                txtPantallaCobro.setText("0");
                operacionActual = "-";
            }
            case "*" -> {
                num1 = Integer.parseInt(txtPantallaCobro.getText());
                txtPantallaCobro.setText("0");
                operacionActual = "*";
            }
            case "/" -> {
                num1 = Integer.parseInt(txtPantallaCobro.getText());
                txtPantallaCobro.setText("0");
                operacionActual = "/";
            }
            case "0","1","2","3","4","5","6","7","8","9" -> {
                String actual = txtPantallaCobro.getText();
                if(aux.equals("=")){
                    txtPantallaCobro.setText("");
                    txtPantallaHistorial.setText("");
                    operacionActual = "";
                    aux = "";
                    num1 = 0;
                    num2 = 0;
                    txtPantallaCobro.setText(tecla);
                } else if (actual.equals("0")) {
                    txtPantallaCobro.setText(tecla);
                } else {
                    txtPantallaCobro.setText(actual + tecla);
                }
            }
            case "C" -> {
                txtPantallaCobro.setText("0");
                txtPantallaHistorial.setText("");
                operacionActual = "";
                aux = "";
                num1 = 0;
                num2 = 0;

            }
            case "OK" -> {
                JOptionPane.showMessageDialog(this, "Cobro procesado: $" + txtPantallaCobro.getText());
                txtPantallaCobro.setText("0");
            }
            case "=" -> {
                aux = "=";
                switch (operacionActual) {
                    case "+" -> {
                        num2 = Integer.parseInt(txtPantallaCobro.getText());
                        txtPantallaHistorial.setText(Integer.toString(num2) + "+" + Integer.toString(num1) + "=");
                        txtPantallaCobro.setText(Integer.toString(num1 + num2));
                    }
                    case "-" -> {
                        num1 -= Integer.parseInt(txtPantallaCobro.getText());
                        txtPantallaCobro.setText(Integer.toString(num1));
                    }
                    case "*" -> {
                        num1 *= Integer.parseInt(txtPantallaCobro.getText());
                        txtPantallaCobro.setText(Integer.toString(num1));
                    }
                    case "/" -> {
                        if (Integer.parseInt(txtPantallaCobro.getText()) == 0) {
                            JOptionPane.showMessageDialog(null, "No se puede divir entre cero!");
                        } else {
                            num1 /= Integer.parseInt(txtPantallaCobro.getText());
                            txtPantallaCobro.setText(Integer.toString(num1));
                        }
                    }
                }
            }
            default -> {
                System.out.println("Tecla no reconocida");
            }
        };
    }


    public void actualizarTabla(List<Producto> lista) {
        modeloTabla.setRowCount(0);
        for (Producto p : lista) {
            Object[] fila = {p.getId(), p.getNombre(), p.getTalle(), p.getPrecio(), p.getStock()};
            modeloTabla.addRow(fila);
        }
    }
}