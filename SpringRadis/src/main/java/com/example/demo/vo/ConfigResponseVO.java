package com.example.demo.vo;



import java.util.List;

import com.example.demo.entity.ConfigEntity;

public class ConfigResponseVO {
	
	private List<ConfigEntity> configList;

	public List<ConfigEntity> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ConfigEntity> configList) {
		this.configList = configList;
	}

}
