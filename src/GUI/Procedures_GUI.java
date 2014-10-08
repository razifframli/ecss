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
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author End User
 */
public class Procedures_GUI extends JTree {
    
    private static JTree tree;
    public Procedures_GUI() {
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        
        DefaultMutableTreeNode anak = new DefaultMutableTreeNode("anak");
 
        vegetableNode.add(anak);
        
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
        
        this.setModel(new DefaultTreeModel(root));
    }
    
    static JPanel p1 = new JPanel();
    static JPanel p2 = new JPanel();
    public static void main(String[] args) {
        JFrame f1 = new JFrame();
        f1.setSize(200,200);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("hai");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Procedures_GUI pgui = new Procedures_GUI();
                p1.add(new Procedures_GUI());
                p1.revalidate();
            }
        });
        p2.add(b1);
        p2.add(p1);
        f1.add(p2);
        f1.setVisible(true);
    }
}
