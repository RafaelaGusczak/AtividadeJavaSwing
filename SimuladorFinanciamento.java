import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class SimuladorFinanciamento extends JFrame {

    // Veículo
    private JComboBox<String> cbMarca;
    private JTextField txtModelo;
    private JComboBox<Integer> cbAno;
    private JTextField txtValor;
    private JRadioButton rbNovo;
    private JRadioButton rbUsado;
    private ButtonGroup bgTipo;

    // Painel e campos de Veículo Usado
    private JPanel panelUsado;
    private JTextField txtQuilometragem;
    private JTextField txtProprietarios;

    // Componentes de Financiamento
    private JCheckBox chkPossuiEntrada;
    private JLabel lblEntrada;
    private JTextField txtEntrada;
    private JComboBox<Integer> cbParcelas;

    // Botões
    private JButton btnCalcular;
    private JButton btnLimpar;

    // Painel e labels do Resultado
    private JPanel panelResultado;
    private JLabel lblValorFinanciado;
    private JLabel lblValorParcela;
    private JLabel lblTotalAPagar;

    public SimuladorFinanciamento() {
        // Configurações da Janela Principal
        setTitle("Financiamento de Carros");
        setSize(420, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel Principal 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // ==========================================
        // TÍTULO 
        // ==========================================
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblTitulo = new JLabel("Financiamento de Carros");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);

        mainPanel.add(panelTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==========================================
        // 1. DADOS DO VEÍCULO
        // ==========================================
        JLabel lblDadosVeiculo = new JLabel("Dados do Veículo");
        lblDadosVeiculo.setFont(new Font("Arial", Font.BOLD, 14));
        lblDadosVeiculo.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblDadosVeiculo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel panelFormVeiculo = criarPainelFormulario(140);
        GridBagConstraints gbc = getGbc();

        // Marca 
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.35;
        panelFormVeiculo.add(new JLabel("Marca"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        String[] marcas = {"FIAT", "Chevrolet", "Volkswagen", "Ford", "Toyota", "Honda", "Hyundai"};
        cbMarca = new JComboBox<>(marcas);
        panelFormVeiculo.add(cbMarca, gbc);

        // Modelo 
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.35;
        panelFormVeiculo.add(new JLabel("Modelo"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtModelo = new JTextField();
        panelFormVeiculo.add(txtModelo, gbc);

        // Ano 
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.35;
        panelFormVeiculo.add(new JLabel("Ano"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        cbAno = new JComboBox<>();
        for (int ano = 2026; ano >= 2000; ano--) {
            cbAno.addItem(ano);
        }
        panelFormVeiculo.add(cbAno, gbc);

        // Valor
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.35;
        panelFormVeiculo.add(new JLabel("Valor"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtValor = new JTextField();
        panelFormVeiculo.add(txtValor, gbc);

        mainPanel.add(panelFormVeiculo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tipo: NOVO / USADO 
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelTipo.add(new JLabel("Tipo"));
        rbNovo = new JRadioButton("NOVO");
        rbUsado = new JRadioButton("USADO", true);
        bgTipo = new ButtonGroup();
        bgTipo.add(rbNovo);
        bgTipo.add(rbUsado);
        panelTipo.add(rbNovo);
        panelTipo.add(rbUsado);

        mainPanel.add(panelTipo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ==========================================
        // 2. DADOS DO VEÍCULO USADO 
        // ==========================================
        panelUsado = criarPainelFormulario(110);
        panelUsado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createDashedBorder(Color.GRAY, 2, 2),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));

        GridBagConstraints gbcU = getGbc();
        gbcU.insets = new Insets(2, 2, 2, 2);

        // Título 
        gbcU.gridx = 0; gbcU.gridy = 0; gbcU.gridwidth = 2;
        gbcU.weightx = 1.0;
        JLabel lblTituloUsado = new JLabel("Dados do Veículo Usado");
        lblTituloUsado.setFont(new Font("Arial", Font.BOLD, 13));
        panelUsado.add(lblTituloUsado, gbcU);

        // Quilometragem 
        gbcU.gridwidth = 1;
        gbcU.gridx = 0; gbcU.gridy = 1; gbcU.weightx = 0.35;
        panelUsado.add(new JLabel("Quilometragem"), gbcU);
        gbcU.gridx = 1; gbcU.weightx = 0.65;
        txtQuilometragem = new JTextField();
        panelUsado.add(txtQuilometragem, gbcU);

        // Proprietários 
        gbcU.gridx = 0; gbcU.gridy = 2; gbcU.weightx = 0.35;
        panelUsado.add(new JLabel("Proprietários"), gbcU);
        gbcU.gridx = 1; gbcU.weightx = 0.65;
        txtProprietarios = new JTextField();
        panelUsado.add(txtProprietarios, gbcU);

        mainPanel.add(panelUsado);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==========================================
        // 3. FINANCIAMENTO
        // ==========================================
        JLabel lblFinanciamento = new JLabel("Financiamento");
        lblFinanciamento.setFont(new Font("Arial", Font.BOLD, 14));
        lblFinanciamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblFinanciamento);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel panelFinanciamento = criarPainelFormulario(110);
        GridBagConstraints gbcF = getGbc();

        // Checkbox 
        gbcF.gridx = 0; gbcF.gridy = 0; gbcF.gridwidth = 2;
        chkPossuiEntrada = new JCheckBox("Possui entrada?", true);
        panelFinanciamento.add(chkPossuiEntrada, gbcF);

        // Entrada
        gbcF.gridwidth = 1;
        gbcF.gridx = 0; gbcF.gridy = 1; gbcF.weightx = 0.35;
        lblEntrada = new JLabel("Entrada");
        panelFinanciamento.add(lblEntrada, gbcF);

        gbcF.gridx = 1; gbcF.weightx = 0.65;
        txtEntrada = new JTextField();
        panelFinanciamento.add(txtEntrada, gbcF);

        // Parcelas 
        gbcF.gridx = 0; gbcF.gridy = 2; gbcF.weightx = 0.35;
        panelFinanciamento.add(new JLabel("Parcelas"), gbcF);

        gbcF.gridx = 1; gbcF.weightx = 0.65;
        Integer[] parcelas = {12, 24, 36, 48, 60};
        cbParcelas = new JComboBox<>(parcelas);
        cbParcelas.setSelectedItem(36);
        panelFinanciamento.add(cbParcelas, gbcF);

        mainPanel.add(panelFinanciamento);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==========================================
        // 4. BOTÕES 
        // ==========================================
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotoes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnCalcular = new JButton("CALCULAR");
        btnLimpar = new JButton("LIMPAR");
        panelBotoes.add(btnCalcular);
        panelBotoes.add(btnLimpar);

        mainPanel.add(panelBotoes);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==========================================
        // 5. RESULTADO 
        // ==========================================
        panelResultado = new JPanel();
        panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.Y_AXIS));
        panelResultado.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblResultadoTitulo = new JLabel("Resultado");
        lblResultadoTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultadoTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelResultado.add(lblResultadoTitulo);
        panelResultado.add(Box.createRigidArea(new Dimension(0, 8)));

        lblValorFinanciado = new JLabel("Valor financiado: R$ 0,00");
        lblValorParcela = new JLabel("Valor da parcela: R$ 0,00");
        lblTotalAPagar = new JLabel("Total a pagar: R$ 0,00");

        lblValorFinanciado.setFont(new Font("Arial", Font.PLAIN, 13));
        lblValorParcela.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTotalAPagar.setFont(new Font("Arial", Font.PLAIN, 13));

        panelResultado.add(lblValorFinanciado);
        panelResultado.add(Box.createRigidArea(new Dimension(0, 4)));
        panelResultado.add(lblValorParcela);
        panelResultado.add(Box.createRigidArea(new Dimension(0, 4)));
        panelResultado.add(lblTotalAPagar);

        panelResultado.setVisible(false);
        mainPanel.add(panelResultado);

        add(new JScrollPane(mainPanel));

        // ==========================================
        // EVENTOS E AÇÕES
        // ==========================================

        // Alternar exibição do painel de veículo usado
        ActionListener listenerTipo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelUsado.setVisible(rbUsado.isSelected());
                revalidate();
                repaint();
            }
        };
        rbNovo.addActionListener(listenerTipo);
        rbUsado.addActionListener(listenerTipo);

        // Alternar exibição do campo de entrada
        chkPossuiEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean visivel = chkPossuiEntrada.isSelected();
                lblEntrada.setVisible(visivel);
                txtEntrada.setVisible(visivel);
                revalidate();
                repaint();
            }
        });

        // Ação do Botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
            }
        });

        // Ação do Botão Calcular
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularFinanciamento();
            }
        });
    }

    private JPanel criarPainelFormulario(int alturaMaxima) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alturaMaxima));
        return panel;
    }

    private GridBagConstraints getGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 2, 3, 2);
        return gbc;
    }

    private void calcularFinanciamento() {
        // 1. Validação do Campo VALOR 
        String strValor = txtValor.getText().trim().replace(",", ".");
        if (strValor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o valor do veículo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        double valorVeiculo;
        try {
            valorVeiculo = Double.parseDouble(strValor);
            if (valorVeiculo <= 0) {
                JOptionPane.showMessageDialog(this, "O valor do veículo deve ser maior que zero!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo Valor não pode conter letras!", "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Validação do Painel de Veículo USADO
        if (rbUsado.isSelected()) {
            String strKm = txtQuilometragem.getText().trim();
            String strProp = txtProprietarios.getText().trim();

            if (strKm.isEmpty() || strProp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha a Quilometragem e os Proprietários do veículo usado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Long.parseLong(strKm);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "O campo Quilometragem não pode conter letras!", "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 3. Validação do Campo ENTRADA 
        double entrada = 0.0;
        if (chkPossuiEntrada.isSelected()) {
            String strEntrada = txtEntrada.getText().trim().replace(",", ".");
            if (strEntrada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o valor da entrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                entrada = Double.parseDouble(strEntrada);
                if (entrada < 0 || entrada >= valorVeiculo) {
                    JOptionPane.showMessageDialog(this, "A entrada deve ser maior/igual a zero e menor que o valor do veículo!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "O campo Entrada não pode conter letras!", "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 4. CÁLCULO DO FINANCIAMENTO
        int numeroParcelas = (Integer) cbParcelas.getSelectedItem();
        double taxa = 0.32; // Taxa fixa simples de exemplo (32%)

        double valorFinanciado = valorVeiculo - entrada;
        double valorTotal = valorFinanciado * (1 + taxa);
        double valorParcela = valorTotal / numeroParcelas;
        double totalAPagar = valorParcela * numeroParcelas;

        DecimalFormat df = new DecimalFormat("R$ #,##0.00");

        // Atualização dos Rótulos do Resultado
        lblValorFinanciado.setText("Valor financiado: " + df.format(valorFinanciado));
        lblValorParcela.setText("Valor da parcela: " + df.format(valorParcela));
        lblTotalAPagar.setText("Total a pagar: " + df.format(totalAPagar));

        // Exibe o painel de resultado apenas após o cálculo efetuado
        panelResultado.setVisible(true);
        revalidate();
        repaint();
    }

    private void limparFormulario() {
        cbMarca.setSelectedIndex(0);
        txtModelo.setText("");
        cbAno.setSelectedIndex(0);
        txtValor.setText("");

        rbUsado.setSelected(true);
        panelUsado.setVisible(true);
        txtQuilometragem.setText("");
        txtProprietarios.setText("");

        chkPossuiEntrada.setSelected(true);
        lblEntrada.setVisible(true);
        txtEntrada.setVisible(true);
        txtEntrada.setText("");

        cbParcelas.setSelectedItem(36);
        panelResultado.setVisible(false);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimuladorFinanciamento().setVisible(true);
            }
        });
    }
}
