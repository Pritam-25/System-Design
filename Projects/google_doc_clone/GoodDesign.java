import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// ========== DocumentElement Interface ==========
interface DocumentElement {
    String render();
}

// ========== Concrete TextElement ==========
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

// ========== Concrete ImageElement ==========
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

// ========== Document: Pure Storage ==========
class Document {
    private List<DocumentElement> documentElements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        documentElements.add(element);
    }

    public List<DocumentElement> getElements() {
        return documentElements;
    }
}

// ========== Renderer Interface ==========
interface DocumentRenderer {
    String render(List<DocumentElement> elements);
}

// ========== Plain Text Renderer ==========
class PlainTextRenderer implements DocumentRenderer {
    @Override
    public String render(List<DocumentElement> elements) {
        StringBuilder result = new StringBuilder();
        for (DocumentElement element : elements) {
            result.append(element.render()).append("\n");
        }
        return result.toString();
    }
}

// ========== Persistence Interface ==========
interface Persistence {
    void save(String data);
}

// ========== File-Based Storage ==========
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

// ========== Document Editor ==========
class DocumentEditor {
    private Document document;
    private Persistence storage;
    private DocumentRenderer renderer;

    public DocumentEditor(Document document, Persistence storage, DocumentRenderer renderer) {
        this.document = document;
        this.storage = storage;
        this.renderer = renderer;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        document.addElement(new ImageElement(imagePath));
    }

    public String renderDocument() {
        return renderer.render(document.getElements());
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}

// ========== Client ==========
public class GoodDesign {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();
        DocumentRenderer renderer = new PlainTextRenderer();

        DocumentEditor editor = new DocumentEditor(document, persistence, renderer);

        editor.addText("Hello, world!");
        editor.addText("This is a real-world document editor example.");
        editor.addImage("picture.jpg");

        System.out.println(editor.renderDocument());

        editor.saveDocument();
    }
}
