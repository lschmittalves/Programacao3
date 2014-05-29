package UI.Swing;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrmImportacao extends javax.swing.JFrame {

    private IFrmActionListener actionListener;

    private FrmImportacao() throws HeadlessException {
    }

    public FrmImportacao(IFrmActionListener actionListener) throws Exception {

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - (this.getSize().width / 2), (dim.height / 2) - (this.getSize().height / 2));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        seletorArquivo.setFileFilter(filter);

        this.actionListener = actionListener;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seletorArquivo = new javax.swing.JFileChooser();
        txtArquivoSelecionado = new javax.swing.JTextField();
        btnImportar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Importar");
        setName("frmBase"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        txtArquivoSelecionado.setText("Clique para selecionar um arquivo");
        txtArquivoSelecionado.setToolTipText("");
        txtArquivoSelecionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtArquivoSelecionadoMouseClicked(evt);
            }
        });

        btnImportar.setText("Importar");
        btnImportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportarMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtArquivoSelecionado)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(btnImportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtArquivoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportar)
                    .addComponent(btnCancelar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportarMouseClicked
        actionListener.onSelectFile(new File(txtArquivoSelecionado.getText()));
        this.dispose();
    }//GEN-LAST:event_btnImportarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void txtArquivoSelecionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtArquivoSelecionadoMouseClicked
        int returnVal = seletorArquivo.showOpenDialog(this);
        if (returnVal == seletorArquivo.APPROVE_OPTION) {
            txtArquivoSelecionado.setText(seletorArquivo.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_txtArquivoSelecionadoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JFileChooser seletorArquivo;
    private javax.swing.JTextField txtArquivoSelecionado;
    // End of variables declaration//GEN-END:variables
}
