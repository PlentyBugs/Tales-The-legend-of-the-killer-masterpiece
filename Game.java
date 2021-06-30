import window.menu.MainWindow;

import java.io.IOException;

public class Game {

    public static void main(String[] args) throws InterruptedException, IOException {

        MainWindow mainWindow = new MainWindow();

//        StartWindow startWindow = new StartWindow();
//
//        synchronized (StartWindow.class) {
//            try {
//                StartWindow.class.wait();
//            } catch (InterruptedException ex) {
//                System.out.println("Game Started");
//            }
//        }
//
//        String game = startWindow.getGame();
//        startWindow.close();
//        if(game.equals("new")) {
//
//        } else if(game.equals("load")){
//            FindFile ff = new FindFile();
//
//            ff.findFile("*.txt", new File("./Saves/"));
//
//            LoadGameWindow loadGameWindow = new LoadGameWindow(ff.getFiles());
//
//            String fileName;
//            do {
//                System.out.println();
//                fileName = loadGameWindow.getFileName();
//            } while (fileName == null);
//            loadGameWindow.close();
//
//            try (FileInputStream fin = new FileInputStream("./Saves/" + fileName);
//                 ObjectInputStream ois = new ObjectInputStream(fin)) {
//                FieldWindow fieldWindow = (FieldWindow) ois.readObject();
//                Player player = fieldWindow.getPlayer();
//                fieldWindow.drawMap();
//                player.initWindows();
//                fieldWindow.drawAllPlayerWindow(fieldWindow.getPlayer(), fieldWindow);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }
}
