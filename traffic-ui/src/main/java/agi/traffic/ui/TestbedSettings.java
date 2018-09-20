package agi.traffic.ui;


import java.util.*;

public class TestbedSettings {
    public static final String Hz = "Hz";
    public static final String DrawStats = "Stats";

    private List<TestbedSetting> settings;
    private final Map<String, TestbedSetting> settingsMap;

    public TestbedSettings() {
        settings = new ArrayList<>();
        settingsMap = new HashMap<>();
        populateDefaultSettings();
    }

    private void populateDefaultSettings() {
        addSetting(new TestbedSetting(Hz, 60, 1, 60));
        addSetting(new TestbedSetting(DrawStats, true));
    }

    public void addSetting(TestbedSetting argSetting) {
        settings.add(argSetting);
        settingsMap.put(argSetting.name, argSetting);
    }

    public List<TestbedSetting> getSettings() {
        return Collections.unmodifiableList(settings);
    }

    public TestbedSetting getSetting(String argName) {
        return settingsMap.get(argName);
    }
}
