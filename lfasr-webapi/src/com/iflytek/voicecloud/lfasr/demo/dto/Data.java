package com.iflytek.voicecloud.lfasr.demo.dto;

public class Data {

	// {
	// "bg": 250,//��ǰ��仰��˵����ʼʱ�䣬��λΪ����
	// "ed": 2890,//��ǰ��仰��˵������ʱ�䣬��λΪ����
	// "onebest":// "�ޣ��㣬�����õ�����ߵ�������",
	// "speaker": "1"//˵���˱�ţ����֡�1���͡�2��Ϊ��ͬ˵���ˣ��绰ר�ð湦�ܣ�
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