import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

    public static final int SIZE = 100;
    private int TILE_SIZE = 10;
    Rectangle[][] rectangles = new  Rectangle[SIZE][SIZE];
    private Color currentColor = Color.AQUA;



    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Rectangle rectangle = new Rectangle(TILE_SIZE,TILE_SIZE, Color.WHITE);
                root.add(rectangle,c,r);
                rectangles[r][c] = rectangle;
            }

        }

        Scene scene = new Scene(root, SIZE*TILE_SIZE, SIZE*TILE_SIZE);

        scene.setOnMouseDragged(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                rectangles[y / TILE_SIZE][x / TILE_SIZE].setFill(currentColor);
            }



        });
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.A){
                currentColor = Color.AQUA;
            }
            if(event.getCode() == KeyCode.R){
                currentColor = Color.RED;
            }
            if(event.getCode() == KeyCode.H){
                currentColor = Color.HOTPINK;
            }


        });

        scene.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY){
                int x = (int) event.getX()/TILE_SIZE;
                int y = (int) event.getY()/TILE_SIZE;
                floodFill((Color) rectangles[y][x].getFill(), x,y);



            }
        });






        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void floodFill(Color clicked, int r, int c) {
        if(r < 0 || c < 0 || c >= SIZE || r >= SIZE){
            return;
        }
        if(rectangles[r][c].getFill() == clicked){
            rectangles[r][c].setFill(currentColor);
            floodFill(clicked,r+1,c);
            floodFill(clicked,r-1,c);
            floodFill(clicked,r,c+1);
            floodFill(clicked,r,c-1);
            floodFill(clicked,r+1,c-1);
            floodFill(clicked,r-1,c-1);
            floodFill(clicked,r-1,c+1);

        }

    }
}