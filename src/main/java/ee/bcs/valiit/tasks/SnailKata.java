package ee.bcs.valiit.tasks;

public class SnailKata {

    public static void main(String[] args) {
        snail(new int[][]{{}});
    }

    public static int[] snail(int[][] array) {

        int[] newArray = new int[array.length * array.length];
        int i = array.length;
        int tracker = 0;
        int character = 0;

        int topY = 0;
        int topX = 0;

        int rightY = 0;
        int rightX = 0;

        int downY = array.length - 1;
        int downX = 0;

        int leftY = 0;
        int leftX = 0;

        // handle exception for [[]]
        if (array.length == 0 || array[0].length == 0){
            int[] ints = new int[0];
        return ints;
        }

        // handle exception if insert is 2x2 or 1x1 cube
        int k = 1;
        if (array.length < 2){
            k = 0;
        }
        //fill outer cube then move in
        while (character < newArray.length) {

            while (topX < array.length - k - tracker) {
                newArray[character] = array[topY][topX++];
                character++;
            }
            rightX = topX;
            while (k != 0 && rightY < array.length - k - tracker) {
                newArray[character] = array[rightY++][rightX];
                character++;
            }
            downX = rightX;
            while (k != 0 && downX > 0 + tracker) {
                newArray[character] = array[downY][downX--];
                character++;
            }
            leftY = downY;
            while (k != 0 && leftY > 0 + tracker) {
                newArray[character] = array[leftY--][leftX];
                character++;
            }

            // if outer circle is filled, move to inner circle
            // leftY is always last to be filled when filling clockwise
            if (leftY == (tracker)) {
                tracker++;
                topY = tracker;
                topX = tracker;

                rightY = tracker;
                rightX = tracker;

                downY = array.length - 1 - tracker;
                downX = tracker;

                leftY = tracker;
                leftX = tracker;
            }
            if (array.length % 2 == 1 && array.length > 2 && (topY == array.length / 2) && (topX == array.length / 2)) {
                newArray[character] = array[topY][topX++];
                character++;
            }
        }
        return newArray;
    }
}
