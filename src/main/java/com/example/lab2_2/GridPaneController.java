package com.example.lab2_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class GridPaneController implements Initializable {
    @FXML private TextField pathId;
    @FXML private TextField endId;
    @FXML private ProgressBar barId;
    @FXML private ListView<String> listId;
    private Task task;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JavaFXApplication4.items.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change obj) {
                listId.setItems(obj.getList());
            }
        });
    }    
    @FXML
    protected void btnFindClick(ActionEvent event) {
        String path=pathId.getText();
        String end =endId.getText();


        JavaFXApplication4.items.clear();
        //barId.setProgress(-1);
        task = new FindTask(JavaFXApplication4.items,path,end);


        barId.progressProperty().bind(task.progressProperty());
		//run asynk Task in concurrent version
        Thread thread=new Thread(task);
        thread.setDaemon(true);
        thread.start();


//        barId.setProgress(1);
    }
    @FXML
    protected void btnCancelClick(ActionEvent event) {
        System.out.println("Try to Cancel!");
        if (task!=null)  task.cancel();
    }
}
