package com.syntese.settings;

public interface ISettings {
	public String getCurrentSetting(String settingName);
	public String getDefaultSetting(String settingName);
	public boolean setCurrentSetting(String settingName, String settingValue);
	public void save();
}
