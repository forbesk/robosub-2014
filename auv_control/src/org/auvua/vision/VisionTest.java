package org.auvua.vision;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSlider;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author root
 * 
 * Filter testing class
 */
public class VisionTest {
	
	public static void main( String[] args )
	{
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		VideoCapture camera = new VideoCapture(0);
	    if(!camera.isOpened()){
	        System.out.println("Camera Error");
	    }
	    else{
	        System.out.println("Camera OK?");
	    }


	    JFrame gui = new JFrame();
	    JFrame controls = new JFrame();
	    controls.setLayout(new GridLayout(6,1));
	    JSlider[] sliders = new JSlider[6];
	    
	    int[] initValues = { 170, 255, 0, 100, 0, 100 };
	    
	    for(int i = 0; i < sliders.length; i++) {
	    	sliders[i] = new JSlider(0,255,initValues[i]);
		    sliders[i].setMaximum(255);
		    sliders[i].setMinimum(0);
	    	controls.add(sliders[i]);
	    }
	    
	    controls.setVisible(true);
	    controls.setSize(200,500);
	    
	    gui.setVisible(true);
	    gui.setPreferredSize(new Dimension(800,800));
	    
	    gui.setResizable(false);
	    gui.setSize(800, 800);
	    
	    Mat frame = new Mat(),
	        filtered = new Mat(),
            edges = new Mat(),
            rgb = new Mat();
	    
	    while(true) {
	    	camera.read(frame);
	    	
	    	Imgproc.cvtColor(frame, rgb, Imgproc.COLOR_BGR2RGB);
	    	
	    	Scalar lower = new Scalar( sliders[0].getValue(), sliders[2].getValue(), sliders[4].getValue() );
	    	Scalar upper = new Scalar( sliders[1].getValue(), sliders[3].getValue(), sliders[5].getValue() );
	    	
	    	Core.inRange(rgb, lower, upper, filtered);
	    	
	    	Imgproc.Canny(filtered, edges, 50, 150);
	    	Imgproc.cvtColor(edges, edges, Imgproc.COLOR_GRAY2BGR);
	    	
	    	List<MatOfPoint> contours = new LinkedList<MatOfPoint>();
	    	Imgproc.findContours(filtered, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	    	
	    	MatOfPoint largestContour = null;
	    	double largestArea = 0;
	    	for(MatOfPoint contour : contours) {
	    		double area = Imgproc.contourArea(contour);
	    		if(area > largestArea) {
	    			largestArea = area;
	    			largestContour = contour;
	    		}
	    	}

	    	if(largestContour != null) {
    	    	
    	    	Core.addWeighted(frame, 0.7, edges, 0.3, 0, frame);

	    		RotatedRect box = Imgproc.minAreaRect(new MatOfPoint2f(largestContour.toArray()));

	    		Core.circle(frame, box.center, 5, new Scalar(255,0,0), 3);

	    		double theta;
	    		if(box.size.height > box.size.width) {
	    			theta = box.angle / 180 * Math.PI;
	    		} else {
	    			theta = (box.angle + 90) / 180 * Math.PI;
	    		}

	    		System.out.println(theta);
	    		
	    		Point start = new Point(), end = new Point();
	    		double a = Math.cos(theta), b = Math.sin(theta);

	    		start.x = Math.round(box.center.x + 1000*(-b));
	    		start.y = Math.round(box.center.y + 1000*(a));
	    		end.x = Math.round(box.center.x - 1000*(-b));
	    		end.y = Math.round(box.center.y - 1000*(a));

	    		Core.line(frame, start, end, new Scalar(0,255,0),2);
	    	}


	    	gui.getContentPane().getGraphics().drawImage(toBufferedImage(frame), 0, 0, null);
	    	gui.setSize(frame.width(), frame.height());
	    	
	    	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
	    }
	   

	}
	
	public static Image toBufferedImage(Mat m){
	      int type = BufferedImage.TYPE_BYTE_GRAY;
	      if ( m.channels() > 1 ) {
	          type = BufferedImage.TYPE_3BYTE_BGR;
	      }
	      int bufferSize = m.channels()*m.cols()*m.rows();
	      byte [] b = new byte[bufferSize];
	      m.get(0,0,b); // get all the pixels
	      BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	      final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      System.arraycopy(b, 0, targetPixels, 0, b.length);  
	      return image;

	  }
}