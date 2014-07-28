package org.auvua.vision;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class FloorMarkerFilter implements ImageFilter {
	
	private Map<String,Object> filterOutput = new HashMap<String,Object>();
	private Mat filtered = new Mat(), rgb = new Mat(), edges = new Mat();
	private boolean drawIndicators = true;
	
	public FloorMarkerFilter() {
		filterOutput.put("markerVisible", false);
		filterOutput.put("angle", 0.0);
		filterOutput.put("xPosition", 0.0);
		filterOutput.put("yPosition", 0.0);
		filterOutput.put("length", 0.0);
		filterOutput.put("width", 0.0);
	}

	@Override
	public Map<String, Object> filter(Mat image) {
		
		Imgproc.cvtColor(image, rgb, Imgproc.COLOR_BGR2RGB);
		Scalar lower = new Scalar( 170, 0, 0 );
    	Scalar upper = new Scalar( 255, 100, 100 );
    	
    	Core.inRange(rgb, lower, upper, filtered);
    	Imgproc.Canny(filtered, edges, 50, 150);
    	
    	List<MatOfPoint> contours = new LinkedList<MatOfPoint>();
    	Imgproc.findContours(filtered, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
    	
    	MatOfPoint largestContour = null;
    	double largestArea = 0;
    	for (MatOfPoint contour : contours) {
    		double area = Imgproc.contourArea(contour);
    		if(area > largestArea) {
    			largestArea = area;
    			largestContour = contour;
    		}
    	}

    	if (largestContour != null) {
    		RotatedRect box = Imgproc.minAreaRect(new MatOfPoint2f(largestContour.toArray()));

    		double theta;
    		double length;
    		double width;
    		
    		if(box.size.height > box.size.width) {
    			theta = box.angle / 180 * Math.PI;
    			length = box.size.height;
    			width = box.size.width;
    		} else {
    			theta = (box.angle + 90) / 180 * Math.PI;
    			length = box.size.width;
    			width = box.size.height;
    		}
    		
    		if(drawIndicators) {

    	    	Imgproc.cvtColor(edges, edges, Imgproc.COLOR_GRAY2BGR);
    	    	
    	    	Core.addWeighted(image, 0.7, edges, 0.3, 0, image);
    			
    			Core.circle(image, box.center, 5, new Scalar(255,0,0), 3);
    			
	    		Point start = new Point(), end = new Point();
	    		double a = Math.cos(theta), b = Math.sin(theta);

	    		start.x = Math.round(box.center.x + 1000*(-b));
	    		start.y = Math.round(box.center.y + 1000*(a));
	    		end.x = Math.round(box.center.x - 1000*(-b));
	    		end.y = Math.round(box.center.y - 1000*(a));

	    		Core.line(image, start, end, new Scalar(0,255,0),2);
    		}
    		
    		filterOutput.put("markerVisible", true);
    		filterOutput.put("angle", theta);
    		filterOutput.put("xPosition", box.center.x);
    		filterOutput.put("yPosition", box.center.y);
    		filterOutput.put("length", length);
    		filterOutput.put("width", width);
    	} else {
    		filterOutput.put("markerVisible", false);
    		filterOutput.put("angle", 0.0);
    		filterOutput.put("xPosition", 0.0);
    		filterOutput.put("yPosition", 0.0);
    		filterOutput.put("length", 0.0);
    		filterOutput.put("width", 0.0);
    	}
    	
    	return filterOutput;
	}

	@Override
	public Map<String, Object> getFilterValues() {
		return filterOutput;
	}

}
