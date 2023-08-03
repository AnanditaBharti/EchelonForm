import java.util.Scanner;
public class echlonForm {
	public static void analyse(double[][] matrix)
	{
		boolean flag=false; //determines if free variable exits
		for (int i=0;i<matrix[0].length-1;i++)           
		{
			if(i>matrix.length-1)                     
				break;
			// row:calculating the row which needs to be swapped
			int row=i;
			for (int j=i;j<matrix.length;j++)
			{
				if(matrix[j][i]!=0)
				{
					row=j;
					break;
				}
				//does free variable exists?
				else if(j==matrix.length-1 && flag==false)
					flag=true;
			}
			
			if(matrix[row]!=matrix[i])//checking is swapping required?
			{
				// swapping the elements with the non zero row 
			    for (int k=0;k<matrix[0].length;k++)
			   {
			    	double temp=matrix[i][k];
				    matrix[i][k]=matrix[row][k];
			    	matrix[row][k]=temp;
			   }
			}
			double div=matrix[i][i];//pivot element of that column
			if(div!=0) // checking pivot is non zero
			{
				for (int k=0;k<matrix[0].length;k++)//making the pivot element 1
				{
					matrix[i][k]=matrix[i][k]/div;
				}
			}
			//making rest elements in that column zero
			for (int j=i+1;j<matrix.length;j++)
			{
				if(matrix[j][i]!=0)//if element in that column is non zero
				{
					double div2=matrix[j][i];
					for (int k=i;k<matrix[0].length;k++)
					{
						matrix[j][k]=matrix[j][k]-div2*matrix[i][k];
					}
				}	
			}
		}
		
		// swapping all zero rows
		for(int i=0;i<matrix.length;i++)
		{
			boolean check1=true;
			int row1=i;  // row to be swapped
			for(int j=i;j<matrix[0].length;j++)
			{
				if(matrix[i][j]!=0) 
				{
					check1=false;
					break;
				}
			}
			if(!check1)
				continue;
			int row2=i;  // swap from
			boolean check2=true;
			for(int j=matrix.length-1;j>i;j--)
			{
				for(int k=0;k<matrix[0].length;k++)
				{
					if(matrix[j][k]!=0) 
					{
						check2=false;
						break;
					}
				}
				if(check2)
					continue;
				else
					row2=j;
			}
			if(row1!=row2) 
			{
				for(int a=0;a<matrix[0].length;a++)
				{
					double temp=matrix[row1][a];
					matrix[row1][a]=matrix[row2][a];
					matrix[row2][a]=temp;
				}
			}
			else
				break;
		}
		// printing matrix
		System.out.println("Augmented Matrix:");
		for(int a=0;a<matrix.length;a++)
		{
			for(int b=0;b<matrix[0].length;b++)
			{
				System.out.print(matrix[a][b]+" ");
			}
			System.out.println();
		}
		
		// checking for no solution
		for(int i=matrix.length-1;i>-1;i--)       
		{
			boolean check=false;
			//checking  only coefficient matrix
			for (int j=0;j<matrix[0].length-1;j++)     
			{
				if(matrix[i][j]==0)
					check=true;
				else
				{
					check=false;
					break;
				}
			}
			if(check && matrix[i][matrix[0].length-1]!=0)
			{
				System.out.println("No solution!");
			    return;
			}
		}
		// checking for infinite solution
		if(flag)
		{
			System.out.println("Infinite solution!");
			return;
		}
		else // checking for unique solution
		{
			System.out.println("Unique solution!");
			uniqueSol(matrix);
			return;
		}
	}
	public static void uniqueSol(double[][] matrix)
	{
		double[] arr=new double[matrix.length];
		for(int i=matrix.length-1;i>-1;i--)   
		{
			arr[i]=matrix[i][matrix[0].length-1];
			for(int j=i+1;j<matrix[0].length-1;j++)         
			{
				arr[i]=arr[i]-matrix[i][j]*arr[j];
				//System.out.print();
			}
			System.out.println("x"+(i+1)+" ="+arr[i]);
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of rows and columns:");
        int row=sc.nextInt();
        int col=sc.nextInt();
        double[][] matrix=new double[row][col];
       
        for(int i=0;i<row;i++)
        {
        	System.out.print("Enter values for ROW"+(i+1)+" :");
        	for(int j=0;j<col;j++)
            {
        		matrix[i][j]=sc.nextDouble();
            }
        }
        analyse(matrix);
	}}
