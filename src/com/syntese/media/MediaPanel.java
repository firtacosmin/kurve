package com.syntese.media;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


//public class MediaPanel extends JPanel implements ControllerListener
//{
//	
//	private Player mediaPlayer;
//	private Component visual = null;
//	private Component control = null;
//    int videoWidth = 0;
//    int videoHeight = 0;
//      	 public MediaPanel( URL mediaURL )
//      	 {
//         	 setLayout( new BorderLayout() ); // use a BorderLayout
//   
//         	 // Use lightweight components for Swing compatibility
//   	 Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
//         
//         	 try
//         	 {
//            	 // create a player to play the media specified in the URL
//            	  mediaPlayer = Manager.createRealizedPlayer( mediaURL );
//            	  mediaPlayer.addControllerListener(this);
//            	 // get the components for the video and the playback controls
//            	 Component video = mediaPlayer.getVisualComponent();
//            	 //Component controls = mediaPlayer.getControlPanelComponent();
//            	 Dimension dim = new Dimension(351,289);
//            	 this.setPreferredSize(dim);
//            	 this.setSize(dim);
//            	 this.setPreferredSize(dim);
//            	 if ( video != null )
//               	 add( video, BorderLayout.CENTER ); // add video component
//   
//          	 //if ( controls != null )
//               	 //add( controls, BorderLayout.SOUTH ); // add controls
//   
//            	 mediaPlayer.start(); // start playing the media clip
//         	 } // end try
//         	 catch ( NoPlayerException noPlayerException )
//         	 {
//            	 System.err.println( "No media player found" );
//         	 } // end catch
//         	 catch ( CannotRealizeException cannotRealizeException )
//   	 {
//            	 System.err.println( "Could not realize media player" );
//         	 } // end catch
//         	 catch ( IOException iOException )
//	   {
//            	 System.err.println( "Error reading from the source" );
//         	 } // end catch
//	      } // end MediaPanel constructor
//
//		@Override
//		public void controllerUpdate(ControllerEvent ce) {
//			// TODO Auto-generated method stub
//		   if (ce instanceof RealizeCompleteEvent) {
////			   mediaPlayer.prefetch();
//		    } else if (ce instanceof PrefetchCompleteEvent) {
////		        if (visual != null)
////		        return;
////		        
////		        if ((visual = mediaPlayer.getVisualComponent()) != null) {
////		        Dimension size = visual.getPreferredSize();
////		        videoWidth = size.width;
////		        videoHeight = size.height;
////		        getContentPane().add("Center", visual);
////		        } else
////		        videoWidth = 320;
////		        if ((control = mplayer.getControlPanelComponent()) != null) {
////		        controlHeight = control.getPreferredSize().height;
////		        getContentPane().add("South", control);
////		        }
////		        setSize(videoWidth + insetWidth,
////		            videoHeight + controlHeight + insetHeight);
////		        validate();
////		        mediaPlayer.start();
//		    } else if (ce instanceof EndOfMediaEvent) {
//		    	mediaPlayer.setMediaTime(new Time(0));
//		        mediaPlayer.start();
//		    }
//		}
//   	} // end class MediaPanel

public class MediaPanel extends JInternalFrame implements ControllerListener {
Player mplayer;
Component visual = null;
Component control = null;
int videoWidth = 0;
int videoHeight = 0;
int controlHeight = 30;
int insetWidth = 10;
int insetHeight = 30;
boolean firstTime = true;


public MediaPanel(URL file) {
	super("Media", true, true, true, true);
	getContentPane().setLayout( new BorderLayout() );
	setSize(320, 10);
	setLocation(50, 50);
	setVisible(true);
	Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
	try {
		mplayer = Manager.createRealizedPlayer( file );
	} catch (NoPlayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (CannotRealizeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mplayer.addControllerListener((ControllerListener) this);
	mplayer.realize();
	addInternalFrameListener( new InternalFrameAdapter() {
	    public void internalFrameClosing(InternalFrameEvent ife) {
	    mplayer.close();
	    }
	} );
	        
}

public void controllerUpdate(ControllerEvent ce) {
if (ce instanceof RealizeCompleteEvent) {
    mplayer.prefetch();
} else if (ce instanceof PrefetchCompleteEvent) {
    if (visual != null)
    return;
    
    if ((visual = mplayer.getVisualComponent()) != null) {
    Dimension size = visual.getPreferredSize();
    videoWidth = size.width;
    videoHeight = size.height;
    getContentPane().add("Center", visual);
    } else
    videoWidth = 351;
    if ((control = mplayer.getControlPanelComponent()) != null) {
    controlHeight = control.getPreferredSize().height;
    getContentPane().add("South", control);
    }
    setSize(videoWidth + insetWidth,
        videoHeight + controlHeight + insetHeight);
    validate();
    mplayer.start();
} else if (ce instanceof EndOfMediaEvent) {
    mplayer.setMediaTime(new Time(0));
    mplayer.start();
}
}
}