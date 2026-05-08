/*
 * Proxy
    | virtual
    | protection
    | remote

    the proxy pattern provides a surrogate or placeholder for another object to control access to it
 */

interface IImage{
   void display();
}

class RealImage implements IImage {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("[RealImage] Loading image from disk: " + filename);
    }

    @Override
    public void display() {
        System.out.println("[RealImage] Displaying " + filename);
    }
}

class ImageProxy implements IImage {
    private RealImage realImage;
    private final String filename;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class VirtualProxy {
      public static void main(String[] args) {
         IImage image = new ImageProxy("example.jpg");
         // The image is not loaded yet
         System.out.println("Image proxy created, image not loaded yet.");
         
         // Now display the image, which will trigger loading
         image.display();
         
         // Display again, this time it should be faster as it's already loaded
         image.display();
      }
}
