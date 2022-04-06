package gamejava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputListener extends JFrame implements KeyListener {
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //libreria para obtener tamaño pantalla
    double width = screenSize.getWidth();   //obtiene el ancho de la pantalla en px
    double height = screenSize.getHeight(); //obtiene el alto de la pantalla en px
    int windowWidth = 200, windowHeight = 100, xOffset = 400, yOffset = 200; //separacion de la ventana respecto la esquina inferior derecha

    InputListener() {
        this.setTitle("Input listener"); //titulo de la ventana java
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //comportamineto al cerrar la ventana
        this.setResizable(false); //no permite el redimensionado de la ventana
        this.setSize(windowWidth, windowHeight); //establece el ancho y alto de la ventana
        this.setLocation((int) width - xOffset, (int) height - yOffset); //posicion de inicio de la ventana
        this.setVisible(true); //ventana visible
        this.getContentPane().setBackground(new Color(24, 24, 24)); //color de fondo
        this.addKeyListener(this); //añade los metodos de camptura de teclas
        this.setAlwaysOnTop(true); //ventana siempre encima del resto

        JLabel focusLabel = new JLabel(); //crea un titulo (para dentro la ventana)
        focusLabel.setText("Playing"); //establece el texto de la ventana
        focusLabel.setForeground(Color.white); //color del titulo

        this.addWindowFocusListener(new WindowFocusListener() { //permite detectar si la ventana tiene o no el foco
            @Override
            public void windowGainedFocus(WindowEvent e) {
                //System.out.println("Venatana con foco");
                focusLabel.setText("Playing");
            }
            
            @Override
            public void windowLostFocus(WindowEvent e) {
                //System.out.println("Venatana sin foco");
                focusLabel.setText("Focus this window to play");
            }
        });
        this.add(focusLabel); //añade el titulo (label) creado a la ventana
    }
    
    @Override
    public void keyPressed(KeyEvent e) { //detecta tecla pulsada
        switch (e.getKeyCode()) {
            case 38: // flecha arriba
            case 87: // w
                GameJava.INPUT = "up";
                break;
            case 40: // flecha abajo
            case 83: // s
                GameJava.INPUT = "down";
                break;
            case 37: // flecha izquierda
            case 65: // a
                GameJava.INPUT = "left";
                break;
            case 39: // flecha derecha
            case 68: // d
                GameJava.INPUT = "right";
                break;
            case 32: // espacio
                GameJava.INPUT = "space";
                break;
            case 10: // enter
                GameJava.INPUT = "enter";
                break;
            case 27: // ESC
                GameJava.INPUT = "escape";
                break;
            default:
                GameJava.INPUT = String.valueOf(e.getKeyChar());
        }

        ////////// IMPRIME EL CARACTER Y EL CODIGO DE CUALQUIER INPUT //////////
        //System.out.println("Char: " + e.getKeyChar() + "\tCode: " + e.getKeyCode());
    }

    ////////// METODOS ABSTRACTOS EXTRA //////////
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
