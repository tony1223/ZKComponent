package org.zkoss.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

public class MyEvent extends Event {

	private String dateString = null;

	public static final MyEvent getKeyEvent(AuRequest request) {
		final Component comp = request.getComponent();
		final Map data = request.getData();

		String date = (String )data.get("date");
		return new MyEvent(request.getCommand(), comp, date);

	}

	public MyEvent(String name, Component target, String key) {
		super(name, target);
		dateString = key;
	}

	public String getClientDateString() {
		return dateString;
	}

	public static final String NAME = "onMyLabelPress";
}
