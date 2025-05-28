import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistroEstudiantes extends JFrame {

    // Componentes
    private final JTextField txtNombre, txtEdad, txtCodigo;
    private final JRadioButton rbMasculino, rbFemenino;
    private final ButtonGroup grupoGenero;
    private final JTable tabla;
    private final DefaultTableModel modeloTabla;

    public RegistroEstudiantes() {
        setTitle("Gestor de Registro de Estudiantes");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2)); // Aumentado a 6 filas

        // Labels y TextFields
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        // Género
        panelFormulario.add(new JLabel("Género:"));
        JPanel panelGenero = new JPanel();
        rbMasculino = new JRadioButton("Masculino");
        rbFemenino = new JRadioButton("Femenino");
        grupoGenero = new ButtonGroup();
        grupoGenero.add(rbMasculino);
        grupoGenero.add(rbFemenino);
        panelGenero.add(rbMasculino);
        panelGenero.add(rbFemenino);
        panelFormulario.add(panelGenero);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnEliminar = new JButton("Eliminar"); // Nuevo botón

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnLimpiar);
        panelFormulario.add(new JLabel()); // Celda vacía
        panelFormulario.add(btnEliminar); // Agregado en nueva fila

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Edad", "Código", "Género"}, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Menú
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemSalir = new JMenuItem("Salir");

        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemSalir);
        barraMenu.add(menuArchivo);
        setJMenuBar(barraMenu);

        // Acciones
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        itemSalir.addActionListener(e -> System.exit(0));
        itemNuevo.addActionListener(e -> limpiarCampos());
    }

    private void agregarEstudiante() {
        String nombre = txtNombre.getText();
        String edad = txtEdad.getText();
        String codigo = txtCodigo.getText();
        String genero = rbMasculino.isSelected() ? "Masculino" : rbFemenino.isSelected() ? "Femenino" : "";

        if (nombre.isEmpty() || edad.isEmpty() || codigo.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        modeloTabla.addRow(new Object[]{nombre, edad, codigo, genero});
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtCodigo.setText("");
        grupoGenero.clearSelection();
    }

    private void eliminarEstudiante() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            modeloTabla.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroEstudiantes().setVisible(true));
    }
}
