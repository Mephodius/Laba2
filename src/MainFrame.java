import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
// Главный класс приложения, он же класс фрейма

class Functions{
    final double pi = 3.1416;
    double Function1(double x, double y, double z){
        return (Math.pow(Math.pow(Math.log(1+x),2)+Math.cos(pi*Math.pow(z,3)),Math.sin(y))+Math.pow((Math.exp(Math.pow(x,2))+Math.cos(Math.exp(z)+Math.pow(1/y,0.5))),1/x));
    }
    double Function2(double x, double y, double z){
        return Math.pow(Math.cos(pi*Math.pow(x,3))+2*Math.log(1+y),0.25)*(Math.exp(Math.pow(z,2)+Math.pow(1/x, 0.5)+Math.cos(Math.exp(y))));
    }
}
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    String path = System.getProperty("user.dir");
    private static final int WIDTH = 600;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private BufferedImage functionimage1 = ImageIO.read(new File(path+"\\Resources\\Func1.jpg"));
    private BufferedImage functionimage2 = ImageIO.read(new File(path+"\\Resources\\Func2.jpg"));
    private JLabel fim = new JLabel("Last function chosen");
    private JTextField textFieldX = new JTextField("0");
    private JTextField textFieldY = new JTextField("0");
    private JTextField textFieldZ = new JTextField("0");
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JLabel Result = new JLabel("Here comes the result");
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioFunctions = new ButtonGroup();
    private ButtonGroup radioMemory = new ButtonGroup();
    private JRadioButton func1 = new JRadioButton("Function1", true);
    private JRadioButton func2 = new JRadioButton("Function2");
    private JRadioButton mem1 = new JRadioButton("Memory1", true);
    private JLabel Mem1 = new JLabel("0");
    private JRadioButton mem2 = new JRadioButton("Memory2");
    private JLabel Mem2 = new JLabel("0");
    private JRadioButton mem3 = new JRadioButton("Memory3");
    private JLabel Mem3 = new JLabel("0");
    private JButton MC = new JButton("Clear chosen memory");
    private JButton Mplus = new JButton("Add the result to the chosen memory");
    private JButton Reset = new JButton("Reset all the parameters");
    private JButton GoAhead = new JButton("Go ahead");
    // Контейнер для отображения радио-кнопок
    private Box hzero = Box.createHorizontalBox();
    private Box hfirst = Box.createHorizontalBox();
    private Box hsecond = Box.createHorizontalBox();
    private Box hthird = Box.createHorizontalBox();
    private Box vfinal = Box.createVerticalBox();
    private JPanel panel = new JPanel();
    double result;
    double fmem1=0.0;
    double fmem2=0.0;
    double fmem3=0.0;
    // Вспомогательный метод для добавления кнопок на панель
    /*private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
               // imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }*/
    // Конструктор класса
    public MainFrame() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        //setResizable(false);
       /* addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);*/

       /* radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));*/
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        JLabel labelForY = new JLabel("Y:");
        JLabel labelForZ = new JLabel("Z");
        radioFunctions.add(func1);
        radioFunctions.add(func2);
        func1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fim.setIcon(new ImageIcon(functionimage1));
            }
        });
        func2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fim.setIcon(new ImageIcon(functionimage2));
            }
        });
        textFieldX.setPreferredSize(new Dimension(50,20));
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        textFieldY.setPreferredSize(new Dimension(50,20));
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        textFieldZ.setPreferredSize(new Dimension(50,20));
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        radioMemory.add(mem1);
        radioMemory.add(mem2);
        radioMemory.add(mem3);
        hzero.add(Box.createHorizontalGlue());
        hzero.add(fim);
        hzero.add(Box.createHorizontalGlue());
       // hfirst.add(Box.createHorizontalGlue());
        hfirst.add(labelForX);
     //   hfirst.add(Box.createHorizontalGlue());
        hfirst.add(textFieldX);
        hfirst.add(Box.createHorizontalStrut(20));
        hfirst.add(mem1);
     //   hfirst.add(Box.createHorizontalGlue());
        hfirst.add(Mem1);
        hfirst.add(Box.createHorizontalGlue());
        hfirst.add(MC);
        hfirst.add(Box.createHorizontalGlue());
        hfirst.add(Mplus);
        hfirst.add(Box.createHorizontalGlue());
      //  hsecond.add(Box.createHorizontalGlue());
        hsecond.add(labelForY);
      //  hsecond.add(Box.createHorizontalGlue());
        hsecond.add(textFieldY);
        hsecond.add(Box.createHorizontalStrut(20));
        hsecond.add(mem2);
    //    hsecond.add(Box.createHorizontalGlue());
        hsecond.add(Mem2);
        hsecond.add(Box.createHorizontalGlue());
        hsecond.add(func1);
      //  hsecond.add(Box.createHorizontalGlue());
        hsecond.add(func2);
        hsecond.add(Box.createHorizontalGlue());
        hsecond.add(Reset);
        hsecond.add(Box.createHorizontalGlue());
    //    hthird.add(Box.createHorizontalGlue());
        hthird.add(labelForZ);
      //  hthird.add(Box.createHorizontalGlue());
        hthird.add(textFieldZ);
        hthird.add(Box.createHorizontalStrut(20));
        hthird.add(mem3);
       // hthird.add(Box.createHorizontalGlue());
        hthird.add(Mem3);
        hthird.add(Box.createHorizontalGlue());
        hthird.add(Result);
        hthird.add(Box.createHorizontalGlue());
        hthird.add(GoAhead);
        hthird.add(Box.createHorizontalGlue());
        fim.setIcon(new ImageIcon(functionimage1));
        vfinal.add(Box.createVerticalGlue());
        vfinal.add(hzero);
        vfinal.add(Box.createVerticalGlue());
        vfinal.add(hfirst);
        vfinal.add(Box.createVerticalGlue());
        vfinal.add(hsecond);
        vfinal.add(Box.createVerticalGlue());
        vfinal.add(hthird);
        vfinal.add(Box.createVerticalGlue());
        add(vfinal);
        /*panel.add(fim);
        panel.add(hfirst);
        panel.add(hsecond);
        panel.add(hthird);
        add(panel);*/
        //pack();
        setVisible(true);
// Создать область для кнопок
        GoAhead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Functions function = new Functions();
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double z = Double.parseDouble(textFieldZ.getText());
                    if(func1.isSelected()){
                        result = function.Function1(x, y, z);
                    }
                    else {
                        result = function.Function2(x, y, z);
                    }
                    Result.setText(String.format("%.3f",result));
                    setVisible(true);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                Result.setText("0");
                setVisible(true);
            }
        });
        MC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mem1.isSelected()){
                    Mem1.setText("0");
                    fmem1=0;
                }
                if(mem2.isSelected()){
                    Mem2.setText("0");
                    fmem2=0;
                }
                if(mem3.isSelected()){
                    Mem3.setText("0");
                    fmem3=0;
                }
                setVisible(true);
            }
        });
        Mplus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mem1.isSelected()){
                    fmem1+=result;
                    Mem1.setText(String.format("%.3f",fmem1));
                }
                if(mem2.isSelected()){
                    fmem2+=result;
                    Mem2.setText(String.format("%.3f",fmem2));
                }
                if(mem3.isSelected()){
                    fmem3+=result;
                    Mem3.setText(String.format("%.3f",fmem3));
                }
                setVisible(true);
            }
        });
// Связать области воедино в компоновке BoxLayout
        /*Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);*/
    }
    // Главный метод класса
    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
