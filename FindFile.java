import java.io.File;
import java.util.ArrayList;

public class FindFile {

    private ArrayList<File> files = new ArrayList<>();

    public void findFile(String name, File file){
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list){
                if (fil.isDirectory()){
                    findFile(name,fil);
                } else if (name.equalsIgnoreCase(fil.getName())){
                    System.out.println(fil.getParentFile());
                }
                files.add(fil);
            }
    }

    public ArrayList<File> getFiles() {
        return files;
    }
}
