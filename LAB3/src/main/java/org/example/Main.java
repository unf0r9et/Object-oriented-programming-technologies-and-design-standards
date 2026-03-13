package org.example;

import canvas.ShapeCanvas;

// Импорт всех компонентов (Фабрики, Рендереры, Редакторы, IO, Фигуры)
import factory.*;
import render.*;
import editor.*;
import io.*;
import shape.*;
import shape.Rectangle;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphic Editor v3 (Serialization & UI)");
        // Окно будет открываться на весь экран, но минимальный размер 800x600
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapeCanvas canvas = new ShapeCanvas();
        JsonFileHandler jsonHandler = new JsonFileHandler();

        // =========================================================
        // РЕГИСТРАЦИЯ ВСЕХ КОМПОНЕНТОВ (БЕЗ IF/SWITCH)
        // =========================================================

        // 1. Rectangle
        canvas.registerShapeType(Rectangle.class, new RectangleRenderer(), new RectangleEditor());
        jsonHandler.register(Rectangle.class, "Rectangle", new RectangleSerializer(), new RectangleDeserializer());

        // 2. Square
        canvas.registerShapeType(Square.class, new SquareRenderer(), new SquareEditor());
        jsonHandler.register(Square.class, "Square", new SquareSerializer(), new SquareDeserializer());

        // 3. Ellipse
        canvas.registerShapeType(Ellipse.class, new EllipseRenderer(), new EllipseEditor());
        jsonHandler.register(Ellipse.class, "Ellipse", new EllipseSerializer(), new EllipseDeserializer());

        // 4. Circle
        canvas.registerShapeType(Circle.class, new CircleRenderer(), new CircleEditor());
        jsonHandler.register(Circle.class, "Circle", new CircleSerializer(), new CircleDeserializer());

        // 5. Line
        canvas.registerShapeType(Line.class, new LineRenderer(), new LineEditor());
        jsonHandler.register(Line.class, "Line", new LineSerializer(), new LineDeserializer());

        // 6. Point (указываем полный путь shape.Point, чтобы не путать с java.awt.Point)
        canvas.registerShapeType(shape.Point.class, new PointRenderer(), new PointEditor());
        jsonHandler.register(shape.Point.class, "Point", new PointSerializer(), new PointDeserializer());

        // 7. Triangle
        canvas.registerShapeType(Triangle.class, new TriangleRenderer(), new TriangleEditor());
        jsonHandler.register(Triangle.class, "Triangle", new TriangleSerializer(), new TriangleDeserializer());

        // =========================================================
        // НАСТРОЙКА ИНТЕРФЕЙСА (UI)
        // =========================================================

        // Левая панель инструментов
        JPanel leftToolbar = new JPanel(new GridLayout(15, 1, 5, 5));
        leftToolbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Правая панель для редактирования свойств
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel propertiesPanel = new JPanel();
        rightPanel.add(new JLabel("Properties", SwingConstants.CENTER), BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(propertiesPanel), BorderLayout.CENTER); // Добавили ScrollPane для удобства
        rightPanel.setPreferredSize(new Dimension(250, 0));

        // Обновление правой панели при клике на фигуру
        canvas.setOnShapeSelected(panel -> {
            propertiesPanel.removeAll();
            propertiesPanel.add(panel);
            propertiesPanel.revalidate();
            propertiesPanel.repaint();
        });

        // =========================================================
        // КНОПКИ РИСОВАНИЯ (Tools)
        // =========================================================

        leftToolbar.add(new JLabel("Tools:", SwingConstants.CENTER));

        JButton rectBtn = new JButton("Rectangle");
        rectBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new RectangleFactory())));

        JButton squareBtn = new JButton("Square");
        squareBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new SquareFactory())));

        JButton ellipseBtn = new JButton("Ellipse");
        ellipseBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new EllipseFactory())));

        JButton circleBtn = new JButton("Circle");
        circleBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new CircleFactory())));

        JButton lineBtn = new JButton("Line");
        lineBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new LineFactory())));

        JButton pointBtn = new JButton("Point");
        pointBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new PointFactory())));

        JButton triangleBtn = new JButton("Triangle");
        triangleBtn.addActionListener(e -> canvas.setMouseMode(createDrawingAdapter(canvas, new TriangleFactory())));

        leftToolbar.add(rectBtn);
        leftToolbar.add(squareBtn);
        leftToolbar.add(ellipseBtn);
        leftToolbar.add(circleBtn);
        leftToolbar.add(lineBtn);
        leftToolbar.add(pointBtn);
        leftToolbar.add(triangleBtn);

        // =========================================================
        // КНОПКИ УПРАВЛЕНИЯ (Actions)
        // =========================================================

        leftToolbar.add(new JLabel("Actions:", SwingConstants.CENTER));

        // Кнопка Выбора фигуры (Select)
        JButton selectBtn = new JButton("SELECT TOOL");
        selectBtn.setBackground(Color.YELLOW);
        selectBtn.addActionListener(e -> canvas.setMouseMode(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { canvas.selectShapeAt(e.getX(), e.getY()); }
        }));
        leftToolbar.add(selectBtn);

        // Кнопка Удаления
        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> canvas.removeSelectedShape());
        leftToolbar.add(deleteBtn);

        // Кнопка Очистки экрана
        JButton clearBtn = new JButton("Clear Canvas");
        clearBtn.addActionListener(e -> {
            canvas.getShapes().clear();
            canvas.selectShapeAt(-1, -1); // Сбросить выделение
            canvas.repaint();
        });
        leftToolbar.add(clearBtn);

        // Кнопки Сохранения / Загрузки
        leftToolbar.add(new JLabel("File:", SwingConstants.CENTER));

        JButton saveBtn = new JButton("Save JSON");
        saveBtn.addActionListener(e -> {
            try {
                jsonHandler.save(new File("shapes.json"), canvas.getShapes());
                JOptionPane.showMessageDialog(frame, "Saved successfully to shapes.json");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving file!");
            }
        });

        JButton loadBtn = new JButton("Load JSON");
        loadBtn.addActionListener(e -> {
            try {
                canvas.setShapes(jsonHandler.load(new File("shapes.json")));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading file!");
            }
        });

        leftToolbar.add(saveBtn);
        leftToolbar.add(loadBtn);

        // Собираем всё в главное окно
        frame.add(new JScrollPane(leftToolbar), BorderLayout.WEST); // Добавили скролл для левого меню
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        // По умолчанию включаем прямоугольник
        canvas.setMouseMode(createDrawingAdapter(canvas, new RectangleFactory()));

        frame.setVisible(true);
    }

    /**
     * Универсальный метод для создания "рисовалки" с предпросмотром.
     * Возвращает адаптер мыши, который понимает, как рисовать конкретную фигуру.
     */
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
                // Создаем временную фигуру для предпросмотра
                Shape preview = factory.createShape(startX, startY, e.getX(), e.getY(), Color.BLUE);
                canvas.setCurrentShape(preview);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Сохраняем фигуру в основной список
                canvas.commitCurrentShape();
            }
        };
    }
}