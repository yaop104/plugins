package com.sme.service.detailState.context;

import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.service.detailState.imp.FormalState;
import com.sme.service.detailState.imp.NoPassState;
import com.sme.service.detailState.imp.OfflineState;
import com.sme.service.detailState.imp.SourceState;
import com.sme.service.detailState.imp.StopState;
import com.sme.service.detailState.imp.TestState;
import com.sme.util.JSONObject;



public class StateContext {
	
	
	private static final Istate SOURCE = new SourceState();
	
	private static final Istate NOPASS = new NoPassState();
	
	private static final Istate FORMAL = new FormalState();
	
	private static final Istate STOP = new StopState();
	
	private static final Istate OFFLINE = new OfflineState();
	
	private static final Istate TEST = new TestState();
	
	private Istate now;
	
	private StateContext()
	{
		
	}
	public static StateContext build(String stateS)
	{
		
		Integer state = Integer.parseInt(stateS);
		
		StateContext result = new StateContext();
		switch(state) {
			case 1:
				result.now = SOURCE;
				break;
			case 2:
				result.now = NOPASS;
				break;
			case 3:
				result.now = FORMAL;
				break;
			case 4:
				result.now = STOP;
				break;
			case 5:
				result.now = OFFLINE;
				break;
			case 6:
				result.now = TEST;
				break;
			
			default:
				return null;
		}
		return result;
	}
	
	public JSONObject testPublish(PAppDetail app) {
		
		return this.now.testPublish(app);
		
	}
	
	public JSONObject formalPublis(PAppDetail app) {
		// TODO Auto-generated method stub
		return this.now.formalPublis(app);
	}
	
	public JSONObject noPass(PAppDetail app) {
		// TODO Auto-generated method stub
		return this.now.noPass(app);
	}
	
	public JSONObject stop(PAppDetail app) {
		// TODO Auto-generated method stub
		return this.now.stop(app);
	}
	

	public JSONObject start(PAppDetail app) {
		// TODO Auto-generated method stub
		return this.now.start(app);
	}
	
	public JSONObject del(PAppDetail app) {
		// TODO Auto-generated method stub
		return this.now.del(app);
	}
	

}
