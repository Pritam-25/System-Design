
import java.util.ArrayList;
import java.util.List;

// Base interface for files and folders
interface FileSystem{
    void ls(int indent);
    void openAll(int indent);
    int getSize();
    String getName();
    FileSystem cd(String name);
    boolean inFolder();
}

// Leaf: File
class File implements FileSystem{
    private final String name;
    private final int size;

    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public void ls(int indent) {
        String indentSpace = " ".repeat(indent);
        System.out.println(indentSpace+name);
    }

    @Override
    public FileSystem cd(String name) {
        return null;  // Files do not have children, so this method returns null
    }

    @Override
    public void openAll(int indent) {
          String indentSpace = " ".repeat(indent);
        System.out.println(indentSpace+name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }
    

    @Override
    public boolean inFolder() {
        return false;
    }    
}

// Composite: Folder
class Folder implements FileSystem{
    private final String name;
    private final List<FileSystem> childrenList;

    public Folder(String name){
        this.name = name;
        this.childrenList = new ArrayList<>();
    }

    public void add(FileSystem item){
        childrenList.add(item);
    }

    @Override
    public void ls(int indent) {
        String indentSpace = " ".repeat(indent);
        for(FileSystem child: childrenList){
            if(child.inFolder()){
                System.out.println(indentSpace + "+ " + child.getName());
            } else {
                System.out.println(indentSpace + child.getName());
            }
        }
    }

    @Override
    public void openAll(int indent) {
        String indentSpace = " ".repeat(indent);
        System.out.println(indentSpace + "+ " + name);
        for(FileSystem child: childrenList){
            child.openAll(indent + 4);
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for(FileSystem child: childrenList){
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public FileSystem cd(String name) {
        for(FileSystem child: childrenList){
            if(child.getName().equals(name)){
                return child;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean inFolder() {
        return true;
    }
}



public class CompositeDesignPattern {
    public static void main(String[] args) {
        // Build file system
        Folder root = new Folder("root");
        root.add(new File("file1.txt", 100));
        root.add(new File("file2.txt", 200));
        Folder subFolder1 = new Folder("subFolder1");
        subFolder1.add(new File("file3.txt", 300));
        subFolder1.add(new File("file4.txt", 400));
        subFolder1.add(new File("file5.txt", 500));
        root.add(subFolder1);

        Folder subFolder2 = new Folder("subFolder2");
        subFolder2.add(new File("file7.txt", 700));
        subFolder2.add(new File("file8.txt", 800));
        root.add(subFolder2);

        Folder subSubFolder = new Folder("subSubFolder");
        subSubFolder.add(new File("file6.txt", 600));
        subFolder1.add(subSubFolder);
        root.add(new File("file9.txt", 900));


        // root.ls(0);
        root.openAll(0);

        // subFolder1.ls(0);
        // subFolder1.openAll(0);
    }
}
