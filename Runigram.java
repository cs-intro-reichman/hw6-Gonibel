import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		System.out.println("");

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;
		Color[][] image1;

		// Tests the horizontal flipping of an image:
		//image = flippedHorizontally(tinypic);
		//System.out.println();
		//print(image);

		//image = flippedVertically(tinypic);
		//System.out.println();
		//print(image);

		// image = grayScaled(tinypic);
		// System.out.println();
		// print(image);

		//image = scaled(tinypic, 3, 5);
		//print(image);

		image = read("thor.ppm");
		image1 = read("cake.ppm");
		print(image[0][0]);
		print(tinypic[0][0]);
		print(blend(image[0][0], tinypic[1][1], 0.25));

		// setCanvas(blend(image, image1, 0.25));

		// display(blend(image, image1, 0.25));
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				int red = in.readInt();
				int green = in.readInt();
				int blue = in.readInt();
				Color newcolor = new Color(red, green, blue);
				image[i][j] = newcolor;
			}
		}

		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		
		for( int i = 0; i < image.length; i++) {
			for ( int j = 0; j < image[0].length; j++) {
				print(image[i][j]);
			}
			System.out.println("");
		}

	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		
		Color[][] matrix = new Color[image.length][image[0].length];
		int colindex = image[0].length - 1;

		for(int i = 0; i < matrix.length; i++) {
			for( int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = image[i][colindex];
				colindex--;
			}
			colindex = image[0].length - 1;
		}

		return matrix;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		/// 
	
		Color[][] matrix = new Color[image.length][image[0].length];
		int rowindex = image[0].length - 1;

		for(int j = 0; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++){
				matrix[i][j] = image[rowindex][j];
				rowindex--;
			}
			rowindex = image[0].length - 1;
		}

		return matrix;
	}

	
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		
		double grey = ((0.299 * pixel.getRed()) + (0.587 * pixel.getGreen()) + (0.114 * pixel.getBlue()));
		//int red = (int) (0.299 * pixel.getRed());
		//int green = (int) (0.587 * pixel.getGreen());
		//int blue = (int) (0.144 * pixel.getBlue());
		//int lum = red + green + blue;
		//int lum = (int) Math.round(grey);
		//int lum = Math.max(0, Math.min(255, lum));
		int lum = (int) grey;
		Color newgrey = new Color(lum, lum, lum);

		return newgrey;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		/// 
		
		Color[][] greymatrix = new Color[image.length][image[0].length];
		
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				greymatrix[i][j] = luminance(image[i][j]);
			}
		}

		return greymatrix;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		
		double h0 = (double) image.length / height;
		double w0 = (double) image[0].length / width;
		Color[][] matrix = new Color[height][width];

		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++){
				int hindex = (int) (i * h0);
				int windex = (int) (j * w0);
				matrix[i][j] = image[hindex][windex];
			}
		}

		return matrix;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		
		int red = (int) ((alpha * c1.getRed()) + ((1 - alpha) * c2.getRed()));
		int green = (int) ((alpha * c1.getGreen()) + ((1 - alpha) * c2.getGreen()));
		int blue = (int) ((alpha * c1.getBlue()) + ((1 - alpha) * c2.getBlue()));
		Color newcolor = new Color(red, green, blue);

		return newcolor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color[][] matrix = new Color[image1.length][image1[0].length];
		for(int i = 0; i < image1.length; i++) {
			for (int j = 0; j < image1[0].length; j++) {
				matrix[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}

		return matrix;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		target = scaled(target, source[0].length, source.length);
		for(int i = 0; i <= n; i++) {
			double alpha = ((double)(n - i)) / n;
			Color[][] matrix = blend(source, target, alpha);
			setCanvas(matrix);
			display(matrix);
			StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

