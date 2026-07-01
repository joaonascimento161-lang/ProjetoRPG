package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import personagens.*;
import personagens.Adm;
import personagens.Deus;
import sistema.GameData;
import save.SaveManager;

public class MenuPrincipal extends JFrame {

    private static final Color COR_BORDA        = new Color(120, 70, 20);
    private static final Color COR_TITULO       = new Color(244, 228, 188);
    private static final Color COR_SUBTITULO    = new Color(180, 150, 100);
    private static final Color COR_BTN_FUNDO    = new Color(100, 55, 20);
    private static final Color COR_BTN_SAIR     = new Color(100, 20, 20);
    private static final Font  FONTE_BOTAO      = new Font("Serif", Font.BOLD, 16);

    public MenuPrincipal() {
        setTitle("Projeto RPG");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        PainelComFundo painel = new PainelComFundo("resources/Image/madeira.jpg");
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel logo = carregarLogo("resources/image/Logo.png", 400, 250);
        logo.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnNovo      = criarBotao("⚔  NOVO JOGO", COR_BTN_FUNDO);
        JButton btnContinuar = criarBotao("▶  CONTINUAR", COR_BTN_FUNDO);
        JButton btnSair      = criarBotao("✕  SAIR",      COR_BTN_SAIR);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setOpaque(false);
        painelBotoes.setAlignmentX(CENTER_ALIGNMENT);
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnContinuar);
        painelBotoes.add(btnSair);

        JLabel versao = new JLabel("Versão 1.0  •  João Victor F. do Nascimento");
        versao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        versao.setForeground(new Color(140, 120, 90));
        versao.setAlignmentX(CENTER_ALIGNMENT);

        painel.add(Box.createVerticalGlue());
        painel.add(logo);
        painel.add(Box.createVerticalStrut(36));
        painel.add(painelBotoes);
        painel.add(Box.createVerticalGlue());
        painel.add(versao);
        painel.add(Box.createVerticalStrut(16));

        add(painel);

        btnNovo.addActionListener(e -> abrirSelecaoDeClasse());

        btnContinuar.addActionListener(e -> {
            if (!SaveManager.existeSave()) {
                mostrarAviso("Nenhum save encontrado!", "Continuar");
                return;
            }
            Personagem jogador = SaveManager.carregar();
            if (jogador != null) {
                dispose();
                new TelaPrincipal(jogador);
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // ── Seleção de classe ─────────────────────────────────────────────────
    private void abrirSelecaoDeClasse() {
        JDialog dialogo = new JDialog(this, "Escolha sua Classe", true);
        dialogo.setSize(820, 520);
        dialogo.setLocationRelativeTo(this);
        dialogo.setResizable(false);
        dialogo.setUndecorated(true);

        JPanel fundo = new JPanel(new BorderLayout(0, 0));
        fundo.setBackground(new Color(15, 10, 6));
        fundo.setBorder(new LineBorder(COR_BORDA, 2));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 18, 10));
        header.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel lblTitulo = new JLabel("Escolha sua Classe");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 22));
        lblTitulo.setForeground(COR_TITULO);

        JLabel lblSub = new JLabel("Cada classe tem atributos e habilidades únicas");
        lblSub.setFont(new Font("Serif", Font.ITALIC, 14));
        lblSub.setForeground(COR_SUBTITULO);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(lblTitulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(lblSub);

        header.add(textos, BorderLayout.WEST);
        fundo.add(header, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 3, 12, 12));
        grid.setBackground(new Color(15, 10, 6));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ClasseInfo[] classes = {
                new ClasseInfo("Guerreiro",  "⚔",  "Alto HP e dano físico",        new Color(160, 50,  20),  () -> new Guerreiro()),
                new ClasseInfo("Mago",       "✦",  "Mana elevada, magia poderosa", new Color(60,  40,  140), () -> new Mago()),
                new ClasseInfo("Arqueiro",   "🏹", "Velocidade e precisão",        new Color(40,  100, 40),  () -> new Arqueiro()),
                new ClasseInfo("Assassino",  "🗡", "Críticos e furtividade",       new Color(60,  60,  60),  () -> new Assassino()),
                new ClasseInfo("Paladino",   "🛡", "Defesa e cura divina",         new Color(150, 130, 40),  () -> new Paladino()),
                new ClasseInfo("Berserker",  "💢", "Dano extremo, baixa defesa",   new Color(160, 30,  30),  () -> new Berserker()),
                new ClasseInfo("Curandeiro", "💚", "Cura e suporte em combate",    new Color(30,  120, 80),  () -> new Curandeiro()),
        };

