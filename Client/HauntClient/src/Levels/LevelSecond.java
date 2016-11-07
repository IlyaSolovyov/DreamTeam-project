package Levels;

public class LevelSecond extends LevelFirst {


	private static final long serialVersionUID = 1L;

	public LevelSecond(String resourceName) {
		super(resourceName);
		layer1 = new int[][] { 
			{ 0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
			{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}
			};
			
		layer2 = new int[][] {
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{109,111,111,464,142,111,111,111,111,110,464,464,142,111,111,111,111,111,111,110},
			{437,  0,  0,  0,  0,  0,  0,  0,  0,138,  0,  0,  0,  0,  0,  0,  0,  0,  0,138},
			{437,  0,  0,  0,142,111,114,  0,  0,115,  0,210,  0,  0,116,117,  0,156,  0,138},
			{138,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,143,144,  0,  0,  0,138},
			{138,  0,156,  0,  0,  0,  0,132,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,115},
			{437,  0,  0,  0,  0,142,111,111,111,111,110,  0,  0,156,  0,  0,  0,  0,  0,438},
			{437,  0,  0,197,  0,  0,  0,  0,  0,  0,115,  0,  0,  0,  0,210,  0,  0,171,438},
			{141,  0,  0,  0,  0,  0,  0,197,  0,  0,  0,  0,132,  0,  0,  0,  0,  0,  0,438},
			{138,  0,  0, 210, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  210,0,  0,  0,141},
			{136,111,111,111,111,111,111,114,465,465,142,111,111,111,114,465,142,111,111,137}
			};
			
		layer3 = new int[][] {
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,320,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,320,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,241,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,241,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,320,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,264,  0},
			{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
			};
			
		layerHUD = new int[][] { { 313, 282, 285, 313, 285, 313, 282, 285, 313, 282, 282, 285, 313, 282, 282, 285, 313,
				282, 282, 285 } };
	
	}
	

}