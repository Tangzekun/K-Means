import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

class kmeans 
{
	public static void main(String[] args) 
	{
		int containerSize = 0;
		int centerSize = 0;
		
		ArrayList<Point> pointContainer = new ArrayList<Point>();
		ArrayList<Point> centerContainer = new ArrayList<Point>(); 
		
		
		
		Point A1 = new Point(3.0,10.0);	
		Point A2 = new Point(4.0,6.0);	
		Point A3 = new Point(9.0,5.0);
		Point B1 = new Point(3.0,8.0);	
		Point B2 = new Point(8.0,5.0);	
		Point B3 = new Point(6.0,6.0);	
		Point C1 = new Point(2.0,2.0);
		Point C2 = new Point(5.0,7.0);	
		Point C3 = new Point(6.0,8.0);
		pointContainer.add(A1);
		pointContainer.add(A2);
		pointContainer.add(A3);
		pointContainer.add(B1);
		pointContainer.add(B2);
		pointContainer.add(B3);
		pointContainer.add(C1);
		pointContainer.add(C2);
		pointContainer.add(C3);
		
		
		Point center1 = A1;
		Point center2 = B1;
		Point center3 = C1;
		centerContainer.add(center1);
		centerContainer.add(center2);
		centerContainer.add(center3);
				
		containerSize = pointContainer.size();
		centerSize = centerContainer.size();
		
		Double squareError1 = 0.0;
		Double squareError2 = 1.0;
		int difference = 1;
		
		ArrayList<Point> cluster1 = new ArrayList<Point>();
		ArrayList<Point> cluster2 = new ArrayList<Point>();
		ArrayList<Point> cluster3 = new ArrayList<Point>();
		cluster1.add(center1);
		cluster2.add(center2);
		cluster3.add(center3);
				
		 
		while(difference != 0)
		{	
//			System.out.println("SquareError1: " + squareError1);
//			System.out.println("SquareError2: " + squareError2);
			System.out.println();
			
			difference = Double.compare(squareError1, squareError2);

			squareError1 = 0.0;
			squareError2 = 0.0;

			
			for(int j=0; j< containerSize; j++)
			{	
				ArrayList<Double> distanceContainer = new ArrayList<Double> ();
				int minDistanceIndex = 0;
				Double minDistance = 0.0;
				Double distance1 = 0.0;
				Double distance2 = 0.0;
				Double distance3 = 0.0;
				
				if(!centerContainer.contains((pointContainer.get(j))))
				{
				
					for(int i =0; i< centerSize; i++)
					{
//						System.out.println("Test point: "+ "[" + pointContainer.get(j).get_x() + "," + pointContainer.get(j).get_y() + "]");
//						System.out.println("Test center: " + "[" + centerContainer.get(i).get_x() + "," + centerContainer.get(i).get_y()+ "]");
						distance1 = Math.pow((pointContainer.get(j).get_x() - centerContainer.get(i).get_x()), 2);
						distance2 = Math.pow((pointContainer.get(j).get_y() - centerContainer.get(i).get_y()), 2);
						distance3 = Math.sqrt(distance1 + distance2 );
						
						distanceContainer.add(distance3);
//						System.out.println("Distance : " + distance3);
						
					}	
//					System.out.println();
//					System.out.println("distanceContainer size: " + distanceContainer.size());
					minDistance = Collections.min(distanceContainer);
//					System.out.println("minDistance : " + minDistance);
					minDistanceIndex = distanceContainer.indexOf(minDistance);
//					System.out.println("minDistanceIndex : " + minDistanceIndex);
//					System.out.println();
//					squareError1 += minDistance;
					
					
					if(minDistanceIndex == 0)
					{	
						if(!cluster1.contains(pointContainer.get(j)) )
						{
							cluster1.add(pointContainer.get(j));
						}
						
					}
					else if (minDistanceIndex == 1) 
					{
						if(!cluster2.contains(pointContainer.get(j)) )
						{
							cluster2.add(pointContainer.get(j));
						}
					}
					else if (minDistanceIndex == 2) 
					{
						if(!cluster3.contains(pointContainer.get(j)) )
						{
							cluster3.add(pointContainer.get(j));
						}
					}
				}
			}
//				System.out.println("SquareError1: " + squareError1);
				


				
				
				
				int sizeCluster1 =0;
				int sizeCluster2 =0;
				int sizeCluster3 =0;
				Double sum_New_x = 0.0;
				Double sum_New_y = 0.0;
				Double new_x =0.0;
				Double new_y =0.0;
				ArrayList<Integer> clusterSizeContainer = new ArrayList<Integer> ();
				ArrayList<ArrayList<Point>> clusterContainer = new ArrayList<ArrayList<Point>> ();
				
				clusterContainer.add(cluster1);
				clusterContainer.add(cluster2);
				clusterContainer.add(cluster3);
				
				sizeCluster1 = cluster1.size();
				sizeCluster2 = cluster2.size();
				sizeCluster3 = cluster3.size();	
				clusterSizeContainer.add(sizeCluster1);
				clusterSizeContainer.add(sizeCluster2);
				clusterSizeContainer.add(sizeCluster3);
				
				for(int t=0; t< clusterContainer.size(); t++)
				{
					System.out.print ("Cluster " + (t+1) + ": ");
					for(int z =0; z<clusterSizeContainer.get(t); z++)
					{
						
						System.out.print ( "[" + clusterContainer.get(t).get(z).get_x() + "," + clusterContainer.get(t).get(z).get_y() + "] ");
					}
					System.out.println();
				}
				
				for(int t=0; t< clusterContainer.size(); t++)
				{
					for(int z =0; z<clusterSizeContainer.get(t); z++)
					{
						sum_New_x += clusterContainer.get(t).get(z).get_x();
						sum_New_y += clusterContainer.get(t).get(z).get_y();
					}
//					System.out.println ("sum_New_x :" + sum_New_x);
//					System.out.println ("sum_New_y :" + sum_New_y);
//					System.out.println ("clusterSize :" + clusterSizeContainer.get(t));
					new_x = sum_New_x / clusterSizeContainer.get(t);
					new_y = sum_New_y / clusterSizeContainer.get(t);
					Point newCenter = new Point(new_x, new_y);
					centerContainer.set(t,newCenter);
					System.out.println("Center" + (t+1) + ": "  + newCenter.get_x() + ", " + newCenter.get_y());
					sum_New_x = 0.0;
					sum_New_y = 0.0;

				}
				
				for(int j=0; j< containerSize; j++)
				{	
					ArrayList<Double> distanceContainer = new ArrayList<Double> ();
					int minDistanceIndex = 0;
					Double minDistance = 0.0;
					Double distance1 = 0.0;
					Double distance2 = 0.0;
					Double distance3 = 0.0;
					
					for(int i =0; i< centerSize; i++)
					{
						distance1 = Math.pow((pointContainer.get(j).get_x() - centerContainer.get(i).get_x()), 2);
						distance2 = Math.pow((pointContainer.get(j).get_y() - centerContainer.get(i).get_y()), 2);
						distance3 = Math.sqrt(distance1 + distance2 );
						
						distanceContainer.add(distance3);
					}	
					
					minDistance = Collections.min(distanceContainer);
					minDistanceIndex = distanceContainer.indexOf(minDistance);
//					squareError2 += minDistance;
					
				}
//				System.out.println("SquareError2: " + squareError2);
				
				cluster1.clear();
				cluster2.clear();
				cluster3.clear();
		}
		
		
		
	}
	

}


class Point 
{
    Double x;
    Double y;
	public Point(Double x_L, Double y_L)
	{
		x = x_L;
		y = y_L;
	}
	
	public Double get_x() {return x;};
	public Double get_y() {return y;};
}
