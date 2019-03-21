package study;

public class study {
    public static void main(String[] args) {
        int[] a = new int[]{1,5,6,8,9,3,4};



        //冒泡算法
        for(int i = 0;i<a.length;i++){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for(int j=i+1;j<a.length;j++){
                if(a[j] < min){
                    min = a[j];
                    index = j;
                }
            }
            if(a[i] > min){
                a[index] = a[i];
                a[i] = min;
            }
        }

        for(int b:a){
            System.out.println(b);
        }
    }
}
