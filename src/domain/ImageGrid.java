/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Interface.MainFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jeanp
 */
public class ImageGrid extends MainFrame {

    String getFileDirection;//direccion string del archivo seleccionado
    String getFileName;
    ImageIcon drawImage;

    public ImageGrid() {
        getFileDirection="";
        getFileName ="";
        drawImage = new ImageIcon(new ImageIcon(getClass().
          getResource(getFileName)).getImage());
    }
   
    //metodo para dibujar la imagen mediante graphics
    public void loadImage(Graphics g){
        g.drawImage(drawImage.getImage(), 0, 0, 200,200,null);
    }

    public void imageSplit(int rows, int columns, JFileChooser jfc) throws IOException {
        
        File file = new File("C:\\Users\\jeanp\\OneDrive\\Escritorio\\19120x1080.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file

        // determina el tamaño del chunk que se quiere tomar las imagenes
        int chunkWidth = image.getWidth() / columns;
        int chunkHeight = image.getHeight() / rows;
        int chunks = rows * columns;
        int count = 0;

        //ArrayImage para que guarde los chunks de imagenes
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                //Inicializo el array con chunks de imagen
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // dibuja el chunk de imagen
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        System.out.println("Imagen dividida");

        //escribe los chunks de imagenes en files de imagenes
        for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));
        }
        System.out.println("Mini images created");
    }//fin de imageSplit

    
}
