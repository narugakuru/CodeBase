import javax.script.*;
import java.util.Set;

public class demo {


    public static void main(String[] args) {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("Nashorn");

        Bindings bindings = scriptEngine.createBindings();
        Set<String> strings = bindings.keySet();

    }
}
