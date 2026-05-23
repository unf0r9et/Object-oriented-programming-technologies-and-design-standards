package org.example;

import canvas.ShapeCanvas;
import editor.*;
import factory.*;
import io.*;
import plugin.PluginLoader;
import plugin.PluginRegistry;
import plugin.ShapePlugin;
import render.*;
import shape.*;
import shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;

/**
 * Lab 4 entry point: graphic editor with dynamic shape plugins and optional signature verification.
 */
public class Main {

  private static final Path DEFAULT_PLUGINS_DIR =
      Path.of(System.getProperty("user.dir"), "plugins-runtime");

  /**
   * Starts the editor, loads built-in shapes, then discovers plugins from folder or CLI paths.
   *
   * @param args optional: {@code --plugins-dir=<path>}, {@code --plugin=<jar>}, {@code --strict-signatures}
   */
  public static void main(String[] args) {
    boolean strictSignatures = false;
    Path pluginsDir = DEFAULT_PLUGINS_DIR;
    java.util.List<String> extraJars = new java.util.ArrayList<>();

    for (String arg : args) {
      if ("--strict-signatures".equals(arg)) {
        strictSignatures = true;
      } else if (arg.startsWith("--plugins-dir=")) {
        pluginsDir = Path.of(arg.substring("--plugins-dir=".length()));
      } else if (arg.startsWith("--plugin=")) {
        extraJars.add(arg.substring("--plugin=".length()));
      } else if (arg.endsWith(".jar")) {
        extraJars.add(arg);
      }
    }

    JFrame frame = new JFrame("Graphic Editor v4 (Shape Plugins)");
    frame.setMinimumSize(new Dimension(800, 600));
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ShapeCanvas canvas = new ShapeCanvas();
    JsonFileHandler jsonHandler = new JsonFileHandler();
    PluginRegistry pluginRegistry = new PluginRegistry();

    registerBuiltInShapes(canvas, jsonHandler);

    final Path pluginDirectory = pluginsDir;
    final PluginLoader pluginLoader = new PluginLoader(strictSignatures);
    try {
      for (ShapePlugin plugin : pluginLoader.loadFromDirectory(pluginDirectory)) {
        pluginRegistry.register(plugin);
      }
      for (ShapePlugin plugin : pluginLoader.loadFromPaths(extraJars.toArray(String[]::new))) {
        pluginRegistry.register(plugin);
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(
          frame,
          "Plugin load error: " + ex.getMessage(),
          "Plugins",
          JOptionPane.WARNING_MESSAGE);
      ex.printStackTrace();
    }

    JPanel leftToolbar = new JPanel(new GridLayout(0, 1, 5, 5));
    leftToolbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel propertiesPanel = new JPanel();
    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.add(new JLabel("Properties", SwingConstants.CENTER), BorderLayout.NORTH);
    rightPanel.add(new JScrollPane(propertiesPanel), BorderLayout.CENTER);
    rightPanel.setPreferredSize(new Dimension(250, 0));

    canvas.setOnShapeSelected(
        panel -> {
          propertiesPanel.removeAll();
          propertiesPanel.add(panel);
          propertiesPanel.revalidate();
          propertiesPanel.repaint();
        });

    leftToolbar.add(new JLabel("Tools:", SwingConstants.CENTER));
    addBuiltInToolButtons(leftToolbar, canvas);

    leftToolbar.add(new JLabel("Plugins:", SwingConstants.CENTER));
    pluginRegistry.applyAll(canvas, jsonHandler, leftToolbar, factory -> canvas.setMouseMode(
        createDrawingAdapter(canvas, factory)));

    leftToolbar.add(new JLabel("Actions:", SwingConstants.CENTER));

    JButton selectBtn = new JButton("SELECT TOOL");
    selectBtn.setBackground(Color.YELLOW);
    selectBtn.addActionListener(
        e ->
            canvas.setMouseMode(
                new MouseAdapter() {
                  @Override
                  public void mousePressed(MouseEvent ev) {
                    canvas.selectShapeAt(ev.getX(), ev.getY());
                  }
                }));
    leftToolbar.add(selectBtn);

    JButton deleteBtn = new JButton("Delete Selected");
    deleteBtn.addActionListener(e -> canvas.removeSelectedShape());
    leftToolbar.add(deleteBtn);

    JButton clearBtn = new JButton("Clear Canvas");
    clearBtn.addActionListener(
        e -> {
          canvas.getShapes().clear();
          canvas.selectShapeAt(-1, -1);
          canvas.repaint();
        });
    leftToolbar.add(clearBtn);

    leftToolbar.add(new JLabel("File:", SwingConstants.CENTER));

    JButton saveBtn = new JButton("Save JSON");
    saveBtn.addActionListener(
        e -> {
          try {
            jsonHandler.save(new File("shapes.json"), canvas.getShapes());
            JOptionPane.showMessageDialog(frame, "Saved to shapes.json");
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving file!");
          }
        });

    JButton loadBtn = new JButton("Load JSON");
    loadBtn.addActionListener(
        e -> {
          try {
            canvas.setShapes(jsonHandler.load(new File("shapes.json")));
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading file!");
          }
        });

    JButton reloadPluginsBtn = new JButton("Reload Plugins");
    reloadPluginsBtn.addActionListener(
        e -> {
          try {
            PluginRegistry fresh = new PluginRegistry();
            for (ShapePlugin p : pluginLoader.loadFromDirectory(pluginDirectory)) {
              fresh.register(p);
            }
            JOptionPane.showMessageDialog(
                frame, "Reloaded " + fresh.getShapePlugins().size() + " plugin(s). Restart to apply UI.");
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Reload failed: " + ex.getMessage());
          }
        });
    leftToolbar.add(saveBtn);
    leftToolbar.add(loadBtn);
    leftToolbar.add(reloadPluginsBtn);

    JLabel statusLabel = new JLabel("Plugins: " + pluginRegistry.getShapePlugins().size());
    leftToolbar.add(statusLabel);

    frame.add(new JScrollPane(leftToolbar), BorderLayout.WEST);
    frame.add(canvas, BorderLayout.CENTER);
    frame.add(rightPanel, BorderLayout.EAST);

    canvas.setMouseMode(createDrawingAdapter(canvas, new RectangleFactory()));
    frame.setVisible(true);
  }

  /** Registers all built-in shapes from labs 1–3. */
  private static void registerBuiltInShapes(ShapeCanvas canvas, JsonFileHandler jsonHandler) {
    canvas.registerShapeType(Rectangle.class, new RectangleRenderer(), new RectangleEditor());
    jsonHandler.register(
        Rectangle.class, "Rectangle", new RectangleSerializer(), new RectangleDeserializer());

    canvas.registerShapeType(Square.class, new SquareRenderer(), new SquareEditor());
    jsonHandler.register(Square.class, "Square", new SquareSerializer(), new SquareDeserializer());

    canvas.registerShapeType(Ellipse.class, new EllipseRenderer(), new EllipseEditor());
    jsonHandler.register(
        Ellipse.class, "Ellipse", new EllipseSerializer(), new EllipseDeserializer());

    canvas.registerShapeType(Circle.class, new CircleRenderer(), new CircleEditor());
    jsonHandler.register(Circle.class, "Circle", new CircleSerializer(), new CircleDeserializer());

    canvas.registerShapeType(Line.class, new LineRenderer(), new LineEditor());
    jsonHandler.register(Line.class, "Line", new LineSerializer(), new LineDeserializer());

    canvas.registerShapeType(shape.Point.class, new PointRenderer(), new PointEditor());
    jsonHandler.register(
        shape.Point.class, "Point", new PointSerializer(), new PointDeserializer());

    canvas.registerShapeType(Triangle.class, new TriangleRenderer(), new TriangleEditor());
    jsonHandler.register(
        Triangle.class, "Triangle", new TriangleSerializer(), new TriangleDeserializer());
  }

  /** Adds toolbar buttons for built-in drawing tools. */
  private static void addBuiltInToolButtons(JPanel toolbar, ShapeCanvas canvas) {
    toolbar.add(toolButton("Rectangle", canvas, new RectangleFactory()));
    toolbar.add(toolButton("Square", canvas, new SquareFactory()));
    toolbar.add(toolButton("Ellipse", canvas, new EllipseFactory()));
    toolbar.add(toolButton("Circle", canvas, new CircleFactory()));
    toolbar.add(toolButton("Line", canvas, new LineFactory()));
    toolbar.add(toolButton("Point", canvas, new PointFactory()));
    toolbar.add(toolButton("Triangle", canvas, new TriangleFactory()));
  }

  private static JButton toolButton(String label, ShapeCanvas canvas, ShapeFactory factory) {
    JButton btn = new JButton(label);
    btn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, factory)));
    return btn;
  }

  private static MouseAdapter createDrawingAdapter(ShapeCanvas canvas, ShapeFactory factory) {
    return new MouseAdapter() {
      int startX, startY;

      @Override
      public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        shape.Shape preview = factory.createShape(startX, startY, e.getX(), e.getY(), Color.BLUE);
        canvas.setCurrentShape(preview);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        canvas.commitCurrentShape();
      }
    };
  }
}
