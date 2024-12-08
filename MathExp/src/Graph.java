import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Graph extends JFrame {
    private ArrayList<ArrayList<Double>> dataPoints;
    private double zoomFactor = 20.0;

    public Graph(ArrayList<ArrayList<Double>> points) {
        dataPoints = new ArrayList<>(points);
        initializeUI();
    }

    private void initializeUI() {
//        setUndecorated(true); // Remove window borders
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Graph");

        PlotCanvas canvas = new PlotCanvas();
        canvas.setBackground(Color.BLACK);
        add(canvas);

        canvas.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                zoomFactor *= 1.1; // Zoom in
            } else {
                zoomFactor /= 1.1; // Zoom out
            }
            canvas.repaint();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose(); // Exit fullscreen on ESC
                }
            }
        });

        setVisible(true);
    }

    private class PlotCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderPlot((Graphics2D) g);
        }

        private void renderPlot(Graphics2D g2d) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.WHITE);
            g2d.drawLine(0, centerY, getWidth(), centerY); // X-axis
            g2d.drawLine(centerX, 0, centerX, getHeight()); // Y-axis

            g2d.setColor(Color.RED);
            for (int i = 0; i < dataPoints.size(); i++) {
                ArrayList<Double> point = dataPoints.get(i);

                int x = centerX + (int) (point.get(0) * zoomFactor);
                int y = centerY - (int) (point.get(1) * zoomFactor);

                g2d.fillOval(x - 3, y - 3, 6, 6); // Draw point

                if (i > 0) {
                    ArrayList<Double> prev = dataPoints.get(i - 1);
                    int prevX = centerX + (int) (prev.get(0) * zoomFactor);
                    int prevY = centerY - (int) (prev.get(1) * zoomFactor);
                    g2d.draw(new Line2D.Double(prevX, prevY, x, y)); // Draw connecting line
                }
            }
        }
    }

}
