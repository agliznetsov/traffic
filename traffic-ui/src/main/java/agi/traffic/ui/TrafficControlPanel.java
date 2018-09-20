package agi.traffic.ui;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;


public class TrafficControlPanel extends VBox {
    final TrafficModel model;
    final TrafficController controller;

    Button resetButton = new Button("Reset");
    Button runButton = new Button("");
    Button stepButton = new Button("Step");
//    public ComboBox<Traffic> tests;

    public TrafficControlPanel(TrafficModel model, TrafficController controller) {
        this.model = model;
        this.controller = controller;
        addButtons();
        refresh();
    }

    private void addButtons() {
        runButton.setAlignment(Pos.CENTER);
        stepButton.setAlignment(Pos.CENTER);
        resetButton.setAlignment(Pos.CENTER);

        runButton.setOnAction((e) -> {
            controller.toggleRun();
            refresh();
        });

        stepButton.setOnAction((e) -> {
            controller.step();
            refresh();
        });

        resetButton.setOnAction((e) -> {
            controller.reset();
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(resetButton, runButton, stepButton);
        getChildren().add(buttons);
    }

    private void refresh() {
        runButton.setText(model.isRunning() ? "Stop" : "Start");
    }

//    private void updateTests(ComboBoxModel<ListItem> model) {
//        ObservableList<ListItem> list = tests.itemsProperty().get();
//        list.clear();
//        for (int i = 0; i < model.getSize(); i++) {
//            list.add((ListItem) model.getElementAt(i));
//        }
//    }

    public void initComponents() {
        VBox top = new VBox();

//        DefaultComboBoxModel testList = model.getTests();
//        testList.addListDataListener(new ListDataListener() {
//            @Override
//            public void intervalRemoved(ListDataEvent e) {
//                updateTests((ComboBoxModel<ListItem>) e.getSource());
//            }
//
//            @Override
//            public void intervalAdded(ListDataEvent e) {
//                updateTests((ComboBoxModel<ListItem>) e.getSource());
//            }
//
//            @Override
//            public void contentsChanged(ListDataEvent e) {
//                updateTests((ComboBoxModel<ListItem>) e.getSource());
//            }
//        });
//
//        tests = new ComboBox<ListItem>();
//        updateTests((ComboBoxModel<ListItem>) testList);
//        tests.setOnAction((actionEvent) -> {
//            testSelected();
//        });
//        tests.setCellFactory(ComboBoxListCell.<ListItem>forListView(new StringConverter<ListItem>() {
//            @Override
//            public String toString(ListItem listItem) {
//                if (listItem == null) {
//                    return ("");
//                } else if (listItem.isCategory()) {
//                    return (listItem.category);
//                } else {
//                    return (listItem.test.getName());
//                }
//            }
//
//            @Override
//            public ListItem fromString(String string) {
//                return null;
//            }
//        }, tests.getItems()));
//
//        top.getChildren().add(new Label("Choose a test:"));
//        top.getChildren().add(tests);

//        TestbedSettings settings = model.getSettings();
//        addSettings(top, settings);
//
//        setTop(top);

    }

//    protected void testSelected() {
//        int testNum = tests.getSelectionModel().getSelectedIndex();
//        controller.playTest(testNum);
//    }

//    private void addSettings(Pane argPanel, TestbedSettings argSettings) {
//        for (TestbedSetting setting : argSettings.getSettings()) {
//            switch (setting.constraintType) {
//                case RANGE:
//                    Label text = new Label(setting.name + ": " + setting.value);
//                    Slider slider = new Slider(setting.min, setting.max, setting.value);
//                    // slider.setMaximumSize(new Dimension(200, 20));
//                    slider.valueProperty().addListener((prop, oldValue, newValue) -> {
//                        stateChanged(slider);
//                    });
//                    putClientProperty(slider, "name", setting.name);
//                    putClientProperty(slider, SETTING_TAG, setting);
//                    putClientProperty(slider, LABEL_TAG, text);
//                    argPanel.getChildren().add(text);
//                    argPanel.getChildren().add(slider);
//                    break;
//                case BOOLEAN:
//                    CheckBox checkbox = new CheckBox(setting.name);
//                    checkbox.setSelected(setting.enabled);
//                    checkbox.selectedProperty().addListener((prop, oldValue, newValue) -> {
//                        stateChanged(checkbox);
//                    });
//                    putClientProperty(checkbox, SETTING_TAG, setting);
//                    argPanel.getChildren().add(checkbox);
//                    break;
//            }
//        }
//    }

//    private <T> T getClientProperty(Control control, String tag) {
//        Map<String, Object> map = (Map<String, Object>) control.getUserData();
//        return (map != null ? (T) map.get(tag) : null);
//    }
//
//    private void putClientProperty(Control control, String tag, Object o) {
//        Map<String, Object> map = (Map<String, Object>) control.getUserData();
//        if (map == null) {
//            map = new HashMap<>();
//            control.setUserData(map);
//        }
//        map.put(tag, o);
//    }
//
//    private void stateChanged(Control control) {
//        TestbedSetting setting = getClientProperty(control, SETTING_TAG);
//        switch (setting.constraintType) {
//            case BOOLEAN:
//                CheckBox box = (CheckBox) control;
//                setting.enabled = box.isSelected();
//                break;
//            case RANGE:
//                Slider slider = (Slider) control;
//                setting.value = (int) slider.getValue();
//                Label label = getClientProperty(slider, LABEL_TAG);
//                label.setText(setting.name + ": " + setting.value);
//                break;
//        }
//    }
}
