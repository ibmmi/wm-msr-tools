package WzP.Util.Advanced;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.passman.PasswordManagerException;
// --- <<IS-END-IMPORTS>> ---

public final class PasswordManagement

{
	// ---( internal utility methods )---

	final static PasswordManagement _instance = new PasswordManagement();

	static PasswordManagement _newInstance() { return new PasswordManagement(); }

	static PasswordManagement _cast(Object o) { return (PasswordManagement)o; }

	// ---( server methods )---




	public static final void getOutboundPassword (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getOutboundPassword)>> ---
		// @sigtype java 3.5
		// [i] field:0:required entryKey
		// [o] field:0:required password
		// pipeline
		boolean bPresent = false;
		String	entryKey = null;
		IDataCursor pipelineCursor = pipeline.getCursor();
		if ( bPresent = pipelineCursor.first("entryKey")){
			entryKey = (String) IDataUtil.get( pipelineCursor, "entryKey" );
		}		
		pipelineCursor.destroy();
		
		if (!bPresent) return; // absent -> absent
		
		// TODO: address key absent case
		String p = null;
		
		if ( null != entryKey){
			try {
				com.wm.util.security.WmSecureString ps = com.wm.app.b2b.server.OutboundPasswordManager.retrievePassword(entryKey);
				if (null == ps) throw new ServiceException("No such key: " + entryKey);
				p = ps.toString();
			} catch (PasswordManagerException e) {
				throw new ServiceException(e);
			}
		}
		
		// pipeline
		pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "password", p );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

