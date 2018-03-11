package cn.chenjw.codedemo.algorithm;

public class Sort {

	/**
	 * 冒泡排序，比较相邻的元素。如果第一个比第二个大，就交换他们两个。 
	 * @param nums
	 */
	public static void bubbleSort(int[] nums ){
		int temp = 0;
		int size = nums.length;
		System.out.println("排序前："+show(nums));
		for(int i = 0 ;i<size-1;i++){
			for(int j = 0 ;j<size-i-1;j++){
				
				if(nums[j]<nums[j+1]){
					temp = nums[j];
					nums[j]  = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
		System.out.println("排序后："+show(nums));
	}
	
	//选择排序2618
	public static void selSort(int[] numbers){
		int size = numbers.length,temp;
		for (int i = 0; i < size; i++) {   
	        int k = i;   
	        for (int j = size - 1; j >i; j--)  {   
	            if (numbers[j] < numbers[k])  k = j;   
	        }   
	      
	        temp = numbers[i];
	        numbers[i] = numbers[k];
	        numbers[k] = temp;
	       System.out.println(show(numbers)); ;
	    }   
	}
	//快速排序
	
	public static void quickSort(int[] nums,int low,int high){
		
		if(low<high){
			int middle = getMid(nums,low,high); //将numbers数组进行一分为二
	        quickSort(nums, low, middle-1);   //对低字段表进行递归排序
	        quickSort(nums, middle+1, high); //对高字段表进行递归排序
		}
		
	}
	//插入排序
	
	
	private static int getMid(int[] nums, int low, int high) {
		// TODO Auto-generated method stub
		int temp = nums[low];
		while(low<high){
			while(low<high && nums[high]>temp){
				high--;
			}
			nums[low] = nums[high];//比中轴小的记录移到低端
			while(low<high && nums[low]<temp){
				low++;
			}
			nums[high] = nums[low];//比中轴大的记录移到高端
		}
		nums[low] = temp;
		return low;
	}

	private static String show(int[] nums) {
		StringBuffer sb = new StringBuffer();
		for(int i :nums){
			sb.append(String.valueOf(i)).append("->");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
	int[] n = {2 ,5,1,8,3,11,6};
	//bubbleSort(n);
	//quickSort(n, 0, 6);
	selSort(n);
	//System.out.println(show(n));;
	}

}
