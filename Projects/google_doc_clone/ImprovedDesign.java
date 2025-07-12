import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Interface for document elements
interface DocumentElement {
    String render();
}

// Concrete implementation for text elements
class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

// Concrete implementation for image elements
class ImageElement implements DocumentElement {
    private String imagePath;

    public ImageElement(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String render() {
        return "[Image: " + imagePath + "]";
    }
}

// Document class responsible for holding a collection of elements
class Document {
    private List<DocumentElement> documentElements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        documentElements.add(element);
    }

    public String render() {
        StringBuilder result = new StringBuilder();
        for (DocumentElement element : documentElements) {
            result.append(element.render());
            result.append("\n");
        }
        return result.toString();
    }
}

// Interface for persistence
interface Persistence {
    void save(String data);
}

// Concrete implementation that saves to file
class FileStorage implements Persistence {
    @Override
    public void save(String data) {
        try {
            FileWriter outputFile = new FileWriter("document.txt");
            outputFile.write(data);
            outputFile.close();
            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file");
        }
    }
}

// Editor class to operate on document and storage
class DocumentEditor {
    private Document document;
    private Persistence storage;

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        document.addElement(new ImageElement(imagePath)); 
    }

    public String renderDocument() {
        return document.render();
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}

// Main client
public class ImprovedDesign {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();
        DocumentEditor editor = new DocumentEditor(document, persistence);

        // Simulate user input
        editor.addText("Hello, world!");
        editor.addText("This is a real-world document editor example.");
        editor.addImage("picture.jpg");

        // Display and save the final document
        System.out.println(editor.renderDocument());
        editor.saveDocument();
    }
}
