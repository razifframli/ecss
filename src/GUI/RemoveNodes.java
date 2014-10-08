/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author End User
 */
public class RemoveNodes {

    public static JButton button1, button2;
    public static DefaultTreeModel model;
    public static TreePath path;
    public static JTree tree;
    public static DefaultMutableTreeNode nNode;
    public static MutableTreeNode mNode;
    String nodeName;
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("Removing a Node to a JTree Component!");

    public static void main(String[] args) {
        DefaultMutableTreeNode myComputer = new DefaultMutableTreeNode("My Computer");
        DefaultMutableTreeNode c = new DefaultMutableTreeNode("Local Disk(C:)");
        DefaultMutableTreeNode vinod = new DefaultMutableTreeNode("Vinod");
        DefaultMutableTreeNode swing = new DefaultMutableTreeNode("Swing");
        DefaultMutableTreeNode tr = new DefaultMutableTreeNode("Tree");
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("3½ Floppy(A:)");
        DefaultMutableTreeNode e = new DefaultMutableTreeNode("New Volume(E:)");
        c.add(vinod);
        vinod.add(swing);
        swing.add(tr);
        myComputer.add(c);
        myComputer.add(a);
        myComputer.add(e);
        tree = new JTree(myComputer);
        panel.add(tree);
        button1 = new JButton("Remove Specific Node");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model = (DefaultTreeModel) tree.getModel();
                String nodeName = JOptionPane.showInputDialog(null, "Enter the node name which have to be deleted from tree:");
                if (nodeName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Node could not be deleted from tree!");
                } else {
                    path = tree.getNextMatch(nodeName, 0, Position.Bias.Forward);
                    mNode = (MutableTreeNode) path.getLastPathComponent();
                    model.removeNodeFromParent(mNode);
                    JOptionPane.showMessageDialog(null, "Node are deleted from tree!");
                }
            }
        });
        panel.add(button1);
        button2 = new JButton("Remove Root Node");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model = (DefaultTreeModel) tree.getModel();
                model.setRoot(null);
                button1.setEnabled(false);
                button2.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Remove the root!");
            }
        });
        JButton btn3 = new JButton("Test");
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.add(tree);
                panel.revalidate();
            }
        });
        panel.add(button2);
        panel.add(btn3);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setSize(200, 300);
        frame.setVisible(true);
    }
}
