import java.awt.*;
import java.util.ArrayList;

public class Pie extends Chart{
    // constructor for pie
    public Pie(ArrayList<Double> data, ArrayList<String> labels) {
        super(data, labels);
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {
        // counting the total of people born
        double total = 0;
        for(int i = 0; i < this.data.size(); i++)
        {
            total += this.data.get(i);
        }
        int step = 0;
        // looping through the diff genders
        for(int i = 0; i < this.data.size(); i++)
        {
            // calculating the avg of the complete circle per type to know the angle to fill
            int angle = (int)Math.round(this.data.get(i)/total*360);
            // selecting a random color
            g.setColor(new Color((int) (255 * Math.random()),(int) (255 * Math.random()), (int) (255 * Math.random())));

            // filling the part of the arc
            g.fillArc(x, y, width, height, step, angle);

            // having a step counter to know on which angle we stopped and to where to draw from
            // on next rotation
            step += angle;

            // drawing a rect and a label based on current color / step

            g.fillRect(x+(i*80), y + width + 20, 10, 10);
            g.drawString(this.labels.get(i), x + i*80 + 15, y + width + 30);
        }

    }
}

