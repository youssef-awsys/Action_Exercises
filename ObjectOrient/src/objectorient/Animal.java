package objectorient;

public class Animal {
    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        final int weight = 17;
        Dog dog = new Dog(weight);
        System.out.println(dog.numWhiskers);
    }
    public static final class Dog{
        /**
         *
         */
        public static String color = "Brown";
        private int numTeeth;
        private int numWhiskers;
        private int weight;
        
        public Dog(int weight) {
            this(weight, 16);
        }
        public Dog(int weight, int numTeeth) {
            this(weight, numTeeth, 6);
        }
        public Dog(int weight, int numTeeth, int numWhiskers) {
            this.weight = weight;
            this.numTeeth = numTeeth;
            this.numWhiskers = numWhiskers;
        }
        
    }
}
