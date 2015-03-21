import model.WSModel;
import view.MainForm;
import controller.Controller;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PLayerClient {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        MainForm mainForm = new MainForm(new Controller(new WSModel()));
    }
}