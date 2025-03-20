import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Visualization extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double CAMERA_DISTANCE = 1000;
    
    private List<Item> items;
    private Truck truck;
    
    public Visualization(List<Item> items, Truck truck) {
        this.items = items;
        this.truck = truck;
    }
    
    @Override
    public void start(Stage primaryStage) {
        // 3D sahne oluştur
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
        
        // Kamera ayarları
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-CAMERA_DISTANCE);
        camera.setNearClip(0.1);
        camera.setFarClip(5000.0);
        scene.setCamera(camera);
        
        // Kamyon görselleştirmesi
        Box truckBox = new Box(truck.getWidth(), truck.getHeight(), truck.getLength());
        truckBox.setTranslateX(0);
        truckBox.setTranslateY(0);
        truckBox.setTranslateZ(0);
        truckBox.setFill(Color.TRANSPARENT);
        truckBox.setStroke(Color.BLACK);
        truckBox.setStrokeWidth(2);
        root.getChildren().add(truckBox);
        
        // Eşyaların görselleştirilmesi
        for (Item item : items) {
            Box itemBox = new Box(item.getWidth(), item.getHeight(), item.getLength());
            itemBox.setTranslateX(item.getPosition().getX());
            itemBox.setTranslateY(item.getPosition().getY());
            itemBox.setTranslateZ(item.getPosition().getZ());
            itemBox.setFill(Color.rgb(255, 0, 0, 0.5));
            itemBox.setStroke(Color.RED);
            itemBox.setStrokeWidth(1);
            root.getChildren().add(itemBox);
        }
        
        // Sahne rotasyonu için mouse kontrolü
        scene.setOnMousePressed(event -> {
            scene.setOnMouseDragged(e -> {
                root.setRotate(root.getRotate() + (e.getSceneX() - event.getSceneX()) * 0.5);
            });
        });
        
        primaryStage.setTitle("Truck Loading Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} 