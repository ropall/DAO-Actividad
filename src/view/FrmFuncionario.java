package view;

import dao.FuncionarioDAO;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class FrmFuncionario extends JFrame {

    private JTextField txtId, txtTipoId, txtNumeroId, txtNombres, txtApellidos,
            txtEstadoCivil, txtSexo, txtDireccion, txtTelefono, txtFechaNac;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnListar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JScrollPane scroll;
    private FuncionarioDAO dao = new FuncionarioDAO();

    public FrmFuncionario() {
        setTitle("Gestión de Funcionarios - RRHH");
        setSize(950, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false); // 👈 Ventana con tamaño fijo

        JLabel lblTitulo = new JLabel("CRUD de Funcionarios");
        lblTitulo.setBounds(380, 10, 300, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 50, 100, 20);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(120, 50, 100, 20);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblTipoId = new JLabel("Tipo ID:");
        lblTipoId.setBounds(20, 80, 100, 20);
        add(lblTipoId);
        txtTipoId = new JTextField();
        txtTipoId.setBounds(120, 80, 100, 20);
        add(txtTipoId);

        JLabel lblNumId = new JLabel("Número ID:");
        lblNumId.setBounds(20, 110, 100, 20);
        add(lblNumId);
        txtNumeroId = new JTextField();
        txtNumeroId.setBounds(120, 110, 100, 20);
        add(txtNumeroId);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(20, 140, 100, 20);
        add(lblNombres);
        txtNombres = new JTextField();
        txtNombres.setBounds(120, 140, 150, 20);
        add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 170, 100, 20);
        add(lblApellidos);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 170, 150, 20);
        add(txtApellidos);

        JLabel lblEstadoCivil = new JLabel("Estado Civil:");
        lblEstadoCivil.setBounds(20, 200, 100, 20);
        add(lblEstadoCivil);
        txtEstadoCivil = new JTextField();
        txtEstadoCivil.setBounds(120, 200, 150, 20);
        add(txtEstadoCivil);

        JLabel lblSexo = new JLabel("Sexo (M/F):");
        lblSexo.setBounds(20, 230, 100, 20);
        add(lblSexo);
        txtSexo = new JTextField();
        txtSexo.setBounds(120, 230, 50, 20);
        add(txtSexo);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 260, 100, 20);
        add(lblDireccion);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 260, 200, 20);
        add(txtDireccion);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 290, 100, 20);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 290, 150, 20);
        add(txtTelefono);

        JLabel lblFecha = new JLabel("Fecha Nac (YYYY-MM-DD):");
        lblFecha.setBounds(20, 320, 180, 20);
        add(lblFecha);
        txtFechaNac = new JTextField();
        txtFechaNac.setBounds(200, 320, 120, 20);
        add(txtFechaNac);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 360, 100, 30);
        add(btnAgregar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(130, 360, 100, 30);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 360, 100, 30);
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(350, 360, 100, 30);
        add(btnListar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(460, 360, 100, 30);
        add(btnLimpiar);

        // Tabla
        modelo = new DefaultTableModel(new String[]{
                "ID", "Tipo ID", "Número ID", "Nombres", "Apellidos",
                "Estado Civil", "Sexo", "Dirección", "Teléfono", "Fecha Nac"
        }, 0);

        tabla = new JTable(modelo);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 👈 Para que aparezca scroll horizontal si se necesita

        // 👇 Ajuste de ancho total y scroll
        scroll = new JScrollPane(tabla);
        scroll.setBounds(330, 50, 580, 280); // Más angosta para no tapar botones
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll);

        // Listeners
        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    txtId.setText(modelo.getValueAt(fila, 0).toString());
                    txtTipoId.setText(modelo.getValueAt(fila, 1).toString());
                    txtNumeroId.setText(modelo.getValueAt(fila, 2).toString());
                    txtNombres.setText(modelo.getValueAt(fila, 3).toString());
                    txtApellidos.setText(modelo.getValueAt(fila, 4).toString());
                    txtEstadoCivil.setText(modelo.getValueAt(fila, 5).toString());
                    txtSexo.setText(modelo.getValueAt(fila, 6).toString());
                    txtDireccion.setText(modelo.getValueAt(fila, 7).toString());
                    txtTelefono.setText(modelo.getValueAt(fila, 8).toString());
                    txtFechaNac.setText(modelo.getValueAt(fila, 9).toString());
                }
            }
        });
    }

    // --- Métodos CRUD ---
    private void agregar() {
        try {
            Funcionario f = new Funcionario(
                    txtTipoId.getText(),
                    txtNumeroId.getText(),
                    txtNombres.getText(),
                    txtApellidos.getText(),
                    txtEstadoCivil.getText(),
                    txtSexo.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    Date.valueOf(txtFechaNac.getText())
            );
            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Funcionario agregado exitosamente.");
            listar();
            limpiarCampos();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            modelo.setRowCount(0);
            List<Funcionario> lista = dao.listar();
            for (Funcionario f : lista) {
                modelo.addRow(new Object[]{
                        f.getIdFuncionario(), f.getTipoIdentificacion(), f.getNumeroIdentificacion(),
                        f.getNombres(), f.getApellidos(), f.getEstadoCivil(),
                        f.getSexo(), f.getDireccion(), f.getTelefono(), f.getFechaNacimiento()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + ex.getMessage());
        }
    }

private void actualizar() {
    try {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para actualizar.");
            return;
        }

        Funcionario f = new Funcionario();
        f.setIdFuncionario(Integer.parseInt(txtId.getText()));
        f.setTipoIdentificacion(txtTipoId.getText());
        f.setNumeroIdentificacion(txtNumeroId.getText());
        f.setNombres(txtNombres.getText());
        f.setApellidos(txtApellidos.getText());
        f.setEstadoCivil(txtEstadoCivil.getText());
        f.setSexo(txtSexo.getText());
        f.setDireccion(txtDireccion.getText());
        f.setTelefono(txtTelefono.getText());

        if (!txtFechaNac.getText().isEmpty()) {
            f.setFechaNacimiento(Date.valueOf(txtFechaNac.getText()));
        }

        dao.actualizar(f);
        JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente.");
        listar();
        limpiarCampos();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
    }
}

    private void eliminar() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un funcionario para eliminar.");
                return;
            }
            int id = Integer.parseInt(txtId.getText());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(this, "Funcionario eliminado.");
            listar();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtTipoId.setText("");
        txtNumeroId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEstadoCivil.setText("");
        txtSexo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtFechaNac.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmFuncionario().setVisible(true));
    }
}