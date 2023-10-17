import java.awt.*;
        import java.awt.event.KeyEvent;

        import static java.lang.Thread.sleep;

public class KeyTest {
    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        while (true) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(50000);
        }

    }
}
