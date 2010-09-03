package org.zkoss.mylabel;

import java.util.Date;

import org.zkoss.events.MyEvent;
import org.zkoss.zk.au.out.AuAlert;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;


public class MyLabel extends Div {

	/**
	 *
	 */
	private static final long serialVersionUID = -1904887879605953011L;
	private String _value = "";

	static {
												// here is **KEY POINT** , default state is NON_DEFERRABLE,
												// that means your event WILL NOT trigger immediately ,
												// it will trigger until it find an NON_DEFERRABLE event.
		addClientEvent(MyLabel.class,MyEvent.NAME ,  CE_IMPORTANT | CE_NON_DEFERRABLE);
	}


	public MyLabel(){

	}
	public MyLabel(String value){
		this._value = value;
	}

	public String getValue() {
		return _value;
	}
	public void setValue(String value) {
		if(!_value.equals(value)){
			this._value = value;
			smartUpdate("value",_value);
		}
	}

	@Override
	protected boolean isChildable() {
		return false;
	}

	/**
	 *	render properties (send the data to client in creating widget. )
	 */
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
			throws java.io.IOException {
		super.renderProperties(renderer);

		//call the javascript defined setter method in widget class.
		// @see web.js.mylabel.MyLabel.js#$define
		render(renderer, "value", _value);
	}

	@Override
	public String getZclass() {
		return _zclass == null ? "z-mylabel" : _zclass;
	}

	public void onMyLabelPress(MyEvent event){
		response(new AuAlert("you click the label  \nwhen "+event.getClientDateString()+" \n \n server response \n when "+ new Date()));
	}

	/**
	 * Processes an AU request.
	 *
	 */
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(MyEvent.NAME)) {
			MyEvent evt = MyEvent.getKeyEvent(request);

			Component component = evt.getTarget();
			Events.sendEvent( component, evt );
		} else{
			super.service(request, everError);
		}
	}
}
