import java.awt.*;
import java.util.ArrayList;

public class Column extends Chart{
    // constructor
    public Column(ArrayList<Double> data, ArrayList<String> labels) {
        super(data, labels);
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {
        // a loop which finds highest value
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < this.data.size(); i++)
        {
            maxValue = Math.max(maxValue, this.data.get(i));
        }

        // defines the highest line which should be closest scale of 500 above maxvalue
        final int HIGHEST_LINE = (int)((500-(maxValue%500)) + maxValue);

        // definiting spaces, max draw size based on window and weight for each column
        final int JUMP_STEPS = (this.data.size() * 2) + 1;
        final int DRAW_SIZE = width / JUMP_STEPS;
        final int MAX_DRAW_HEIGHT = (int) (height * 0.7);

        // drawing the lines and the strings for numbers
        for(int i = 1; i <= 5; i++)
        {
            g.setColor(Color.BLACK);
            int lineHeight = (int) ((0.95 * height) - (((HIGHEST_LINE / 5 * i) / maxValue) * MAX_DRAW_HEIGHT));
            g.drawLine(DRAW_SIZE-10, lineHeight,
                    width-DRAW_SIZE+10, lineHeight);
            g.drawString("" + HIGHEST_LINE / 5 * (i), 0, lineHeight);
        }

        // drawing a column after each space
        // arrMove defines the columns printed and left to print
        int arrMove = 0;
        for(int i = 0; i < JUMP_STEPS; i++)
        {
            // checks if we need to do a space or print a column
            if(i%2 == 1)
            {
                int draw = (int)(this.data.get(arrMove) / maxValue * MAX_DRAW_HEIGHT);
                g.setColor(new Color((int) (255 * Math.random()),(int) (255 * Math.random()), (int) (255 * Math.random())));
                // we are setting the max value to be the biggest column and the rest are
                // precentage off it
                g.fillRect(DRAW_SIZE * i, (int)(0.9 * height)-draw, DRAW_SIZE, draw);
                g.drawString(this.labels.get(arrMove), DRAW_SIZE * i, (int)(0.95 * height));

                arrMove++;
            }
        }
    }
}

