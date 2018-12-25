import java.util.ArrayList;
import java.util.List;

public class Controller {
    public List<GameObject> gameObjects = new ArrayList<GameObject>();
    public void addObject(GameObject object){
        gameObjects.add(object);
    }
    public void removeObject(GameObject object){
        gameObjects.remove(object);
    }
}