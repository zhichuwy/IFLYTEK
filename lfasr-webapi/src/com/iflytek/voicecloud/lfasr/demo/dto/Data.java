package com.iflytek.voicecloud.lfasr.demo.dto;

public class Data {

	// {
	// "bg": 250,//当前这句话的说话开始时间，单位为毫秒
	// "ed": 2890,//当前这句话的说话结束时间，单位为毫秒
	// "onebest":// "噢，你，你听得到我这边的声音吗？",
	// "speaker": "1"//说话人编号（数字“1”和“2”为不同说话人，电话专用版功能）
	// }

	private String bg;
	private String ed;
	private String onebest;
	private String speaker;

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getEd() {
		return ed;
	}

	public void setEd(String ed) {
		this.ed = ed;
	}

	public String getOnebest() {
		return onebest;
	}

	public void setOnebest(String onebest) {
		this.onebest = onebest;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
}