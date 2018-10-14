package org.dos.tournament.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.util.ResourceBundle;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.JSeparator;

import org.dos.tournament.application.petanque.panels.PetanqueSuperMeleePanel;
import org.dos.tournament.application.petanque.panels.tablemodels.ParticipantsTableModel;
import org.dos.tournament.petanque.tournament.movement.SuperMelee;
import org.dos.tournament.petanque.tournament.movement.SuperMeleeClubChampionship;
import org.dos.tournament.player.AssociationAttendee;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.Dialog.ModalExclusionType;

public class TournamentManagerUI
{

  private JFrame frmTurnierverwaltung;
  private final Action actionExit = new SwingAction();
  private final Action createTournament = new CreateTournamentAction(this.frmTurnierverwaltung);

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          TournamentManagerUI window = new TournamentManagerUI();
          window.frmTurnierverwaltung.setVisible(true);
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public TournamentManagerUI()
  {
    initialize();
    ((CreateTournamentAction)this.createTournament).updateApplicationFrame(frmTurnierverwaltung);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frmTurnierverwaltung = new JFrame();
    frmTurnierverwaltung.setMinimumSize(new Dimension(800, 600));
    frmTurnierverwaltung.getContentPane().setSize(new Dimension(600, 800));
    frmTurnierverwaltung.getContentPane().setMinimumSize(new Dimension(600, 800));
    frmTurnierverwaltung.setSize(new Dimension(1024, 768));
    frmTurnierverwaltung.setTitle(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.frmTurnierverwaltung.title")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
    frmTurnierverwaltung.setBounds(100, 100, 450, 300);
    frmTurnierverwaltung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JMenuBar menuBar = new JMenuBar();
    frmTurnierverwaltung.setJMenuBar(menuBar);
    
    JMenu mnDatei = new JMenu(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.mnDatei.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
    menuBar.add(mnDatei);
    
    JMenuItem mntmNeuesTurnier = new JMenuItem(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.mntmNeuesTurnier.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
    mntmNeuesTurnier.setAction(createTournament);
    mnDatei.add(mntmNeuesTurnier);
    
    JMenuItem mntmTurnierSpeichern = new JMenuItem(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.mntmTurnierSpeichern.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
    mnDatei.add(mntmTurnierSpeichern);
    
    JSeparator separator = new JSeparator();
    mnDatei.add(separator);
    
    JMenuItem mntmTurnierverwaltungBeenden = new JMenuItem(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.mntmTurnierverwaltungBeenden.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
    mntmTurnierverwaltungBeenden.setAction(actionExit);
    mnDatei.add(mntmTurnierverwaltungBeenden);
  }

  private class SwingAction extends AbstractAction {
    public SwingAction() {
      putValue(NAME, ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.action.name")); //$NON-NLS-1$ //$NON-NLS-2$
      putValue(SHORT_DESCRIPTION, ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.actionExit.short description")); //$NON-NLS-1$ //$NON-NLS-2$
    }
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }
  private class CreateTournamentAction extends AbstractAction {
    private JFrame frmApplication;
    public CreateTournamentAction(JFrame frmApplication) {
      putValue(NAME, ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.createTournament.name")); //$NON-NLS-1$ //$NON-NLS-2$
      putValue(SHORT_DESCRIPTION, ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("TournamentManagerUI.createTournament.short description")); //$NON-NLS-1$ //$NON-NLS-2$

      this.frmApplication = frmApplication;
    }
    
    public void updateApplicationFrame(JFrame frame)
    {
      this.frmApplication = frame;
    }
    
    public void actionPerformed(ActionEvent e) {
      if(null != this.frmApplication)
      {
        ParticipantsTableModel _tableModel = new ParticipantsTableModel();
        SuperMelee _tournament = new SuperMeleeClubChampionship();
        //_tournament.addObserver(_tableModel);
        //_tournament.addCompetitor(new AssociationAttendee(1,"Max Mustermann", "BG Gross-Gerau"));
        PetanqueSuperMeleePanel _panel = new PetanqueSuperMeleePanel(_tournament);
        _panel.setTournament(_tournament);
        this.frmApplication.getContentPane().add(_panel);
        this.frmApplication.revalidate();
        /*
        this.frmApplication.getContentPane().doLayout();
        this.frmApplication.getWindowListeners().notifyAll();
        this.frmApplication.getContentPane().repaint();
        this.frmApplication.repaint();*/
      }
    }
  }
}
