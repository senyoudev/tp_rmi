package exercice_deux.Client;

import exercice_deux.global.Compte.GestionCompte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ClientUI extends JFrame {
    private JTextField idField;
    private JButton creerCompteButton;
    private JButton ajouterButton;
    private JButton retirerButton;
    private JButton consulterSoldeButton;
    private JButton transfererSoldeButton;
    private JTextField sommeField;
    private JTextField idCField;
    private JTextField idDField;

    private String generatedId;

    public ClientUI() {
        idField = new JTextField(10);
        sommeField = new JTextField(10);
        idCField = new JTextField(10);
        idDField = new JTextField(10);

        creerCompteButton = new JButton("Créer Compte");
        ajouterButton = new JButton("Ajouter");
        retirerButton = new JButton("Retirer");
        consulterSoldeButton = new JButton("Consulter Solde");
        transfererSoldeButton = new JButton("Transférer Solde");

        creerCompteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GestionCompte gestionBancaire = RemoteManager.getGestionCompte();
                    //Generate random id
                    generatedId = UUID.randomUUID().toString();
                    gestionBancaire.creerCompte(generatedId,0);
                    JOptionPane.showMessageDialog(ClientUI.this, "Compte créé : ", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Compte créé : "+generatedId);
                } catch (Exception ex) {
                    System.err.println("Erreur : " + ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        consulterSoldeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(generatedId!=null) {
                        GestionCompte gestionBancaire = RemoteManager.getGestionCompte();
                        double solde = gestionBancaire.consulterSolde(generatedId);
                        JOptionPane.showMessageDialog(ClientUI.this, "Solde : " + solde, "Solde du Compte", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Solde : " + solde);
                    } else {
                        JOptionPane.showMessageDialog(ClientUI.this, "Veuillez créer un compte avant de consulter le solde", "Avertissement", JOptionPane.WARNING_MESSAGE);
                        System.out.println("Veuillez créer un compte avant de consulter le solde");
                    }

                } catch (Exception ex) {
                    System.err.println("Erreur : " + ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    double somme = Double.parseDouble(sommeField.getText());

                    GestionCompte gestionBancaire = RemoteManager.getGestionCompte();
                    gestionBancaire.ajouter(id, somme);
                    idField.setText("");
                    sommeField.setText("");
                    JOptionPane.showMessageDialog(ClientUI.this, "Montant ajouté avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    System.err.println("Erreur : " + ex.toString());
                    ex.printStackTrace();
                }
            }
        });

        retirerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    double somme = Double.parseDouble(sommeField.getText());

                    GestionCompte gestionBancaire = RemoteManager.getGestionCompte();
                    gestionBancaire.retirer(id, somme);
                    idField.setText("");
                    sommeField.setText("");
                    JOptionPane.showMessageDialog(ClientUI.this, "Montant retiré avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    System.err.println("Erreur : " + ex.toString());
                    ex.printStackTrace();
                }
            }
        });



        transfererSoldeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id_C = idCField.getText();
                    String id_D = idDField.getText();
                    double somme = Double.parseDouble(sommeField.getText());

                    GestionCompte gestionBancaire = RemoteManager.getGestionCompte();
                    gestionBancaire.transfererSolde(id_C, id_D, somme);

                    idCField.setText("");
                    idDField.setText("");
                    sommeField.setText("");
                    JOptionPane.showMessageDialog(ClientUI.this, "Transfert effectué avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    System.err.println("Erreur : " + ex.toString());
                    ex.printStackTrace();
                }
            }
        });


        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Somme:"));
        add(sommeField);
        add(new JLabel("ID (Compte C):"));
        add(idCField);
        add(new JLabel("ID (Compte D):"));
        add(idDField);
        add(creerCompteButton);
        add(ajouterButton);
        add(retirerButton);
        add(consulterSoldeButton);
        add(transfererSoldeButton);

        // Set layout and frame properties
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("Gestion Compte Client");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}