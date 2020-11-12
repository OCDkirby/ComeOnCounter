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
    private int comeOnRecord = 0;
    private int comeOnLast = 0;
    private int comeOnCurrent = 0;
    private int gabeRecord = 0;
    private int gabeLast = 0;
    private int gabeCurrent = 0;

    private File file;

    public Label comeOnRecordLabel;
    public Label comeOnLastLabel;
    public Label comeOnCurrentLabel;
    public Button comeOn;

    public Label gabeRecordLabel;
    public Label gabeLastLabel;
    public Label gabeCurrentLabel;
    public Button gabe;

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
                String grec = br.readLine();
                String gl = br.readLine();

                br.close();
                in.close();

                comeOnRecord = Integer.parseInt(crec);
                comeOnLast = Integer.parseInt(cl);
                gabeRecord = Integer.parseInt(grec);
                gabeLast = Integer.parseInt(gl);
            }
            else
            {
                comeOnRecord = 18;
                comeOnLast = 15;
                gabeRecord = 0;
                gabeLast = 0;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        comeOnRecordLabel.setText("Record: " + comeOnRecord);
        comeOnLastLabel.setText("Last class: " + comeOnLast);
        comeOnCurrentLabel.setText("Current: " + comeOnCurrent);
        
        gabeRecordLabel.setText("Record: " + gabeRecord);
        gabeLastLabel.setText("Last class: " + gabeLast);
        gabeCurrentLabel.setText("Current: " + gabeCurrent);
    }

    public void saveToFile()
    {
        try
        {
            PrintWriter out = new PrintWriter(file);

            if(comeOnCurrent != 0)
            {
                if(comeOnCurrent > comeOnRecord)
                    comeOnRecord = comeOnCurrent;

                out.println(comeOnRecord);
                out.println(comeOnCurrent);
            }
            else
            {
                out.println(comeOnRecord);
                out.println(comeOnLast);
            }

            if(gabeCurrent != 0)
            {
                if(gabeCurrent > gabeRecord)
                    gabeRecord = gabeCurrent;

                out.println(gabeRecord);
                out.print(gabeCurrent);
            }
            else
            {
                out.println(gabeRecord);
                out.println(gabeLast);
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
            comeOnCurrent++;
            comeOnCurrentLabel.setText("Current: " + comeOnCurrent);
        }
        else if(event.getSource() == gabe)
        {
            gabeCurrent++;
            gabeCurrentLabel.setText("Current: " + gabeCurrent);
        }
        else if(event.getSource() == save)
        {
            saveToFile();

            if(comeOnCurrent != 0)
            {
                comeOnLast = comeOnCurrent;
            }
            
            if(gabeCurrent != 0)
            {
                gabeLast = gabeCurrent;
            }

            comeOnRecordLabel.setText("Record: " + comeOnRecord);
            comeOnLastLabel.setText("Last class: " + comeOnLast);
            comeOnCurrent = 0;
            comeOnCurrentLabel.setText("Current: " + comeOnCurrent);
            
            gabeRecordLabel.setText("Record: " + gabeRecord);
            gabeLastLabel.setText("Last class: " + gabeLast);
            gabeCurrent = 0;
            gabeCurrentLabel.setText("Current: " + gabeCurrent);
        }
    }
}