        for (ClasseInfo c : classes) {
            grid.add(criarCardClasse(c, dialogo));
        }

        grid.add(criarCardDeus(dialogo));
        grid.add(criarCardAdm(dialogo));

        fundo.add(grid, BorderLayout.CENTER);
        dialogo.setContentPane(fundo);
        dialogo.setVisible(true);
    }

    // ── Card Deus (bloqueado até vencer o boss final) ─────────────────────
    private JPanel criarCardDeus(JDialog dialogo) {
        boolean desbloqueada = GameData.isDeusDesbloqueado();

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setCursor(new Cursor(desbloqueada ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));

        if (desbloqueada) {
            // Card dourado — classe disponível
            Color cor = new Color(180, 140, 20);
            card.setBackground(new Color(28, 22, 5));
            card.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(cor.darker(), 1),
                    BorderFactory.createEmptyBorder(14, 12, 14, 12)
            ));

            JLabel icone = new JLabel("✨");
            icone.setFont(new Font("Serif", Font.PLAIN, 28));
            icone.setAlignmentX(CENTER_ALIGNMENT);

            JLabel nome = new JLabel("Deus");
            nome.setFont(new Font("Serif", Font.BOLD, 14));
            nome.setForeground(new Color(240, 210, 80));
            nome.setAlignmentX(CENTER_ALIGNMENT);

            JLabel desc = new JLabel("<html><center>Poder divino e habilidades únicas</center></html>");
            desc.setFont(new Font("SansSerif", Font.PLAIN, 11));
            desc.setForeground(new Color(190, 165, 80));
            desc.setAlignmentX(CENTER_ALIGNMENT);

            card.add(Box.createVerticalGlue());
            card.add(icone);
            card.add(Box.createVerticalStrut(6));
            card.add(nome);
            card.add(Box.createVerticalStrut(4));
            card.add(desc);
            card.add(Box.createVerticalGlue());

            card.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    card.setBackground(new Color(40, 32, 8));
                    card.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(cor, 1),
                            BorderFactory.createEmptyBorder(14, 12, 14, 12)
                    ));
                }
                @Override public void mouseExited(MouseEvent e) {
                    card.setBackground(new Color(28, 22, 5));
                    card.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(cor.darker(), 1),
                            BorderFactory.createEmptyBorder(14, 12, 14, 12)
                    ));
                }
                @Override public void mouseClicked(MouseEvent e) {
                    dialogo.dispose();
                    dispose();
                    new TelaPrincipal(new Deus());
                }
            });

        } else {
            // Card bloqueado — escuro, sem interação
            card.setBackground(new Color(18, 15, 5));
            card.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(55, 45, 10), 1),
                    BorderFactory.createEmptyBorder(14, 12, 14, 12)
            ));

            JLabel icone = new JLabel("⭐");
            icone.setFont(new Font("Serif", Font.PLAIN, 26));
            icone.setForeground(new Color(70, 60, 15));
            icone.setAlignmentX(CENTER_ALIGNMENT);

            JLabel lblNome = new JLabel("???");
            lblNome.setFont(new Font("Serif", Font.BOLD, 14));
            lblNome.setForeground(new Color(80, 65, 20));
            lblNome.setAlignmentX(CENTER_ALIGNMENT);

            JLabel lblDica = new JLabel("<html><center>Derrote o Boss Final para desbloquear</center></html>");
            lblDica.setFont(new Font("SansSerif", Font.ITALIC, 10));
            lblDica.setForeground(new Color(70, 58, 18));
            lblDica.setAlignmentX(CENTER_ALIGNMENT);

            card.add(Box.createVerticalGlue());
            card.add(icone);
            card.add(Box.createVerticalStrut(6));
            card.add(lblNome);
            card.add(Box.createVerticalStrut(4));
            card.add(lblDica);
            card.add(Box.createVerticalGlue());
        }

        return card;
    }

    // ── Card ADM com campo de senha ───────────────────────────────────────
    private JPanel criarCardAdm(JDialog dialogo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(18, 12, 8));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(50, 35, 15), 1),
                BorderFactory.createEmptyBorder(14, 12, 14, 12)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel icone = new JLabel("🔒");
        icone.setFont(new Font("Serif", Font.PLAIN, 26));
        icone.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblSenha = new JLabel("Acesso restrito");
        lblSenha.setFont(new Font("Serif", Font.ITALIC, 11));
        lblSenha.setForeground(new Color(70, 55, 30));
        lblSenha.setAlignmentX(CENTER_ALIGNMENT);

        JPasswordField campoSenha = new JPasswordField(12);
        campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        campoSenha.setFont(new Font("Monospaced", Font.PLAIN, 13));
        campoSenha.setBackground(new Color(30, 20, 10));
        campoSenha.setForeground(new Color(200, 180, 120));
        campoSenha.setCaretColor(new Color(200, 180, 120));
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(90, 60, 20), 1),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        campoSenha.setVisible(false);

        JLabel lblErro = new JLabel(" ");
        lblErro.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblErro.setForeground(new Color(200, 60, 60));
        lblErro.setAlignmentX(CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(icone);
        card.add(Box.createVerticalStrut(6));
        card.add(lblSenha);
        card.add(Box.createVerticalStrut(8));
        card.add(campoSenha);
        card.add(Box.createVerticalStrut(4));
        card.add(lblErro);
        card.add(Box.createVerticalGlue());

        card.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(28, 18, 10));
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(80, 55, 20), 1),
                        BorderFactory.createEmptyBorder(14, 12, 14, 12)
                ));
            }
            @Override public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(18, 12, 8));
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(50, 35, 15), 1),
                        BorderFactory.createEmptyBorder(14, 12, 14, 12)
                ));
            }
            @Override public void mouseClicked(MouseEvent e) {
                if (!campoSenha.isVisible()) {
                    campoSenha.setVisible(true);
                    lblSenha.setText("Digite a senha:");
                    card.revalidate();
                    campoSenha.requestFocusInWindow();
                }
            }
        });

        campoSenha.addActionListener(e -> {
            String senha = new String(campoSenha.getPassword());
            campoSenha.setText("");

            Adm adm = Adm.tentarCriar(senha);
            if (adm != null) {
                dialogo.dispose();
                dispose();
                new TelaPrincipal(adm);
            } else {
                lblErro.setText("❌ Senha incorreta");
                Timer timer = new Timer(2000, ev -> lblErro.setText(" "));
                timer.setRepeats(false);
                timer.start();
                campoSenha.setVisible(false);
                lblSenha.setText("Acesso restrito");
                card.revalidate();
            }
        });

        return card;
    }

    // ── Card de classe normal ─────────────────────────────────────────────
    private JPanel criarCardClasse(ClasseInfo info, JDialog dialogo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(28, 18, 12));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(info.cor.darker(), 1),
                BorderFactory.createEmptyBorder(14, 12, 14, 12)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel icone = new JLabel(info.icone);
        icone.setFont(new Font("Serif", Font.PLAIN, 28));
        icone.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nome = new JLabel(info.nome);
        nome.setFont(new Font("Serif", Font.BOLD, 14));
        nome.setForeground(COR_TITULO);
        nome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel desc = new JLabel("<html><center>" + info.descricao + "</center></html>");
        desc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        desc.setForeground(COR_SUBTITULO);
        desc.setAlignmentX(CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(icone);
        card.add(Box.createVerticalStrut(6));
        card.add(nome);
        card.add(Box.createVerticalStrut(4));
        card.add(desc);
        card.add(Box.createVerticalGlue());

        card.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(40, 26, 16));
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(info.cor, 1),
                        BorderFactory.createEmptyBorder(14, 12, 14, 12)
                ));
            }
            @Override public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(28, 18, 12));
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(info.cor.darker(), 1),
                        BorderFactory.createEmptyBorder(14, 12, 14, 12)
                ));
            }
            @Override public void mouseClicked(MouseEvent e) {
                dialogo.dispose();
                dispose();
                new TelaPrincipal(info.fabricar.get());
            }
        });

        return card;
    }

    // ── Helpers ───────────────────────────────────────────────────────────
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(cor);
        btn.setForeground(COR_TITULO);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 52));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(cor.brighter(), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(cor.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(cor); }
        });
        return btn;
    }

    private JLabel carregarLogo(String caminho, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminho);
        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private void mostrarAviso(String msg, String titulo) {
        JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.WARNING_MESSAGE);
    }

    // ── Classe interna de dados ───────────────────────────────────────────
    private static class ClasseInfo {
        final String nome;
        final String icone;
        final String descricao;
        final Color cor;
        final java.util.function.Supplier<Personagem> fabricar;

        ClasseInfo(String nome, String icone, String descricao, Color cor,
                   java.util.function.Supplier<Personagem> fabricar) {
            this.nome      = nome;
            this.icone     = icone;
            this.descricao = descricao;
            this.cor       = cor;
            this.fabricar  = fabricar;
        }
    }
}