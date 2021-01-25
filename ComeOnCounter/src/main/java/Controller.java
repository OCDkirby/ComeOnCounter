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
    private int laughRecord = 0;
    private int laughLast = 0;
    private int laughCurrent = 0;

    private File file;

    public Label laughRecordLabel;
    public Label laughLastLabel;
    public Label laughCurrentLabel;
    public Button laugh;

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
                String crec = br.readLine();
                String cl = br.readLine();

                br.close();
                in.close();

                laughRecord = Integer.parseInt(crec);
                laughLast = Integer.parseInt(cl);
            }
            else
            {
                laughRecord = 0;
                laughLast = 0;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        laughRecordLabel.setText("Record: " + laughRecord);
        laughLastLabel.setText("Last class: " + laughLast);
        laughCurrentLabel.setText("Current: " + laughCurrent);
    }

    public void saveToFile()
    {
        try
        {
            PrintWriter out = new PrintWriter(file);

            if(laughCurrent != 0)
            {
                if(laughCurrent > laughRecord)
                    laughRecord = laughCurrent;

                out.println(laughRecord);
                out.println(laughCurrent);
            }
            else
            {
                out.println(laughRecord);
                out.println(laughLast);
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
        if(event.getSource() == laugh)
        {
            laughCurrent++;
            laughCurrentLabel.setText("Current: " + laughCurrent);
        }
        else if(event.getSource() == save)
        {
            saveToFile();

            if(laughCurrent != 0)
            {
                laughLast = laughCurrent;
            }

            laughRecordLabel.setText("Record: " + laughRecord);
            laughLastLabel.setText("Last class: " + laughLast);
            laughCurrent = 0;
            laughCurrentLabel.setText("Current: " + laughCurrent);
        }
    }
}
