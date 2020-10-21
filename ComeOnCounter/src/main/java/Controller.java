package main.java;

import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    private int record = 0;
    private int last = 0;
    private int current = 0;

    private File file;

    public Label recordLabel;
    public Label lastLabel;
    public Label currentLabel;
    public Button comeOn;
    public Button save;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        file = new File("Counts.txt");

        try
        {
            if(file.exists())
            {
                InputStream in = file.toURI().toURL().openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String rec = br.readLine();
                String l = br.readLine();

                br.close();
                in.close();

                record = Integer.parseInt(rec);
                last = Integer.parseInt(l);
            }
            else
            {
                record = 11;
                last = 9;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        recordLabel.setText("Record: " + record);
        lastLabel.setText("Last class: " + last);
        currentLabel.setText("Current: " + current);
    }

    public void saveToFile()
    {
        try
        {
            PrintWriter out = new PrintWriter(file);

            if(current != 0)
            {
                if(current > record)
                record = current;

                out.println(record);
                out.print(current);
            }
            else
            {
                out.println(record);
                out.print(last);
            }

            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleButtonAction(ActionEvent event)
    {
        if(event.getSource() == comeOn)
        {
            current++;
            currentLabel.setText("Current: " + current);
        }
        else if(event.getSource() == save)
        {
            saveToFile();

            if(current != 0)
            {
                last = current;
            }

            recordLabel.setText("Record: " + record);
            lastLabel.setText("Last class: " + last);
            current = 0;
            currentLabel.setText("Current: " + current);
        }
    }
}
