/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemover;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;

/**
 *
 * @author Tore
 */
public class HiloMovimiento implements Runnable {
    private final JLabel label;
    
    public HiloMovimiento(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            Random random = new Random();

            // Tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();
            
            while(!Thread.currentThread().isInterrupted()) {
                int x = random.nextInt(screenWidth);
                int y = random.nextInt(screenHeight);
                
                label.setText("Moved to: (" + x + ", " + y + ")");
                
                // Simular acciones en el Mouse(ratón).
                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                
                // Pausa entre movimientos
                TimeUnit.MILLISECONDS.sleep(5000);
            }
        } catch (AWTException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
