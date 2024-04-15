package com.example.ElJuegoDeLaVida;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameOfLife extends Application {

    private static final int width = 500;
    private static final int height = 500;
    private static final int cellSize = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, width, height + 100);
        final Canvas canvas = new Canvas(width, height);

        Button reset = new Button("Reset");
        Button step = new Button("Step");
        Button run = new Button("Run");
        Button stop = new Button("Stop");

        root.getChildren().addAll(canvas, new HBox(10, reset, step, run, stop));
        primaryStage.setScene(scene);
        primaryStage.show();

        int rows = (int) Math.floor(height / cellSize);
        int cols = (int) Math.floor(width / cellSize);

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        Life life = new Life(rows, cols, graphics);
        life.init();

        AnimationTimer runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                // only update once every second
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(500)) {
                    life.tick();
                    lastUpdate = now;
                }
            }
        };

        reset.setOnAction(l -> life.init() );
        run.setOnAction(  l -> runAnimation.start());
        step.setOnAction( l -> life.tick());
        stop.setOnAction( l -> runAnimation.stop());
    }



    private static class Life {
        private final int rows;
        private final int cols;
        private int[][] grid;
        private Random random = new Random();
        private final GraphicsContext graphics;

        public Life(int rows, int cols, GraphicsContext graphics) {
            this.rows = rows;
            this.cols = cols;
            this.graphics = graphics;
            grid = new int[rows][cols];
        }

        public void init() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = random.nextInt(2);
                }
            }
            draw();
        }

        private void draw() {
            // clear graphics
            graphics.setFill(Color.LAVENDER);
            graphics.fillRect(0, 0, width, height);

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        // first rect will end up becoming the border
                        graphics.setFill(Color.gray(0.5, 0.5));
                        graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                        graphics.setFill(Color.PURPLE);
                        graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                    }else {
                        graphics.setFill(Color.gray(0.5, 0.5));
                        graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                        graphics.setFill(Color.LAVENDER);
                        graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                    }
                }
            }
        }

        public void tick() {
            int[][] next = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int neighbors = countAliveNeighbors(i, j);

                    if (neighbors == 3) {
                        next[i][j] = 1;
                    }else if (neighbors < 2 || neighbors > 3) {
                        next[i][j] = 0;
                    }else {
                        next[i][j] = grid[i][j];
                    }
                }
            }
            grid = next;
            draw();
        }

        private int countAliveNeighbors(int i, int j) {
            int sum = 0;
            int iStart = i == 0 ? 0 : -1;
            int iEndInclusive = i == grid.length - 1 ? 0 : 1;
            int jStart = j == 0 ? 0 : -1;
            int jEndInclusive = j == grid[0].length - 1 ? 0 : 1;

            for (int k = iStart; k <= iEndInclusive; k++) {
                for (int l = jStart; l <= jEndInclusive; l++) {
                    sum += grid[i + k][l + j];
                }
            }

            sum -= grid[i][j];

            return sum;
        }
    }
}
