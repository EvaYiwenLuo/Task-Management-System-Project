package com.model;
//任务表对象
public class mission {
    public String getMission_id() {
		return mission_id;
	}
	public void setMission_id(String mission_id) {
		this.mission_id = mission_id;
	}
	public String getMission_content() {
		return mission_content;
	}
	public void setMission_content(String mission_content) {
		this.mission_content = mission_content;
	}
	public String getMission_status() {
		return mission_status;
	}
	public void setMission_status(String mission_status) {
		this.mission_status = mission_status;
	}
	public String getMission_executor() {
		return mission_executor;
	}
	public void setMission_executor(String mission_executor) {
		this.mission_executor = mission_executor;
	}
	private String mission_id = null;
    private String mission_content = null;
    private String mission_status = null;
    private String mission_executor = null;
    
    public mission(String id,String content,String status,String executor)
    {
    	this.mission_id = id;
    	this.mission_content = content;
    	this.mission_status = status;
    	this.mission_executor = executor;
    }
}
