package exercice_deux.Client;

import javax.swing.*;


public class GestionCompteClient {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientUI();
            }
        });
    }
}
