import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NatalityPanel extends JPanel implements ActionListener {
    // buttons and comboBox
    private JButton show, clear;
    private JComboBox select;

    // an array which saves both pie and column
    private Chart[] dCharts = new Chart[2]; // 0 -> Pie || 1 -> column
    // a pointer which defines what to show on display, default is nothing
    private Display display = Display.DISPLAY_NONE;

    // an enum which points on which chart to display current
    protected enum Display {
        DISPLAY_NONE,
        DISPLAY_COLUMN,
        DISPLAY_PIE,
    }

    // constructor which sets up the button on screen and adds listeners
    public NatalityPanel()
    {
        this.show = new JButton("Show chart");
        this.clear = new JButton("Clear");
        String [] menu = {"By Gender", "By Months"};
        this.select = new JComboBox<>(menu);

        // adding listeners
        show.addActionListener(this);
        clear.addActionListener(this);
        select.addActionListener(this);

        // adding the actual buttons on the screen
        add(show);
        add(clear);
        add(select);

        // loads the data information from the excel
        try
        {
            loadData();
        } catch (IOException e)
        {
            System.out.println("Something went wrong while loading file");
        }
    }

    // a function which loads the data from the file for both graphs
    protected void loadData() throws IOException
    {
        // counters for males and females burn
        int maleCount = 0, femaleCount = 0;

        // setting up data for birth per month
        ArrayList<Double> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        data.add(0.0);
        labels.add("February");
        data.add(0.0);
        labels.add("March");
        data.add(0.0);
        labels.add("April");
        data.add(0.0);
        labels.add("May");
        data.add(0.0);
        labels.add("June");
        data.add(0.0);
        labels.add("July");
        data.add(0.0);
        labels.add("August");
        data.add(0.0);
        labels.add("September");
        data.add(0.0);
        labels.add("October");
        data.add(0.0);
        labels.add("November");
        data.add(0.0);
        labels.add("December");
        data.add(0.0);

        // accessing the file
        File f = new File("NatalityMini.csv");
        FileReader fi = new FileReader(f);
        BufferedReader reader = new BufferedReader(fi);
        String line;
        while((line = reader.readLine()) != null)
        {
            // adding to counter base of each birth registry is male or female
            String[] temp = line.split(",");
            if(temp[2].equals("F"))
                femaleCount++;
            else if(temp[2].equals("M"))
                maleCount++;

            // adding up to monthly birth counter
            int month = Integer.parseInt(temp[1]);
            data.set(month-1, data.get(month-1) +1);
        }

        // setting up data and labels for pie
        ArrayList<Double> pData = new ArrayList<>();

        pData.add((double)femaleCount);
        pData.add((double)maleCount);

        ArrayList<String> pLabels = new ArrayList<>();
        pLabels.add("Females");
        pLabels.add("Males");
        // sets up both the Pie and the Colums with the information loaded above
        this.dCharts[0] = new Pie(pData, pLabels);
        this.dCharts[1] = new Column(data, labels);
    }

    // paintcomponent, draws or removes the chart if nothing pressed
    protected void paintComponent(Graphics g) {
        // clears the panel
        super.paintComponent(g);

        // if we pressed the pie, we draw it
        if(this.display == Display.DISPLAY_PIE)
        {
            int drawSize = Math.min(getHeight(), getWidth());
            drawSize += -100;
            this.dCharts[0].draw(g, ((getWidth()-drawSize)/2), ((getHeight()-drawSize)/2), drawSize, drawSize);
        }

        // if we pressed the column we draw it
        if(this.display == Display.DISPLAY_COLUMN){
            this.dCharts[1].draw(g, 0, 0,getWidth(),getHeight());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == show && this.select.getSelectedItem() == "By Gender")
            this.display = Display.DISPLAY_PIE;

        if(e.getSource() == show && this.select.getSelectedItem() == "By Months")
            this.display = Display.DISPLAY_COLUMN;

        if(e.getSource() == clear)
            this.display = Display.DISPLAY_NONE;

        repaint();

    }
}
